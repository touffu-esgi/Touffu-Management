package cat.touffu.management.javafx.projects;

import cat.touffu.management.components.projects.domain.Project;
import cat.touffu.management.javafx.board.Board;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class OverviewBoard {
    public VBox lines;
    public GridPane grid;
    private Node root;
    private List<Project> projects; // TODO observable list
    private List<OverviewProjectCard> projectCards;
    private int currentColumn = 1;
    private int currentRow = 0;

    public void init(Node board, List<Project> projects) {
        this.root = board;
        this.projects = projects;

        try {
            this.loadProjetCards();
            this.loadLines();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadLines() {
        /*IntStream.range(0, this.getNumberOfLines())
                .forEach(()-> );*/
    }

    private void loadProjetCards() throws IOException {
        this.projectCards = this.projects.stream()
                .map(this::loadProjetCard)
                .filter(Objects::nonNull)
                .toList();
        this.projectCards.stream()
                .map(OverviewProjectCard::getRoot)
                .forEach(this::addInGrid);
    }

    private void addInGrid(Node card) {
        if(this.currentColumn >= 3) {
            this.currentColumn = 0;
            this.currentRow += 1;
            this.grid.addRow(this.currentRow);
        }
        this.grid.add(card, this.currentColumn, this.currentRow);
        this.currentColumn += 1;
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

    public int getNumberOfLines() {
        int numberOfProjects = this.projects.size();
        int addProjectButton = 1;
        int itemPerLine = 3;
        double totalItems = numberOfProjects + addProjectButton;
        return (int) Math.ceil(totalItems / itemPerLine);
    }

}
