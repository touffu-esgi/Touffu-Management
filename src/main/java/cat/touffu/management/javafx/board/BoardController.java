package cat.touffu.management.javafx.board;

import cat.touffu.management.javafx.SettingBoard;
import cat.touffu.management.javafx.projects.DialogCreateNewProject;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.apache.commons.lang3.NotImplementedException;

public class BoardController {
    @FXML
    public Text title_of_board;
    private StackPane stack;

    public void openBoardSetting(ActionEvent actionEvent) {
        Platform.runLater(() -> {
            try {
                Application settingBoard = new SettingBoard(title_of_board, this.stack);
                Stage stage = new Stage();
                settingBoard.start(stage);
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }

    public void initData(StackPane stack){
        this.stack = stack;
    }

    public void onClickToCreateNewProject(ActionEvent actionEvent) {
        Platform.runLater(() -> {
            try {
                DialogCreateNewProject createProject = new DialogCreateNewProject();
                createProject.start(new Stage());
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }

    public void addCard(ActionEvent actionEvent) {
        throw new NotImplementedException("Add card");
    }


    public void addProjectInLeftBar(String id, String title) {
        System.out.println("id = " + id + ", title = " + title);
    }
}