package cat.touffu.management.javafx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BoardControler {
    @FXML
    public Text title_of_board;
    public HBox HboxOfList;
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


    public void addAList(ActionEvent actionEvent) {
        Platform.runLater(() -> {
            try {
                Stage stage = new Stage();
                Application addNewList = new DialogAddNewList(HboxOfList);
                addNewList.start(stage);
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }

    public void initData(StackPane stack){
        this.stack = stack;
    }
}