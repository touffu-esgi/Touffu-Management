package cat.touffu.management.javafx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Platform.runLater(()-> {
            try {Board.getInstance().start(new Stage());}
            catch (Exception e) {e.printStackTrace();}
        });
    }
}
