package cat.touffu.management.plugin;

import cat.touffu.management.javafx.Controller;
import javafx.fxml.FXMLLoader;

public interface JavaFxPlugin{
    String getName();
    Controller getController();
    FXMLLoader getView();
}
