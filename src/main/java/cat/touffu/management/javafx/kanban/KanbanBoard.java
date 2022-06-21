package cat.touffu.management.javafx.kanban;

import cat.touffu.management.components.cards.CardsModule;
import cat.touffu.management.components.cards.application.query.RetrieveCardsInProject.RetrieveCardsInProject;
import cat.touffu.management.components.cards.domain.Card;
import cat.touffu.management.components.cards.domain.CardStatus;
import cat.touffu.management.components.projects.domain.Project;
import cat.touffu.management.javafx.board.Board;
import cat.touffu.management.javafx.card.CardInListController;
import cat.touffu.management.javafx.card.DialogAddCard;
import cat.touffu.management.kernel.query.QueryBus;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.apache.commons.lang3.NotImplementedException;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KanbanBoard {
    public Button change_cover;
    public Text title_of_board;
    private Node root;
    private Project project;
    private final QueryBus cardQueryBus = CardsModule.queryBus();
    private final Map<CardStatus, VBox> lists = new HashMap<>();
    private final Map<String, CardInListController> cardsControllers = new HashMap<>();

    public void openBoardSetting(ActionEvent actionEvent) {
        throw new NotImplementedException("openBoardSetting");
    }

    public void onClickToAddCard(ActionEvent actionEvent) {
    }

    public void init(Node board, Project project) {
        this.root = board;
        this.project = project;

        this.title_of_board.setText(this.project.title());
        this.loadLists();
        this.getCardsOfProjectAndFillLists();
    }

    private void loadLists() {
        this.lists.put(CardStatus.TODO, (VBox) this.root.lookup("#ToDo"));
        this.lists.put(CardStatus.IN_PROGRESS, (VBox) this.root.lookup("#InProgress"));
        this.lists.put(CardStatus.DONE, (VBox) this.root.lookup("#Done"));
    }

    private void getCardsOfProjectAndFillLists() {
        List<Card> cards = cardQueryBus.request(new RetrieveCardsInProject(project.id().value()));
        this.clearAndFillListsWith(cards);
    }

    private void clearAndFillListsWith(List<Card> cards) {
        clearAllLists();
        cards.forEach(this::addCardInListByItsStatus);
    }

    private void clearAllLists() {
        this.lists.values().forEach(list -> list.getChildren().clear());
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
        Node view = loader.load();
        CardInListController controller = loader.getController();
        controller.initData(card, view);
        view.setId(card.id().value());
        return controller;
    }

    public void onClickOnCard(String id) {
        var card = this.cardsControllers.get(id).getCard();
        Platform.runLater(()-> {
            try {
                new DialogAddCard(Board.getInstance().controller.stack, card).start(new Stage());
            } catch (Exception e){}
        });
    }
}
