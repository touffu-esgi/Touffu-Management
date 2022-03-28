package cat.touffu.management.components.cards.exposition.cli;

import cat.touffu.management.components.cards.CardsModule;
import cat.touffu.management.components.cards.application.command.createCardInList.CreateCardInList;
import cat.touffu.management.kernel.command.CommandBus;
import picocli.CommandLine;

@CommandLine.Command(
        name = "new-card",
        description = "Create a new card in a list."
)
public class NewCard implements Runnable{
    @CommandLine.Parameters(index = "0", paramLabel = "title", description = "Card title")
    String title;

    @CommandLine.Parameters(index = "1", paramLabel = "list-id", description = "Id of a list")
    String list_id;

    CommandBus commandBus = CardsModule.commandBus();

    @Override
    public void run() {
        CreateCardInList command = CreateCardInList.of(title, list_id);
        this.commandBus.setExceptionFilter(new CardCliExceptionFilter());
        this.commandBus.send(command);
    }
}