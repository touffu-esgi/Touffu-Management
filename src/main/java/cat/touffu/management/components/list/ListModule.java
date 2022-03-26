package cat.touffu.management.components.list;

import cat.touffu.management.components.list.application.command.CreateNewListInProject.CreateNewListOfCardsHandler;
import cat.touffu.management.components.list.application.command.CreateNewListInProject.CreateNewListOfCardsInProject;
import cat.touffu.management.components.list.domain.ListRepository;
import cat.touffu.management.components.list.domain.event.ListOfCardCreated;
import cat.touffu.management.components.list.infrastructure.SqliteListRepository;
import cat.touffu.management.components.projects.ProjectModule;
import cat.touffu.management.components.projects.application.event.ListOfCardCreatedListener;
import cat.touffu.management.kernel.command.Command;
import cat.touffu.management.kernel.command.CommandBus;
import cat.touffu.management.kernel.command.CommandHandler;
import cat.touffu.management.kernel.command.SimpleCommandBus;
import cat.touffu.management.kernel.event.*;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListModule {

    @Singleton
    public static ListRepository listRepository() {
        return SqliteListRepository.getInstance();
    }

    public static EventBus<ApplicationEvent> applicationEventBus() {
        Map<Class<? extends Event>, List<Subscriber<? extends Event>>> mapEvent = new HashMap<>();
        mapEvent.put(
                ListOfCardCreated.class,
                List.of(new ListOfCardCreatedListener(ProjectModule.projectRepository()))
        );
        return new SimpleEventBus(mapEvent);
    }

    public static CommandBus commandBus() {
        final Map<Class<? extends Command>, CommandHandler> commandHandlerMap = new HashMap<>();
        commandHandlerMap.put(
                CreateNewListOfCardsInProject.class,
                new CreateNewListOfCardsHandler(ListModule.listRepository(), ListModule.applicationEventBus())
        );
        return new SimpleCommandBus(commandHandlerMap);
    }


}