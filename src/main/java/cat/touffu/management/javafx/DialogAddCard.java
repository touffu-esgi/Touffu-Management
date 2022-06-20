package cat.touffu.management.javafx;

import cat.touffu.management.components.cards.domain.CardStatus;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class DialogAddCard extends Application {

    private StackPane stack;
    private final Map<CardStatus, VBox> lists;
    public DialogAddCard(StackPane stage, Map<CardStatus, VBox> lists) {
        this.stack = stage;
        this.lists = lists;
    }

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("add Card");

        FXMLLoader root = new FXMLLoader(getClass().getResource("addCardDialog.fxml"));
        this.stack.getChildren().add(root.load());

        AddCardController cardController = root.getController();
        cardController.initData(lists, this.stack);
        root.setController(cardController);
    }
}
