import cat.touffu.management.javafx.Controller;
import cat.touffu.management.plugin.JavaFxPlugin;
import javafx.fxml.FXMLLoader;

public class TestPlugin implements JavaFxPlugin {

    public TestPlugin() {
        System.out.println("constructor of TestPlugin");
    }

    @Override
    public String getName() {
        return "TestPlugin";
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
