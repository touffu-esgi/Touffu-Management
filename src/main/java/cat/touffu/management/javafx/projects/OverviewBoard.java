package cat.touffu.management.javafx.projects;

import cat.touffu.management.components.projects.domain.Project;
import cat.touffu.management.javafx.board.Board;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OverviewBoard {
    public VBox lines;
    public GridPane grid;
    private Node root;
    private List<Project> projects = new ArrayList<>(); // TODO observable list
    private final List<OverviewProjectCard> projectCards = new ArrayList<>();
    private int currentColumn = 0;
    private int currentRow = 0;

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
        this.projects.forEach(this::loadProjetCard);
        this.projectCards.stream()
                .forEach(this::addInGrid);
    }

    private void addInGrid(OverviewProjectCard card) {
        card.getRoot().setId(card.projectId());
        if(this.currentColumn >= 3) {
            this.currentColumn = 0;
            this.currentRow += 1;
            this.grid.addRow(this.currentRow);
        }
        this.grid.add(card.getRoot(), this.currentColumn, this.currentRow);
        this.currentColumn += 1;
    }

    private void loadProjetCard(Project p) {
        try {
            var loader = new FXMLLoader(OverviewBoard.class.getResource("overview-project-card.fxml"));
            Node view = loader.load();
            OverviewProjectCard card = loader.getController();
            card.init(view, p);
            this.projectCards.add(card);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Node getRoot() {
        return root;
    }

}
