package cat.touffu.management.javafx;

import cat.touffu.management.components.list.ListModule;
import cat.touffu.management.components.list.application.command.CreateNewListInProject.CreateNewListOfCardsInProject;
import cat.touffu.management.components.projects.ProjectModule;
import cat.touffu.management.components.projects.application.command.CreateNewProject.CreateNewProject;
import cat.touffu.management.kernel.command.CommandBus;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class DialogAddNewListController implements EventHandler<ActionEvent> {

    public TextField list_name;
    private Text counterOfCardInAList;
    private HBox HboxOfList;
    private Stage stage;
    private Button button;
    private VBox firstList;
    private Button save;
    private TextField textField;
    private VBox global;

    private final CommandBus commandBus = ListModule.commandBus();

    public void saveNewList(){
        firstList = new VBox();
        HBox title = new HBox();
        global = new VBox();
        ScrollPane scrollPane = new ScrollPane();

        button = new Button("+ Add a card");
        button.setStyle("-fx-background-color:  #3d3d40; -fx-background-radius: 5; -fx-border-style:  segments(6, 6, 6, 6)  line-cap round; -fx-border-color:  #4d4b50; -fx-border-width: 2; -fx-border-radius: 5");
        button.setPrefHeight(46);
        button.setMinWidth(285);
        button.setTranslateY(25);
        button.setTextFill(Paint.valueOf("white"));
        button.setOnAction(this);

        counterOfCardInAList = new Text("0");
        counterOfCardInAList.setStyle("-fx-font-size: 18; -fx-fill: white");
        counterOfCardInAList.setTranslateX(25);
        counterOfCardInAList.setTranslateY(6);

        String listTitle = list_name.getText();
        addNewList();
        Text TitleOfAList = new Text(listTitle);
        TitleOfAList.setStyle("-fx-font-size: 18; -fx-fill: white");
        TitleOfAList.setTranslateX(25);
        TitleOfAList.setTranslateY(6);
        title.setSpacing(20);

        title.getChildren().addAll(counterOfCardInAList, TitleOfAList);
        firstList.getChildren().add(title);
        firstList.setStyle("-fx-background-color: #3D3D40;  -fx-min-height: 165; -fx-min-width: 285; -fx-background-radius: 5; -fx-translate-y: 25.0; -fx-padding: 10");
        firstList.setSpacing(10);

        global.getChildren().addAll(firstList, button);

        scrollPane.setContent(global);
        scrollPane.setStyle("-fx-min-height: 285; -fx-min-width: 285; -fx-max-height: 600; -fx-border-color: transparent; -fx-background-color: transparent; -fx-background: transparent");
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        
        HboxOfList.setPadding(new Insets(10,10,10,10));
        HboxOfList.setSpacing(10);
        HboxOfList.getChildren().add(HboxOfList.getChildren().size() - 1,scrollPane);

        stage.close();
    }

    private void addNewList() {
        try {
            String listTitle = list_name.getText();
            checkListTitleInput(listTitle);
            sendCreateListCommandWithTitle(listTitle);
        } catch (Exception e) {
            // TODO error view
            e.printStackTrace();
        }

    }

    private void checkListTitleInput(String listTitle) throws Exception {
        if(listTitle.trim().length() == 0) {
            throw new Exception("Empty List Title");
        }
    }

    private void sendCreateListCommandWithTitle(String title) {
        // TODO get the id of the project we are in.
        final String projectId = "0308eb60-bb8c-422e-a356-a0f5434a5325";
        CreateNewListOfCardsInProject command = new CreateNewListOfCardsInProject(title, projectId);
        this.commandBus.send(command);
    }



    public void initData(HBox hboxOfList, Stage stage){
        this.HboxOfList = hboxOfList;
        this.stage = stage;
    }


    @Override
    public void handle(ActionEvent actionEvent) {
        if(actionEvent.getSource() == button){
            setTextField();
        }
        if(actionEvent.getSource() == save){
            firstList.getChildren().remove(firstList.getChildren().size() - 1);
            firstList.getChildren().remove(firstList.getChildren().size() - 1);
        }
    }

    private void setCard() {
        VBox pane = new VBox();

        pane.setStyle("-fx-background-color: #252525; -fx-padding: 20; -fx-background-radius: 5");
        pane.setMinHeight(65);
        pane.setMaxWidth(269);
        Text content = new Text(textField.getText());
        content.setWrappingWidth(200);
        content.setStyle("-fx-fill: white; -fx-font-size: 24");
        pane.getChildren().add(content);
        firstList.getChildren().add(pane);
    }

    public void setTextField(){
        textField = new TextField();
        textField.setStyle("-fx-background-color: #252525; -fx-text-fill: white; -fx-min-height: 56; " +
                "-fx-max-width: 269; -fx-background-radius: 7; -fx-border-radius: 5; -fx-border-color: #fbc95c; -fx-border-width: 2; -fx-padding: 10");
        textField.setTranslateX(7);
        save = new Button("save");
        save.setStyle("-fx-background-color: #fbc95c; -fx-text-fill: #252525");
        save.setMaxWidth(131);
        save.setMinHeight(28);
        save.setTranslateX(7);
        save.setOnAction(this);
        firstList.getChildren().addAll(textField, save);
    }
}
