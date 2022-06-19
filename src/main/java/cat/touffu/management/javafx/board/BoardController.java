package cat.touffu.management.javafx.board;

import cat.touffu.management.components.cards.CardsModule;
import cat.touffu.management.components.cards.application.query.RetrieveProjects.RetrieveCardsInProject;
import cat.touffu.management.components.cards.domain.Card;
import cat.touffu.management.components.projects.ProjectModule;
import cat.touffu.management.components.projects.application.query.RetrieveOneProject.RetrieveOneProject;
import cat.touffu.management.components.projects.application.query.RetrieveProjects.RetrieveProjects;
import cat.touffu.management.components.projects.domain.Project;
import cat.touffu.management.javafx.SettingBoard;
import cat.touffu.management.javafx.projects.DialogCreateNewProject;
import cat.touffu.management.kernel.exception.ProjectNotFoundException;
import cat.touffu.management.kernel.query.QueryBus;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.apache.commons.lang3.NotImplementedException;

import java.util.List;

public class BoardController {
    @FXML
    public Text title_of_board;
    private StackPane stack;
    private final QueryBus projectQueryBus = ProjectModule.queryBus();
    private final QueryBus cardQueryBus = CardsModule.queryBus();
    private StringProperty projectTitle = new SimpleStringProperty("");
    private List<Project> projects;

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

    public void initData(StackPane stack){
        this.stack = stack;
        List<Project> projects = projectQueryBus.send(new RetrieveProjects());
        this.projects = projects;
        projects.forEach(p -> addProjectInLeftBar(p.id().value(), p.title()));
    }

    public void onClickToCreateNewProject(ActionEvent actionEvent) {
        Platform.runLater(() -> {
            try {
                DialogCreateNewProject createProject = new DialogCreateNewProject();
                createProject.start(new Stage());
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }

    public void addCard(ActionEvent actionEvent) {
        throw new NotImplementedException("Add card");
    }


    public void addProjectInLeftBar(String id, String title) {
        VBox projectList = (VBox) this.stack.lookup("#projectList");
        Text project = new Text(title);
        project.setId(id);
        project.setFill(Paint.valueOf("white"));
        project.setOnMouseClicked(onClickProjectCard());
        projectList.getChildren().add(project);
    }

    private EventHandler<MouseEvent> onClickProjectCard() {
        return new EventHandler<>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                var id = mouseEvent.getPickResult().getIntersectedNode().getId();
                selectProject(id);
            }
        };
    }

    private void selectProject(String id) {
        Project project = projectQueryBus.send(new RetrieveOneProject(id));
        if(project == null) throw new ProjectNotFoundException(id);
        List<Card> cards = cardQueryBus.send(new RetrieveCardsInProject(project.id().value()));
        System.out.println("cards = " + cards);
        this.projectTitle.setValue(project.title());
        this.fillListsWith(cards);
    }

    private void fillListsWith(List<Card> cards) {
    }

    public StringProperty projectTitleProperty() {
        return projectTitle;
    }

    public String getProjectTitle() {
        return this.projectTitle.get();
    }
}