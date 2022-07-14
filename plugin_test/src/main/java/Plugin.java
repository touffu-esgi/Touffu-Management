import cat.touffu.management.plugin.JavaFxPlugin;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import cat.touffu.management.javafx.Controller;
import javafx.scene.layout.Pane;

public class Plugin implements JavaFxPlugin {

    private final Controller controller;
    private final FXMLLoader view;

    public Plugin() {
        this.controller = new ControllerTest();
        this.view = new FXMLLoader(getClass().getResource("hello.fxml"));
        view.setController(this.controller);
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
    public FXMLLoader getView() {
        return this.view;
    }
}
