package cat.touffu.management.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class DialogAddNewList extends Application {

    private HBox hboxOfList = null;

    public DialogAddNewList(HBox hboxOfList) {
        this.hboxOfList = hboxOfList;
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("board");
        StackPane stack = new StackPane();
        FXMLLoader root = new FXMLLoader(getClass().getResource("Dialog_add_new_list.fxml"));

        stack.getChildren().add(root.load());

        DialogAddNewListController dialogAddNewListController = root.getController();
        dialogAddNewListController.initData(this.hboxOfList, stage);

        root.setController(dialogAddNewListController);

        Scene scene = new Scene(stack,511,365, true);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
