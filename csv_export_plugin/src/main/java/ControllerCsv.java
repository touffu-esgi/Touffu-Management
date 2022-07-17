import cat.touffu.management.components.cards.domain.Card;
import cat.touffu.management.components.projects.domain.CardId;
import cat.touffu.management.components.projects.domain.Project;
import cat.touffu.management.javafx.Controller;
import cat.touffu.management.javafx.board.Board;
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
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class ControllerCsv implements Controller {

    @FXML() public ComboBox<ProjectChoice> select_project;
    @FXML() public Button export_button;
    private List<ProjectChoice> projects;

    @Override
    public void init() {
        this.projects = Board.getInstance()
                .controller.getProjects()
                .stream()
                .map(p -> new ProjectChoice(p.id().value(), p.title(), p))
                .toList();
        this.fillProjectCombo();
        this.export_button.setOnMouseClicked(this.onClickExport());
    }

    private void fillProjectCombo() {
        var cellFactory = this.getProjectChoiceCellFactory();

        ObservableList<ProjectChoice> choices = FXCollections.observableArrayList(this.projects);
        this.select_project.setItems(choices);
        this.select_project.setButtonCell(cellFactory.call(null));
        if(choices.size() > 0) this.select_project.setValue(choices.get(0));
        else this.export_button.setDisable(true);
        this.select_project.setCellFactory(cellFactory);
    }

    private EventHandler<MouseEvent> onClickExport() {
        return mouseEvent -> {
            var project = this.select_project.getValue().entity();
            var cards = Board.getInstance().controller.getCards(project.id().value());
            this.onExport(project, cards);
        };
    }

    public void onExport(Project project, List<Card> cards) {
        var json = projectToJson(project, cards);
        System.out.println(json.toString(4));
    }

    private JSONObject projectToJson(Project project, List<Card> cards) {
        var json = new JSONObject();
        json.put("id", project.id().value());
        json.put("title", project.title());
        json.put("cards", new JSONArray(cards.stream().map(this::cardToJson).toList()));
        return json;
    }

    private JSONObject cardToJson(Card card) {
        var json = new JSONObject();
        json.put("id", card.id().value());
        json.put("title", card.title());
        json.put("status", card.cardStatus().toString());
        return json;
    }

    private Callback<ListView<ProjectChoice>, ListCell<ProjectChoice>> getProjectChoiceCellFactory() {
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
