package cat.touffu.management.javafx;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class SettingsController {
    public TextField title;
    public ChoiceBox colors;


    void initData(String title) {
        this.title.setText(title);
        ObservableList<String> colors = FXCollections.observableArrayList("rouge", "vert", "bleu");
        this.colors.setItems(colors);
    }

}
