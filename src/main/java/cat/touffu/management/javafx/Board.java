package cat.touffu.management.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class Board extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("board");
        StackPane stack = new StackPane();

        FXMLLoader root = new FXMLLoader(getClass().getResource("board.fxml"));
        stack.getChildren().add(root.load());
        BoardController boardController = root.getController();
        boardController.initData(stack);
        Scene scene = new Scene(stack,1512,982, true);
        stage.setMinWidth(1512);
        stage.setMinHeight(982);
        stage.setMaxWidth(1512);
        stage.setMaxHeight(982);
        stage.setScene(scene);
        stage.show();
    }


}

