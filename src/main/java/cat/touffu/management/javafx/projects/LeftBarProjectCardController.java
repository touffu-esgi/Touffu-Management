package cat.touffu.management.javafx.projects;

import cat.touffu.management.components.projects.domain.Project;
import cat.touffu.management.javafx.board.Board;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

public class LeftBarProjectCardController {
    public Label text;
    private Node root;
    private boolean isSelected;
    Color selected = Color.valueOf("#3D3D40");
    Color unselected = Color.valueOf("#252525");
    private Project project;

    public void init(Node root, Project project) {
        this.root = root;
        this.project = project;
        this.text.setText(this.project.title());
    }

    public void setSelected(boolean selected) {
        if(this.isSelected == selected) return; // didn't change

        isSelected = selected;
        this.updateBackGroundColor();
    }

    private void updateBackGroundColor() {
        var newColor = this.isSelected
                ? this.selected
                : unselected;
        ((Region)this.root).setBackground(Background.fill(newColor));
    }

    public void onSelectProject(MouseEvent mouseEvent) {
        Board.getInstance().controller.selectProject(this.project.id().value());
    }
}
