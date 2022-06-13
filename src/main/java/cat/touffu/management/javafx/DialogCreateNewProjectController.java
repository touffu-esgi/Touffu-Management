package cat.touffu.management.javafx;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

public class DialogCreateNewProjectController{


    public TextField list_name;

    public void saveNewList(ActionEvent actionEvent) {
        String value = list_name.getText();
    }
}
