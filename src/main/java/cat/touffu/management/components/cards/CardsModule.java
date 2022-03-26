package cat.touffu.management.components.cards;

import cat.touffu.management.components.cards.application.command.createCardInList.CreateCardInList;
import cat.touffu.management.components.cards.application.command.createCardInList.CreateCardInListHandler;
import cat.touffu.management.components.cards.domain.CardRepository;
import cat.touffu.management.components.cards.infrastructure.SqliteCardRepository;
import cat.touffu.management.kernel.command.Command;
import cat.touffu.management.kernel.command.CommandBus;
import cat.touffu.management.kernel.command.CommandHandler;
import cat.touffu.management.kernel.command.SimpleCommandBus;
import cat.touffu.management.kernel.event.*;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardsModule {

    @Singleton
    public static CardRepository CardsRepository() {
        return SqliteCardRepository.getInstance();
    }

    public static EventBus<ApplicationEvent> applicationEventBus() {
        Map<Class<? extends Event>, List<Subscriber<? extends Event>>> mapEvent = new HashMap<>();
        return new SimpleEventBus(mapEvent);
    }

    public static CommandBus commandBus() {
        final Map<Class<? extends Command>, CommandHandler> commandHandlerMap = new HashMap<>();
        commandHandlerMap.put(
                CreateCardInList.class,
                new CreateCardInListHandler(CardsModule.CardsRepository())
        );
        return new SimpleCommandBus(commandHandlerMap);
    }


}
