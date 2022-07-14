package cat.touffu.management.plugin;

import cat.touffu.management.javafx.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

public interface JavaFxPlugin{
    String getName();
    Controller getController();
    Node getView();
}
