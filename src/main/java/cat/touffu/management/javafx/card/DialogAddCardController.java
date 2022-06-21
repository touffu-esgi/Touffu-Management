package cat.touffu.management.javafx.card;

import cat.touffu.management.components.cards.CardsModule;
import cat.touffu.management.components.cards.adapter.CardStatusException;
import cat.touffu.management.components.cards.adapter.CardStatusFromStringAdapter;
import cat.touffu.management.components.cards.adapter.CardStatusToStringAdapter;
import cat.touffu.management.components.cards.application.command.createCard.AddCardInProject;
import cat.touffu.management.components.cards.application.command.updateCard.UpdateCard;
import cat.touffu.management.components.cards.domain.Card;
import cat.touffu.management.components.cards.domain.CardStatus;
import cat.touffu.management.javafx.board.Board;
import cat.touffu.management.kernel.command.CommandBus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DialogAddCardController {
    public TextField CardDescription;
    public ComboBox<StatusChoice> listOfStatus;
    private final CardStatusFromStringAdapter cardStatusFromStringAdapter = new CardStatusFromStringAdapter();
    private final CardStatusToStringAdapter cardStatusToStringAdapter = new CardStatusToStringAdapter();
    private final CommandBus cardCommandBus = CardsModule.commandBus();
    private StackPane window;
    private final ObservableList<StatusChoice> statusChoices = FXCollections.observableArrayList(
            new StatusChoice(CardStatus.TODO, "To Do"),
            new StatusChoice(CardStatus.IN_PROGRESS, "In Progress"),
            new StatusChoice(CardStatus.DONE, "Done")
    );
    private Card card;

    private CardStatus witchListFromString(String choice){
        return cardStatusFromStringAdapter.adapt(choice);
    }

    public void saveCard(ActionEvent actionEvent) throws IOException {
        boolean cardIsPresent = this.card != null;
        if (cardIsPresent)
            updateCard(card.id().value());
        else
            addCardInProject();

        this.close();
    }

    private void addCardInProject() {
        String cardTitle = CardDescription.getText();
        var projectId = Board.getInstance().controller.getSelectedProject().id().value();
        cardCommandBus.send(new AddCardInProject(
                cardTitle,
                projectId,
                cardStatusToStringAdapter.adapt(listOfStatus.getValue().status()))
        );
    }

    private void updateCard(String id) {
        cardCommandBus.send(new UpdateCard(
                card.id().value(),
                CardDescription.getText(),
                cardStatusToStringAdapter.adapt(listOfStatus.getValue().status())
        ));
    }

    private void close() {
        var stack = window.getChildren();
        stack.remove(stack.toArray().length-1);
    }

    public void initData(StackPane window, Card card){
        this.window = window;
        this.card = card;
        setUpChoiceBox();

        if(card != null) setUpFieldWithCardToUpdate(card);
    }

    private void setUpFieldWithCardToUpdate(Card card) {
        this.CardDescription.setText(card.title());
        this.listOfStatus.setValue(getStatusChoiceByStatus(card.cardStatus()));
    }

    private void setUpChoiceBox() {
        var cellFactory = getStatusChoiceCellFactory();

        this.listOfStatus.setItems(statusChoices);
        this.listOfStatus.setButtonCell(cellFactory.call(null));
        this.listOfStatus.setValue(getStatusChoiceByStatus(CardStatus.TODO));
        this.listOfStatus.setCellFactory(cellFactory);
    }

    private StatusChoice getStatusChoiceByStatus(CardStatus status) {
        return this.statusChoices.stream()
                .filter(choice -> choice.status().equals(status))
                .findFirst()
                .orElseThrow(()-> new CardStatusException(status.toString() + " is not a choice handled." ));
    }

    private Callback<ListView<StatusChoice>, ListCell<StatusChoice>> getStatusChoiceCellFactory() {
        return new Callback<ListView<StatusChoice>, ListCell<StatusChoice>>() {

            @Override
            public ListCell<StatusChoice> call(ListView<StatusChoice> l) {
                return new ListCell<StatusChoice>() {

                    @Override
                    protected void updateItem(StatusChoice item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            setText(item.text());
                        }
                    }
                } ;
            }
        };
    }

    public void CancelSaveCard(ActionEvent actionEvent) {
        this.close();
    }
}
