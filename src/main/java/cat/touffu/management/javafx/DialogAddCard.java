package cat.touffu.management.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DialogAddCard extends Application {

    private StackPane stack;
    private VBox[] lists;
    public DialogAddCard(StackPane stage, VBox[] lists) {
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
