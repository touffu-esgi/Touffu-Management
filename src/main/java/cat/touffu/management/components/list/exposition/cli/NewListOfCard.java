package cat.touffu.management.components.list.exposition.cli;

import cat.touffu.management.components.list.ListModule;
import cat.touffu.management.components.list.application.command.CreateNewListInProject.CreateNewListOfCardsInProject;
import cat.touffu.management.components.list.domain.ListId;
import cat.touffu.management.kernel.command.CommandBus;
import cat.touffu.management.kernel.exception.NotFoundException;
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
        CreateNewListOfCardsInProject createNewListOfCard = new CreateNewListOfCardsInProject(title, id_project);
        try {
            this.commandBus.send(createNewListOfCard);
        } catch (NotFoundException e) {
            System.out.println("Project with id " + id_project + " not found.");
        }

        System.out.println("New list of cards '" + this.title + "' have been created.");
    }
}
