package cat.touffu.management.javafx.projects;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DialogCreateNewProject extends Application {
    public Stage stage;
    final DialogCreateNewProjectController controller;

    public DialogCreateNewProject(StackPane stack) {
        FXMLLoader root = new FXMLLoader(getClass().getResource("addProjectDialog.fxml"));

        try {stack.getChildren().add(root.load());}
        catch (IOException e) {e.printStackTrace();}
        this.controller = root.getController();
        this.controller.init(stack);
    }

    @Override
    public void start(Stage stage) throws Exception {

    }
}
