package cat.touffu.management.javafx.projects;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DialogCreateNewProject extends Application {
    private final StackPane stack;
    public Stage stage;
    final DialogCreateNewProjectController controller;

    public DialogCreateNewProject() {
        this.stack = new StackPane();
        FXMLLoader root = new FXMLLoader(getClass().getResource("addProjectDialog.fxml"));

        try {stack.getChildren().add(root.load());}
        catch (IOException e) {e.printStackTrace();}

        this.controller = root.getController();
        controller.app = this;
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.setTitle("Add a project");

        Scene scene = new Scene(stack,511,365, true);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
