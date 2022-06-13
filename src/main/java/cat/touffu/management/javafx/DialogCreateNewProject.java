package cat.touffu.management.javafx;

import cat.touffu.management.javafx.DialogAddNewListController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class DialogCreateNewProject extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Add a project");
        StackPane stack = new StackPane();
        FXMLLoader root = new FXMLLoader(getClass().getResource("addProjectDialog.fxml"));

        stack.getChildren().add(root.load());

        Scene scene = new Scene(stack,511,365, true);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
