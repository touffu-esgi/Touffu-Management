import cat.touffu.management.javafx.Controller;
import cat.touffu.management.plugin.JavaFxPlugin;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.io.IOException;

public class Plugin implements JavaFxPlugin {

    private final Controller controller;
    private Node view;

    public Plugin() {
        this.controller = new ControllerCsv();
        var loader = new FXMLLoader(getClass().getResource("view.fxml"));
        loader.setController(this.controller);

        try {this.view = loader.load();}
        catch (IOException e) {e.printStackTrace();}
    }

    @Override
    public String getName() {
        return "CsvExportPlugin";
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