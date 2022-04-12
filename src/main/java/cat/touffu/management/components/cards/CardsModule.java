package cat.touffu.management.components.cards;

import cat.touffu.management.components.cards.application.command.createCardInList.CreateCardInList;
import cat.touffu.management.components.cards.application.command.createCardInList.CreateCardInListHandler;
import cat.touffu.management.components.cards.application.event.CardCreationDone;
import cat.touffu.management.components.cards.domain.CardRepository;
import cat.touffu.management.components.cards.infrastructure.SqliteCardRepository;
import cat.touffu.management.components.list.ListModule;
import cat.touffu.management.components.list.application.event.CardCreationDoneListener;
import cat.touffu.management.components.list.application.query.DoesListExists.DoesListExists;
import cat.touffu.management.components.list.application.query.DoesListExists.DoesListExistsHandler;
import cat.touffu.management.components.list.domain.ListRepository;
import cat.touffu.management.kernel.command.Command;
import cat.touffu.management.kernel.command.CommandBus;
import cat.touffu.management.kernel.command.CommandHandler;
import cat.touffu.management.kernel.command.SimpleCommandBus;
import cat.touffu.management.kernel.event.*;
import cat.touffu.management.kernel.query.Query;
import cat.touffu.management.kernel.query.QueryBus;
import cat.touffu.management.kernel.query.QueryHandler;
import cat.touffu.management.kernel.query.SimpleQueryBus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardsModule {


    public static CardRepository cardsRepository() {
        return SqliteCardRepository.getInstance();
    }

    public static EventBus<ApplicationEvent> applicationEventBus() {
        Map<Class<? extends Event>, List<Subscriber<? extends Event>>> mapEvent = new HashMap<>();
        mapEvent.put(
                CardCreationDone.class,
                List.of(new CardCreationDoneListener(ListModule.listRepository()))
        );
        return new SimpleEventBus(mapEvent);
    }

    public static CommandBus commandBus() {
        final Map<Class<? extends Command>, CommandHandler> commandHandlerMap = new HashMap<>();
        commandHandlerMap.put(
                CreateCardInList.class,
                new CreateCardInListHandler(CardsModule.cardsRepository(), CardsModule.queryBus(), CardsModule.applicationEventBus())
        );
        return new SimpleCommandBus(commandHandlerMap);
    }

    public static QueryBus queryBus() {
        final Map<Class<? extends Query>, QueryHandler> queryHandlerMap = new HashMap<>();
        ListRepository listRepository = ListModule.listRepository();
        queryHandlerMap.put(DoesListExists.class, new DoesListExistsHandler(listRepository));
        return new SimpleQueryBus(queryHandlerMap);
    }


}
