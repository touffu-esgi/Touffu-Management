package cat.touffu.management.components.list;

import cat.touffu.management.components.list.application.command.CreateNewListInProject.CreateNewListOfCardsHandler;
import cat.touffu.management.components.list.domain.ListRepository;
import cat.touffu.management.components.list.infrastructure.SqliteListRepository;
import cat.touffu.management.kernel.command.Command;
import cat.touffu.management.kernel.command.CommandBus;
import cat.touffu.management.kernel.command.CommandHandler;
import cat.touffu.management.kernel.command.SimpleCommandBus;
import cat.touffu.management.components.list.application.command.CreateNewListInProject.CreateNewListOfCardsInProject;

import javax.enterprise.context.Dependent;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

public class ListModule {

    @Singleton
    public static ListRepository listRepository() {
        return SqliteListRepository.getInstance();
    }

    @Dependent
    public static CommandBus commandBus() {
        final Map<Class<? extends Command>, CommandHandler> commandHandlerMap = new HashMap<>();
        commandHandlerMap.put(CreateNewListOfCardsInProject.class, new CreateNewListOfCardsHandler(ListModule.listRepository()));
        return new SimpleCommandBus(commandHandlerMap);
    }
}
