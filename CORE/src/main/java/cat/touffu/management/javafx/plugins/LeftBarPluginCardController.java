package cat.touffu.management.javafx.plugins;

import cat.touffu.management.components.projects.domain.Project;
import cat.touffu.management.javafx.board.Board;
import cat.touffu.management.javafx.projects.LeftBarProjectCardController;
import cat.touffu.management.plugin.JavaFxPlugin;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class LeftBarPluginCardController {
    public Label text;
    private Node root;
    private boolean isSelected = false;
    String selected = "#3D3D40";
    String unselected = "transparent";
    private JavaFxPlugin plugin;

    public static LeftBarPluginCardController of(JavaFxPlugin plugin){
        var loader = new FXMLLoader(LeftBarPluginCardController.class.getResource("leftbar-plugin-card.fxml"));
        Node view = null;
        try {view = loader.load();}
        catch (IOException e) {
            System.out.println("KOO");
            return null;}
        LeftBarPluginCardController controller = loader.getController();

        controller.init(view, plugin);
        view.setId(plugin.getName());

        return controller;
    }

    public void init(Node root, JavaFxPlugin plugin) {
        this.root = root;
        this.plugin = plugin;
        this.text.setText(this.plugin.getName());
        this.setSelected(false);
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
        this.updateBackGroundColor();
    }

    private void updateBackGroundColor() {
        var newColor = this.isSelected
                ? this.selected
                : unselected;
        var box = (HBox)this.root;
        box.setStyle("-fx-background-color: " + newColor + ";-fx-background-radius: 5");
    }

    public void onSelectPlugin(MouseEvent mouseEvent) {
        Board.getInstance().controller.selectPluginByName(this.plugin.getName());
    }

    public Node getView() {
        return root;
    }

    public JavaFxPlugin getPlugin() {
        return plugin;
    }
}
