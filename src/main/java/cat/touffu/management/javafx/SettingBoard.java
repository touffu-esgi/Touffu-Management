package cat.touffu.management.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SettingBoard extends Application{

    private final Text title;
    private StackPane stack;
    public SettingBoard(Text title, StackPane stack) {
        this.title = title;
        this.stack = stack;
    }

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("board");

        FXMLLoader root = new FXMLLoader(getClass().getResource("Modify_project.fxml"));
        this.stack.getChildren().add(root.load());

        SettingsController settingsController = root.getController();
        settingsController.initData(title.getText());

        root.setController(settingsController);
    }
}
