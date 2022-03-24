package cat.touffu.management.components.list.exposition.cli;

import cat.touffu.management.components.list.application.command.create_new_list_in_project.CreateNewListOfCards;
import cat.touffu.management.components.list.domain.ListId;
import cat.touffu.management.kernel.command.CommandBus;
import cat.touffu.management.components.list.ListModule;
import picocli.CommandLine;

@CommandLine.Command(
        name = "new-list",
        description = "Create a new list in a project."
)
public class NewListOfCard implements Runnable{
    @CommandLine.Parameters(index = "0", paramLabel = "content", description = "List title")
    String content;

    @CommandLine.Parameters(index = "1", paramLabel = "id_project", description = "id of a project")
    String id_project;
    CommandBus commandBus = ListModule.commandBus();

    @Override
    public void run() {
        CreateNewListOfCards createNewListOfCard = new CreateNewListOfCards(content, id_project);
        ListId listId = this.commandBus.send(createNewListOfCard);
        System.out.println("New list of project '" + this.content + "' have been created in list with uuid = " + listId.value());
    }
}
