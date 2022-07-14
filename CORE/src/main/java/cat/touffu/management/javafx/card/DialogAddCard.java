package cat.touffu.management.javafx.card;

import cat.touffu.management.components.cards.domain.Card;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class DialogAddCard extends Application {

    private final Card card;
    private StackPane stack;
    public DialogAddCard(StackPane stage, Card card) {
        this.stack = stage;
        this.card = card;
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader root = new FXMLLoader(getClass().getResource("addCardDialog.fxml"));
        this.stack.getChildren().add(root.load());

        DialogAddCardController cardController = root.getController();
        cardController.initData(stack, card);
        root.setController(cardController);
    }
}
