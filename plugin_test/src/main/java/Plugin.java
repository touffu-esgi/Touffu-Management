import cat.touffu.management.javafx.board.Board;
import cat.touffu.management.plugin.JavaFxPlugin;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import cat.touffu.management.javafx.Controller;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class Plugin implements JavaFxPlugin {

    private final Controller controller;
    private Node view;

    public Plugin() {
        this.controller = new ControllerTest();
        var loader = new FXMLLoader(getClass().getResource("hello.fxml"));
        loader.setController(this.controller);

        try {
            this.view = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getName() {
        return "TestPlugin";
    }

    @Override
    public Controller getController() {
        return this.controller;
    }

    @Override
    public Node getView() {
        return this.view;
    }
}