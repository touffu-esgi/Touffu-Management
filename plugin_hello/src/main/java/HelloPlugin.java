import cat.touffu.management.javafx.Controller;
import cat.touffu.management.plugin.JavaFxPlugin;
import javafx.fxml.FXMLLoader;

public class HelloPlugin implements JavaFxPlugin {

    private final Controller controller;
    private final FXMLLoader view;

    public HelloPlugin(Controller controller, FXMLLoader view) {
        this.controller = controller;
        this.view = view;
    }

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }

    @Override
    public Controller getController() {
        return controller;
    }

    @Override
    public FXMLLoader getView() {
        return view;
    }
}
