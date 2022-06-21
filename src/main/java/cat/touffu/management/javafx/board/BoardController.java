package cat.touffu.management.javafx.board;

import cat.touffu.management.components.cards.CardsModule;
import cat.touffu.management.components.cards.application.query.RetrieveCardsInProject.RetrieveCardsInProject;
import cat.touffu.management.components.cards.application.query.RetrieveOneCard.RetrieveOneCard;
import cat.touffu.management.components.cards.domain.Card;
import cat.touffu.management.components.cards.domain.CardId;
import cat.touffu.management.components.cards.domain.CardStatus;
import cat.touffu.management.components.projects.ProjectModule;
import cat.touffu.management.components.projects.application.query.RetrieveOneProject.RetrieveOneProject;
import cat.touffu.management.components.projects.application.query.RetrieveProjects.RetrieveProjects;
import cat.touffu.management.components.projects.domain.Project;
import cat.touffu.management.javafx.card.CardInListController;
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
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
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
    private Map<String, CardInListController> cardsControllers = new HashMap<>();

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

    public void onClickToAddCard(ActionEvent actionEvent) {
        if(!isOneProjectSelected()) return;
        Platform.runLater(() -> {
            try {
                Application dialogAddCard = new DialogAddCard(this.stack, null);
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

    public void selectProject(String id) {
        Project project = projectQueryBus.request(new RetrieveOneProject(id));
        if(project == null) throw new ProjectNotFoundException(id);
        List<Card> cards = cardQueryBus.request(new RetrieveCardsInProject(project.id().value()));
        this.projectTitle.setValue(project.title());
        this.selectedProject = project;
        this.fillListsWith(cards);
    }

    private void fillListsWith(List<Card> cards) {
        clearAllLists();
        cards.forEach(this::addCardInListByItsStatus);
    }

    private void clearAllLists() {
        this.lists.values().forEach(list -> list.getChildren().clear());
    }

    private EventHandler<? super MouseEvent> onClickCard() {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                var id = mouseEvent.getPickResult().getIntersectedNode().getId();
                openUpdateCardDialog(id);
            }
        };
    }

    private void openUpdateCardDialog(String id) {
        Card card = cardQueryBus.request(new RetrieveOneCard(id));
        if(card == null) return;
        Platform.runLater(() -> {
            try {
                Application dialogAddCard = new DialogAddCard(this.stack, card);
                dialogAddCard.start(new Stage());
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }

    public void addCardInListByItsStatus(Card card) {
        try {
            var newCardController = newCardControllerFromCard(card);
            this.lists.get(card.cardStatus()).getChildren().add(newCardController.getView());
            this.cardsControllers.put(card.id().value(), newCardController);
        }
        catch (Exception e) {
            // TODO card not added
        }
    }

    private CardInListController newCardControllerFromCard(Card card) throws IOException {
        FXMLLoader loader = new FXMLLoader(CardInListController.class.getResource("cardInList.fxml"));
        Node root = loader.load();
        CardInListController controller = loader.getController();
        controller.initData(card, root);
        root.setId(card.id().value());
        root.setOnMouseClicked(onClickCard());
        return controller;
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

    public void updateCard(Card card) {
        var oldCardController = this.cardsControllers.get(card.id().value());
        oldCardController.contentCard.setText(card.title());
        boolean statusHasChanged = !oldCardController.getCard().cardStatus().equals(card.cardStatus());
        if(statusHasChanged) changeStatusOfCard(card);
    }

    private void changeStatusOfCard(Card card) {
        this.removeCardFromOldListById(card.id().value());
        this.addCardInListByItsStatus(card);
    }

    private void removeCardFromOldListById(String id) {
        var oldStatus = this.cardsControllers.get(id).getCard().cardStatus();
        var oldList = this.lists.get(oldStatus);
        var oldCard = oldList.getChildren().stream()
                .filter(card -> card.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Card " + id + "not in list " + oldStatus) );
        oldList.getChildren().remove(oldCard);
        this.cardsControllers.remove(id);
    }
}