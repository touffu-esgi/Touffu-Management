package cat.touffu.management.components.cards.exposition.cli;

import cat.touffu.management.components.cards.CardsModule;
import cat.touffu.management.components.cards.application.command.createCard.AddCardInProject;
import cat.touffu.management.kernel.command.CommandBus;
import picocli.CommandLine;

@CommandLine.Command(
        name = "new-card",
        description = "Create a new card in a list."
)
public class NewCard implements Runnable{
    @CommandLine.Parameters(index = "0", paramLabel = "title", description = "Card title")
    String title;

    @CommandLine.Parameters(index = "1", paramLabel = "list-id", description = "Id of a project")
    String project_id;

    @CommandLine.Parameters(index = "2", paramLabel = "status", description = "Card status")
    String status;

    CommandBus commandBus = CardsModule.commandBus();

    @Override
    public void run() {
        AddCardInProject command = AddCardInProject.of(title, project_id, status);
        this.commandBus.setExceptionFilter(new CardCliExceptionFilter());
        this.commandBus.send(command);
    }
}
