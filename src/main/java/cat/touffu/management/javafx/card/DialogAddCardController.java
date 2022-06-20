package cat.touffu.management.javafx.card;

import cat.touffu.management.components.cards.CardsModule;
import cat.touffu.management.components.cards.adapter.CardStatusFromStringAdapter;
import cat.touffu.management.components.cards.application.command.createCard.AddCardInProject;
import cat.touffu.management.components.cards.domain.CardStatus;
import cat.touffu.management.javafx.board.Board;
import cat.touffu.management.javafx.board.BoardController;
import cat.touffu.management.kernel.command.CommandBus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DialogAddCardController {
    public TextField CardDescription;
    public ChoiceBox ListOfCard;
    private Map<CardStatus, VBox> lists = new HashMap<>();
    private final CardStatusFromStringAdapter cardStatusFromStringAdapter = new CardStatusFromStringAdapter();
    private final CommandBus cardCommandBus = CardsModule.commandBus();
    private StackPane window;

    private CardStatus witchListFromString(String choice){
        return cardStatusFromStringAdapter.adapt(choice);
    }

    public void saveCard(ActionEvent actionEvent) throws IOException {
        String cardTitle = CardDescription.getText();
        var projectId = Board.getInstance().controller.getSelectedProject().id().value();
        cardCommandBus.send(new AddCardInProject(
                cardTitle,
                projectId,
                ListOfCard.getValue().toString())
        );
        this.close();
    }

    private void close() {
        var stack = window.getChildren();
        stack.remove(stack.toArray().length-1);
    }

    private void setUpCard(CardStatus status) throws IOException {
        FXMLLoader root = new FXMLLoader(getClass().getResource("cardInList.fxml"));
        System.out.println(ListOfCard.getValue().toString());
        this.lists.get(status).getChildren().add(root.load());
        CardInListController cardInListController = root.getController();
        cardInListController.initData(CardDescription.getText());
    }

    public void initData(Map<CardStatus, VBox> lists, StackPane window){
        this.lists = lists;
        this.window = window;
        setUpChoiceBox();
    }

    private void setUpChoiceBox() {
        ObservableList<String> lists = FXCollections.observableArrayList("ToDo", "In Progress", "Done");
        this.ListOfCard.setItems(lists);
    }

    public void CancelAddCard(ActionEvent actionEvent) {
        this.close();
    }
}
