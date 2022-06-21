package cat.touffu.management.javafx.kanban;

import cat.touffu.management.components.projects.domain.Project;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import org.apache.commons.lang3.NotImplementedException;

public class KanbanBoard {
    public Button change_cover;
    public Text title_of_board;
    private Node root;
    private Project project;

    public void openBoardSetting(ActionEvent actionEvent) {
        throw new NotImplementedException("openBoardSetting");
    }

    public void onClickToAddCard(ActionEvent actionEvent) {
    }

    public void init(Node board, Project project) {
        this.root = board;
        this.project = project;
    }
}
