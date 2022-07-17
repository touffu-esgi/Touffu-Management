import cat.touffu.management.javafx.Controller;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;

public class ControllerCsv extends Controller {

    public ComboBox select_project;

    public void onExport(MouseEvent mouseEvent) {
        System.out.println("CSV");
    }
}
