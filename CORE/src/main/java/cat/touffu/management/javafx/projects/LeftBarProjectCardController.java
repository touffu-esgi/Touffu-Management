package cat.touffu.management.javafx.projects;

import cat.touffu.management.components.projects.domain.Project;
import cat.touffu.management.javafx.board.Board;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class LeftBarProjectCardController {
    public Label text;
    private Node root;
    private boolean isSelected = false;
    String selected = "#3D3D40";
    String unselected = "transparent";
    private Project project;

    public void init(Node root, Project project) {
        this.root = root;
        this.project = project;
        this.text.setText(this.project.title());
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

    public void onSelectProject(MouseEvent mouseEvent) {
        Board.getInstance().controller.selectProject(this.project.id().value());
    }

    public Node getView() {
        return root;
    }

    public Project getProject() {
        return project;
    }
}
