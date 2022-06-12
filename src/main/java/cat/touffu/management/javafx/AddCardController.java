package cat.touffu.management.javafx;

import cat.touffu.management.javafx.CardInListController.CardInListController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class AddCardController {
    public TextField CardDescription;
    public ChoiceBox ListOfCard;
    private VBox[] vboxs;
    private StackPane stack;
    private Map<String, Integer> listEnum  = Map.of("ToDo", 0, "InProgress", 1, "Done", 2);

    private int witchListFromString(String choice){
        return this.listEnum.get(choice);
    }

    public void saveCard(ActionEvent actionEvent) throws IOException {
        stack.getChildren().remove(stack.getChildren().toArray().length - 1);
        setUpCard(witchListFromString(ListOfCard.getValue().toString()));
    }

    private void setUpCard(int idOfAList) throws IOException {
        FXMLLoader root = new FXMLLoader(getClass().getResource("cardInList.fxml"));
        System.out.println(ListOfCard.getValue().toString());
        this.vboxs[idOfAList].getChildren().add(root.load());
        CardInListController cardInListController = root.getController();
        cardInListController.initData(CardDescription.getText());
    }

    public void initData(VBox[] vboxs, StackPane stack){
        this.vboxs = vboxs;
        this.stack = stack;
        setUpChoiceBox();
    }

    private void setUpChoiceBox() {
        ObservableList<String> lists = FXCollections.observableArrayList("ToDo", "InProgress", "Done");
        this.ListOfCard.setItems(lists);
    }

    public void CancelAddCard(ActionEvent actionEvent) {
        System.out.println("out");
    }
}
