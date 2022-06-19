package cat.touffu.management.components.cards;

import cat.touffu.management.components.cards.application.command.createCard.AddCardInProject;
import cat.touffu.management.components.cards.application.command.createCard.AddCardInProjectHandler;
import cat.touffu.management.components.cards.application.event.CardCreationDone;
import cat.touffu.management.components.cards.application.query.RetrieveProjects.RetrieveCardsInProject;
import cat.touffu.management.components.cards.application.query.RetrieveProjects.RetrieveCardsInProjectHandler;
import cat.touffu.management.components.cards.domain.CardRepository;
import cat.touffu.management.components.cards.infrastructure.SqliteCardRepository;
import cat.touffu.management.components.projects.ProjectModule;
import cat.touffu.management.components.projects.application.event.CardCreationDoneListener;
import cat.touffu.management.components.projects.application.query.DoesProjectExists.DoesProjectExists;
import cat.touffu.management.components.projects.application.query.DoesProjectExists.DoesProjectExistsHandler;
import cat.touffu.management.components.projects.application.query.RetrieveOneProject.RetrieveOneProjectHandler;
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
                List.of(new CardCreationDoneListener(ProjectModule.projectRepository()))
        );
        return new SimpleEventBus(mapEvent);
    }

    public static CommandBus commandBus() {
        final Map<Class<? extends Command>, CommandHandler> commandHandlerMap = new HashMap<>();
        commandHandlerMap.put(
                AddCardInProject.class,
                new AddCardInProjectHandler(CardsModule.cardsRepository(), CardsModule.queryBus(), CardsModule.applicationEventBus())
        );
        return new SimpleCommandBus(commandHandlerMap);
    }

    public static QueryBus queryBus() {
        final Map<Class<? extends Query>, QueryHandler> queryHandlerMap = new HashMap<>();
        queryHandlerMap.put(DoesProjectExists.class, new DoesProjectExistsHandler(ProjectModule.projectRepository()));
        queryHandlerMap.put(RetrieveCardsInProject.class, new RetrieveCardsInProjectHandler(CardsModule.cardsRepository()));
        return new SimpleQueryBus(queryHandlerMap);
    }


}
