package cat.touffu.management.javafx.board;

import cat.touffu.management.components.cards.CardsModule;
import cat.touffu.management.components.projects.ProjectModule;
import cat.touffu.management.components.projects.application.query.RetrieveOneProject.RetrieveOneProject;
import cat.touffu.management.components.projects.application.query.RetrieveProjects.RetrieveProjects;
import cat.touffu.management.components.projects.domain.Project;
import cat.touffu.management.javafx.card.CardInListController;
import cat.touffu.management.javafx.SettingBoard;
import cat.touffu.management.javafx.kanban.KanbanBoard;
import cat.touffu.management.javafx.projects.DialogCreateNewProject;
import cat.touffu.management.javafx.projects.LeftBarProjectCardController;
import cat.touffu.management.javafx.projects.OverviewBoard;
import cat.touffu.management.kernel.exception.ProjectNotFoundException;
import cat.touffu.management.kernel.query.QueryBus;
import cat.touffu.management.plugin.JavaFxPlugin;
import cat.touffu.management.plugin.PluginHandler;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.stream.Stream;

public class BoardController {
    @FXML
    public Text title_of_board;
    public VBox main;
    public StackPane stack;
    private final QueryBus projectQueryBus = ProjectModule.queryBus();
    private final QueryBus cardQueryBus = CardsModule.queryBus();
    @FXML private VBox projectList;
    private Map<String, CardInListController> cardsControllers = new HashMap<>();
    private Map<String, LeftBarProjectCardController> projectsControllers = new HashMap<>();

    public KanbanBoard kanbanBoard;
    private OverviewBoard overviewBoard;
    private final PluginHandler pluginHandler = new PluginHandler();

    public void openBoardSetting(ActionEvent actionEvent) {
        Platform.runLater(() -> {
            try {
                Application settingBoard = new SettingBoard(title_of_board, this.stack);
                Stage stage = new Stage();
                settingBoard.start(stage);
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }

    public BoardController() {}

    public void initData(StackPane stack) {
        this.stack = stack;
        List<Project> projects = projectQueryBus.request(new RetrieveProjects());
        try {
            this.loadLeftBarWith(projects);
            this.loadOverviewBoard();

            this.pluginHandler.loadPlugins();
            this.pluginHandler.selectPluginByName("TestPlugin");
            this.pluginHandler.displayCurrentPlugin(this.stack);
            
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        this.projectList = (VBox) this.stack.lookup("#projectList");
    }

    private void loadLeftBarWith(List<Project> projects) {
        projects.forEach(this::addProjectInLeftBar);
    }

    public void onClickToCreateNewProject(ActionEvent actionEvent) {
        this.openAddProjectDialog();
    }

    public void openAddProjectDialog() {
        Platform.runLater(() -> {
            try {
                DialogCreateNewProject createProject = new DialogCreateNewProject(this.stack);
                createProject.start(new Stage());
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }

    public void addProjectInLeftBar(Project p) {
        try {
            var projectController = newProjectCardControllerOf(p);
            var view = projectController.getView();
            this.projectList.getChildren().add(view);
            this.projectsControllers.put(p.id().value(), projectController);
        }
        catch (Exception e) {
            e.printStackTrace();
            // TODO project not added
        }
    }

    private LeftBarProjectCardController newProjectCardControllerOf(Project p) throws IOException {
        FXMLLoader loader = new FXMLLoader(LeftBarProjectCardController.class.getResource("leftbar-project-card.fxml"));
        Node root = loader.load();
        LeftBarProjectCardController controller = loader.getController();
        controller.init(root, p);
        root.setId(p.id().value());
        return controller;
    }

    public void selectProject(String id) {
        Project project = projectQueryBus.request(new RetrieveOneProject(id));
        if(project == null) throw new ProjectNotFoundException(id);

        try{
            this.loadKanbanBoardOf(project);
            this.focusOnSelectedProjectCard();
        }
        catch (IOException e) {e.printStackTrace();}
    }

    private void replaceBoardBy(Node board) {
        this.main.getChildren().clear();
        this.main.getChildren().add(board);
        // TODO free controllers
    }

    private void loadKanbanBoardOf(Project project) throws IOException {
        var loader = new FXMLLoader(KanbanBoard.class.getResource("kanban-board.fxml"));
        Node kanban = loader.load();
        this.kanbanBoard = loader.getController();
        this.kanbanBoard.init(kanban, project);
        this.replaceBoardBy(kanban);
    }

    private void focusOnSelectedProjectCard() {
        this.projectsControllers.values().forEach(p -> p.setSelected(false));
        this.projectsControllers.get(this.kanbanBoard.getProject().id().value()).setSelected(true);
    }

    private void loadOverviewBoard() throws IOException {
        var loader = new FXMLLoader(OverviewBoard.class.getResource("overview-board.fxml"));
        Node overview = loader.load();
        this.overviewBoard = loader.getController();
        this.overviewBoard.init(overview, this.projectsControllers.values().stream().map(c ->c.getProject()).toList());
        this.replaceBoardBy(overview);
    }

    public void onClickToOpenOverview(MouseEvent mouseEvent) {
        try {
            this.loadOverviewBoard();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}