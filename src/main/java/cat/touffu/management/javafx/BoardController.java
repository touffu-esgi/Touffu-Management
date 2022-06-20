package cat.touffu.management.javafx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Arrays;

public class BoardController {
    @FXML
    public Text title_of_board;

    public VBox ToDo;
    public VBox InProgress;
    public VBox Done;

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

    public void addCard(ActionEvent actionEvent) {
        Platform.runLater(() -> {
            try {
                Application dialogAddCard = new DialogAddCard(this.stack, new VBox[]{ToDo, InProgress, Done});
                Stage stage = new Stage();
                dialogAddCard.start(stage);
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }
}