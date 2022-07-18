import cat.touffu.management.javafx.Controller;
import cat.touffu.management.plugin.JavaFxPlugin;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.io.IOException;

public class Plugin implements JavaFxPlugin {

    private final Controller controller;
    private Node view;

    public Plugin() throws IOException {
        var loader = new FXMLLoader(getClass().getResource("view.fxml"));
        this.controller = new ControllerJson();

        loader.setController(this.controller);
        this.view = loader.load();
        this.controller.init();
    }


    @Override
    public String getName() {
        return "JsonExport";
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