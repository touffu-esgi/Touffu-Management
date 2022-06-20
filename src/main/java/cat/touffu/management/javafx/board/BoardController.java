package cat.touffu.management.javafx.board;

import cat.touffu.management.components.cards.CardsModule;
import cat.touffu.management.components.cards.application.query.RetrieveCardsInProject.RetrieveCardsInProject;
import cat.touffu.management.components.cards.domain.Card;
import cat.touffu.management.components.cards.domain.CardStatus;
import cat.touffu.management.components.projects.ProjectModule;
import cat.touffu.management.components.projects.application.query.RetrieveOneProject.RetrieveOneProject;
import cat.touffu.management.components.projects.application.query.RetrieveProjects.RetrieveProjects;
import cat.touffu.management.components.projects.domain.Project;
import cat.touffu.management.javafx.card.DialogAddCard;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardController {
    @FXML
    public Text title_of_board;
    private StackPane stack;
    private final QueryBus projectQueryBus = ProjectModule.queryBus();
    private final QueryBus cardQueryBus = CardsModule.queryBus();
    private StringProperty projectTitle = new SimpleStringProperty("");
    private Project selectedProject;
    private List<Project> projects;
    @FXML private VBox projectList;
    private Map<CardStatus, VBox> lists = new HashMap<>();

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
        List<Project> projects = projectQueryBus.request(new RetrieveProjects());
        this.projects = projects;
        projects.forEach(p -> addProjectInLeftBar(p.id().value(), p.title()));

        this.projectList = (VBox) this.stack.lookup("#projectList");
        this.lists.put(CardStatus.TODO, (VBox) this.stack.lookup("#ToDo"));
        this.lists.put(CardStatus.IN_PROGRESS, (VBox) this.stack.lookup("#InProgress"));
        this.lists.put(CardStatus.DONE, (VBox) this.stack.lookup("#Done"));
    }

    public void onClickToCreateNewProject(ActionEvent actionEvent) {
        Platform.runLater(() -> {
            try {
                DialogCreateNewProject createProject = new DialogCreateNewProject(this.stack);
                createProject.start(new Stage());
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }

    public void addCard(ActionEvent actionEvent) {
        if(!isOneProjectSelected()) return;
        Platform.runLater(() -> {
            try {
                Application dialogAddCard = new DialogAddCard(this.stack, this.lists);
                Stage stage = new Stage();
                dialogAddCard.start(stage);
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }

    private boolean isOneProjectSelected() {
        return !this.projectTitle.get().isBlank();
    }


    public void addProjectInLeftBar(String id, String title) {
        Text project = new Text(title);
        project.setId(id);
        project.setFill(Paint.valueOf("white"));
        project.setOnMouseClicked(onClickProjectCard());
        this.projectList.getChildren().add(project);
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
        Project project = projectQueryBus.request(new RetrieveOneProject(id));
        if(project == null) throw new ProjectNotFoundException(id);
        List<Card> cards = cardQueryBus.request(new RetrieveCardsInProject(project.id().value()));
        this.projectTitle.setValue(project.title());
        this.selectedProject = project;
        this.fillListsWith(cards);
    }

    private void fillListsWith(List<Card> cards) {
        List<Card> todo = cards.stream().filter(c -> c.cardStatus().equals(CardStatus.TODO)).toList();
        List<Card> inProgress = cards.stream().filter(c -> c.cardStatus().equals(CardStatus.IN_PROGRESS)).toList();
        List<Card> done = cards.stream().filter(c -> c.cardStatus().equals(CardStatus.DONE)).toList();

        setListWithCards(this.lists.get(CardStatus.TODO), todo);
        setListWithCards(this.lists.get(CardStatus.IN_PROGRESS), inProgress);
        setListWithCards(this.lists.get(CardStatus.DONE), done);
    }

    private EventHandler<? super MouseEvent> onClickCard() {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                throw new NotImplementedException("Click on card");
            }
        };
    }

    public void setListWithCards(VBox list, List<Card> cardsToAdd) {
        var cards = cardsToAdd.stream()
                .map(this::newCardBoxFromCard)
                .toList();
        list.getChildren().clear();
        list.getChildren().addAll(cards);
    }

    public void addCardByItsStatus(Card card) {
        this.lists.get(card.cardStatus()).getChildren().add(newCardBoxFromCard(card));
    }

    private Text newCardBoxFromCard(Card c) {
        var t = new Text(c.title());
        t.setId(c.id().value());
        t.setFill(Paint.valueOf("white"));
        t.setOnMouseClicked(onClickCard());
        return t;
    }

    public StringProperty projectTitleProperty() {
        return projectTitle;
    }

    public String getProjectTitle() {
        return this.projectTitle.get();
    }

    public Project getSelectedProject() {
        return selectedProject;
    }
}