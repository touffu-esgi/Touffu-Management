package cat.touffu.management.javafx.projects;

import cat.touffu.management.components.projects.ProjectModule;
import cat.touffu.management.components.projects.application.command.CreateNewProject.CreateNewProject;
import cat.touffu.management.kernel.command.CommandBus;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

public class DialogCreateNewProjectController{

    public TextField list_name;
    private final CommandBus commandBus = ProjectModule.commandBus();
    private StackPane window;

    public void saveNewList(ActionEvent actionEvent) throws Exception {
        String value = list_name.getText();
        commandBus.send(new CreateNewProject(value));
        this.close();
    }

    public void init(StackPane window) {
        this.window = window;
    }

    public void onCancel(ActionEvent actionEvent) throws Exception {
        this.close();
    }

    private void close() throws Exception {
        var stack = window.getChildren();
        stack.remove(stack.toArray().length-1);
    }

}
