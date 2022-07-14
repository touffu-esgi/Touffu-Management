package cat.touffu.management.javafx.board;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Board extends Application {

    public BoardController controller;
    private final StackPane stack;

    private final static Board INSTANCE = new Board();

    public static Board getInstance() {
        return INSTANCE;
    }

    private Board(){

        FXMLLoader root = new FXMLLoader(getClass().getResource("board.fxml"));
        stack = new StackPane();

        try{
            stack.getChildren().add(root.load());
            this.controller = root.getController();
            this.controller.initData(stack);
        }
        catch (IOException e) { }
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("board");

        Scene scene = new Scene(stack,1512,982, true);
        stage.setMinWidth(1512);
        stage.setMinHeight(982);
        stage.setMaxWidth(1512);
        stage.setMaxHeight(982);
        stage.setScene(scene);
        stage.show();
    }


}

