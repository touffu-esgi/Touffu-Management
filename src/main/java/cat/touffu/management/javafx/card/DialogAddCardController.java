package cat.touffu.management.javafx.card;

import cat.touffu.management.components.cards.CardsModule;
import cat.touffu.management.components.cards.adapter.CardStatusFromStringAdapter;
import cat.touffu.management.components.cards.adapter.CardStatusToStringAdapter;
import cat.touffu.management.components.cards.application.command.createCard.AddCardInProject;
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
    private Map<CardStatus, VBox> lists = new HashMap<>();
    private final CardStatusFromStringAdapter cardStatusFromStringAdapter = new CardStatusFromStringAdapter();
    private final CardStatusToStringAdapter cardStatusToStringAdapter = new CardStatusToStringAdapter();
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
                cardStatusToStringAdapter.adapt(listOfStatus.getValue().status))
        );
        this.close();
    }

    private void close() {
        var stack = window.getChildren();
        stack.remove(stack.toArray().length-1);
    }

    public void initData(Map<CardStatus, VBox> lists, StackPane window){
        this.lists = lists;
        this.window = window;
        setUpChoiceBox();
    }

    private void setUpChoiceBox() {

        ObservableList<StatusChoice> statusChoices = FXCollections.observableArrayList(
                new StatusChoice(CardStatus.TODO, "To Do"),
                new StatusChoice(CardStatus.IN_PROGRESS, "In Progress"),
                new StatusChoice(CardStatus.DONE, "Done")
        );

        var cellFactory = getStatusChoiceCellFactory();

        this.listOfStatus.setItems(statusChoices);
        this.listOfStatus.setButtonCell(cellFactory.call(null));
        this.listOfStatus.setValue(statusChoices.get(0));
        this.listOfStatus.setCellFactory(cellFactory);
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

    public void CancelAddCard(ActionEvent actionEvent) {
        this.close();
    }

    private record StatusChoice(
            CardStatus status,
            String text
    ) {
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            StatusChoice that = (StatusChoice) o;
            return status == that.status;
        }

        @Override
        public int hashCode() {
            return Objects.hash(status);
        }
    }
}
