import cat.touffu.management.javafx.Controller;
import cat.touffu.management.plugin.JavaFxPlugin;
import javafx.fxml.FXMLLoader;

public class TestPlugin implements JavaFxPlugin {
    @Override
    public String getName() {
        return null;
    }

    @Override
    public Controller getController() {
        return null;
    }

    @Override
    public FXMLLoader getView() {
        return null;
    }
}
