package cat.touffu.management.javafx;

import cat.touffu.management.components.cards.adapter.CardStatusException;
import cat.touffu.management.components.cards.adapter.CardStatusFromStringAdapter;
import cat.touffu.management.components.cards.domain.CardStatus;
import cat.touffu.management.javafx.CardInListController.CardInListController;
import cat.touffu.management.kernel.exception.NotFoundException;
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

public class AddCardController {
    public TextField CardDescription;
    public ChoiceBox ListOfCard;
    private Map<CardStatus, VBox> lists = new HashMap<>();
    private StackPane stack;
    private final CardStatusFromStringAdapter cardStatusFromStringAdapter = new CardStatusFromStringAdapter();

    private CardStatus witchListFromString(String choice){
        return cardStatusFromStringAdapter.adapt(choice);
    }

    public void saveCard(ActionEvent actionEvent) throws IOException {
        stack.getChildren().remove(stack.getChildren().toArray().length - 1);
        try {
            setUpCard(witchListFromString(ListOfCard.getValue().toString()));
        } catch (CardStatusException e) {
            // TODO error UI display
            throw new NotFoundException(e.getMessage());
        }

    }

    private void setUpCard(CardStatus status) throws IOException {
        FXMLLoader root = new FXMLLoader(getClass().getResource("cardInList.fxml"));
        System.out.println(ListOfCard.getValue().toString());
        this.lists.get(status).getChildren().add(root.load());
        CardInListController cardInListController = root.getController();
        cardInListController.initData(CardDescription.getText());
    }

    public void initData(Map<CardStatus, VBox> lists, StackPane stack){
        this.lists = lists;
        this.stack = stack;
        setUpChoiceBox();
    }

    private void setUpChoiceBox() {
        ObservableList<String> lists = FXCollections.observableArrayList("ToDo", "In Progress", "Done");
        this.ListOfCard.setItems(lists);
    }

    public void CancelAddCard(ActionEvent actionEvent) {
        System.out.println("out");
    }
}
