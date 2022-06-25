package cat.touffu.management.javafx.projects;

import cat.touffu.management.components.projects.domain.Project;
import cat.touffu.management.javafx.board.Board;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class OverviewBoard {
    public VBox lines;
    public HBox firstLine;
    private Node root;
    private List<Project> projects; // TODO observable list
    private List<OverviewProjectCard> projectCards;

    public void init(Node board, List<Project> projects) {
        this.root = board;
        this.projects = projects;

        try {
            this.loadProjetCards();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadProjetCards() throws IOException {
        this.projectCards = this.projects.stream()
                .map(this::loadProjetCard)
                .filter(Objects::nonNull)
                .toList();
        this.projectCards.stream()
                .map(OverviewProjectCard::getRoot)
                .forEach(card -> this.firstLine.getChildren().add(card));
    }

    private OverviewProjectCard loadProjetCard(Project p) {
        try {
            var loader = new FXMLLoader(OverviewBoard.class.getResource("overview-project-card.fxml"));
            Node view = loader.load();
            OverviewProjectCard card = loader.getController();
            card.init(view, p);
            return card;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Node getRoot() {
        return root;
    }

    public void onClickAddProject(MouseEvent mouseEvent) {
        Board.getInstance().controller.openAddProjectDialog();
    }


}
