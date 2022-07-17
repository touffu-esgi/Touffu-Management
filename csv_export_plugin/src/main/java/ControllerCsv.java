import cat.touffu.management.javafx.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import java.util.List;

public class ControllerCsv implements Controller {

    @FXML() public ComboBox<ProjectChoice> select_project;
    @FXML() public Button export_button;
    private List<ProjectChoice> projects;

    @Override
    public void init() {
        this.projects = List.of(
                new ProjectChoice("1", "title"),
                new ProjectChoice("2", "title2")
        );
        this.fillProjectCombo();
        this.export_button.setDisable(true);
        this.export_button.setOnMouseClicked(this.onClickExport());
    }

    private void fillProjectCombo() {
        ObservableList<ProjectChoice> choices = FXCollections.observableArrayList(this.projects);
        this.select_project.setItems(choices);
    }

    private EventHandler<MouseEvent> onClickExport() {
        return mouseEvent -> {
            System.out.println("EXPORT CSV");
        };
    }

    public void onExport(MouseEvent mouseEvent) {
        System.out.println("CSV");
    }

    private Callback<ListView<ProjectChoice>, ListCell<ProjectChoice>> getStatusChoiceCellFactory() {
        return new Callback<ListView<ProjectChoice>, ListCell<ProjectChoice>>() {
            @Override
            public ListCell<ProjectChoice> call(ListView<ProjectChoice> projectChoiceListView) {
                return new ListCell<ProjectChoice>(){
                    @Override
                    protected void updateItem(ProjectChoice item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            //setTextFill(Color.WHITE);
                            //setBackground(Background.fill(Color.valueOf("#252525")));
                            setText(item.title());
                        }
                    }
                };
            }
        };
    }
}
