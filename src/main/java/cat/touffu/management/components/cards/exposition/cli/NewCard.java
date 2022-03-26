package cat.touffu.management.components.cards.exposition.cli;

import cat.touffu.management.components.cards.application.command.createCardInList.CreateCardInList;
import cat.touffu.management.components.list.ListModule;
import cat.touffu.management.components.list.application.command.CreateNewListInProject.CreateNewListOfCardsInProject;
import cat.touffu.management.components.list.domain.ListId;
import cat.touffu.management.kernel.command.CommandBus;
import picocli.CommandLine;

@CommandLine.Command(
        name = "new-card",
        description = "Create a new card in a list."
)
public class NewCard implements Runnable{
    @CommandLine.Parameters(index = "0", paramLabel = "title", description = "Card title")
    String title;

    @CommandLine.Parameters(index = "1", paramLabel = "list id", description = "Id of a list")
    String list_id;

    CommandBus commandBus = ListModule.commandBus();

    @Override
    public void run() {
        CreateCardInList command = CreateCardInList.of(title, list_id);
        this.commandBus.send(command);
        System.out.println("New card '" + this.title + "' have been created.");
    }
}
