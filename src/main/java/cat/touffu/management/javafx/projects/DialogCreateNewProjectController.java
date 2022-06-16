package cat.touffu.management.javafx.projects;

import cat.touffu.management.components.projects.ProjectModule;
import cat.touffu.management.components.projects.application.command.CreateNewProject.CreateNewProject;
import cat.touffu.management.kernel.command.CommandBus;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

public class DialogCreateNewProjectController{

    public DialogCreateNewProject app;


    public TextField list_name;
    private final CommandBus commandBus = ProjectModule.commandBus();

    public void saveNewList(ActionEvent actionEvent) throws Exception {
        String value = list_name.getText();
        commandBus.send(new CreateNewProject(value));
        this.close();
    }

    public void onCancel(ActionEvent actionEvent) throws Exception {
        this.close();
    }

    private void close() throws Exception {
        this.app.stage.close();
        this.app.stop();
    }

}
