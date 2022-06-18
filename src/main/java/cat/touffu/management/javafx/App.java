package cat.touffu.management.javafx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Platform.runLater(()-> {
            try {new Board().start(new Stage());}
            catch (Exception e) {e.printStackTrace();}
        });
    }
}
