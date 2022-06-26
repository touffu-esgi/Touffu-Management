package cat.touffu.management.javafx.projects;

import cat.touffu.management.components.projects.domain.Project;
import cat.touffu.management.javafx.Main;
import cat.touffu.management.javafx.board.Board;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

public class OverviewProjectCard {
    public Label title;
    public Pane image;
    private Node root;
    private Project project;

    public void init(Node view, Project p) {
        this.root = view;
        this.project = p;
        this.title.setText(p.title());
        var imgPath = "https://images.unsplash.com/photo-1517487881594-2787fef5ebf7?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=870&q=80";
        this.image.setStyle("-fx-background-image: url('"+imgPath+"');");
        this.image.setId(projectId());
    }

    public Node getRoot() {
        return root;
    }

    public void clickProject(MouseEvent mouseEvent) {
        var id = mouseEvent.getPickResult().getIntersectedNode().getId();
        Board.getInstance().controller.selectProject(id);
    }

    public String projectId() {
        return this.project.id().value();
    }
}
