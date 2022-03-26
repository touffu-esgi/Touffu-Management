package cat.touffu.management.components.list.exposition.cli;

import cat.touffu.management.components.list.ListModule;
import cat.touffu.management.components.list.application.command.CreateNewListInProject.CreateNewListOfCardsInProject;
import cat.touffu.management.components.list.domain.ListId;
import cat.touffu.management.kernel.command.CommandBus;
import picocli.CommandLine;

@CommandLine.Command(
        name = "new-listId",
        description = "Create a new listId in a project."
)
public class NewListOfCard implements Runnable{
    @CommandLine.Parameters(index = "0", paramLabel = "title", description = "List title")
    String content;

    @CommandLine.Parameters(index = "1", paramLabel = "id_project", description = "id of a project")
    String id_project;
    
    CommandBus commandBus = ListModule.commandBus();

    @Override
    public void run() {
        CreateNewListOfCardsInProject createNewListOfCard = new CreateNewListOfCardsInProject(content, id_project);
        ListId listId = this.commandBus.send(createNewListOfCard);
        System.out.println("New listId of cards '" + this.content + "' have been created with id : " + listId.value());
    }
}
