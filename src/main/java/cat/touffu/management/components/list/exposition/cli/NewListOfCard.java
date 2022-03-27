package cat.touffu.management.components.list.exposition.cli;

import cat.touffu.management.components.list.ListModule;
import cat.touffu.management.components.list.application.command.CreateNewListInProject.CreateNewListOfCardsInProject;
import cat.touffu.management.kernel.command.CommandBus;
import picocli.CommandLine;

@CommandLine.Command(
        name = "new-list",
        description = "Create a new list in a project."
)
public class NewListOfCard implements Runnable{
    @CommandLine.Parameters(index = "0", paramLabel = "title", description = "List title")
    String title;

    @CommandLine.Parameters(index = "1", paramLabel = "id_project", description = "id of a project")
    String id_project;
    
    CommandBus commandBus = ListModule.commandBus();

    @Override
    public void run() {
        CreateNewListOfCardsInProject command = new CreateNewListOfCardsInProject(title, id_project);
        this.commandBus.setExceptionFilter(new ListCliExceptionFilter());
        this.commandBus.send(command);
    }
}
