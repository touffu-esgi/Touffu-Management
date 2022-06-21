package cat.touffu.management.components.cards;

import cat.touffu.management.components.cards.application.command.createCard.AddCardInProject;
import cat.touffu.management.components.cards.application.command.createCard.AddCardInProjectHandler;
import cat.touffu.management.components.cards.application.command.updateCard.UpdateCard;
import cat.touffu.management.components.cards.application.command.updateCard.UpdateCardHandler;
import cat.touffu.management.components.cards.application.event.CardCreationDone;
import cat.touffu.management.components.cards.application.event.CardUpdateDone;
import cat.touffu.management.components.cards.application.query.RetrieveCardsInProject.RetrieveCardsInProject;
import cat.touffu.management.components.cards.application.query.RetrieveCardsInProject.RetrieveCardsInProjectHandler;
import cat.touffu.management.components.cards.application.query.RetrieveOneCard.RetrieveOneCard;
import cat.touffu.management.components.cards.application.query.RetrieveOneCard.RetrieveOneCardHandler;
import cat.touffu.management.components.cards.domain.CardRepository;
import cat.touffu.management.components.cards.infrastructure.SqliteCardRepository;
import cat.touffu.management.components.projects.ProjectModule;
import cat.touffu.management.components.projects.application.event.CardCreationDoneListener;
import cat.touffu.management.components.projects.application.query.DoesProjectExists.DoesProjectExists;
import cat.touffu.management.components.projects.application.query.DoesProjectExists.DoesProjectExistsHandler;
import cat.touffu.management.javafx.card.CardCreationDoneJavafxListener;
import cat.touffu.management.javafx.card.CardUpdatedJavafxListener;
import cat.touffu.management.kernel.Repository;
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
                List.of(
                        new CardCreationDoneListener(ProjectModule.projectRepository()),
                        new CardCreationDoneJavafxListener()
                )
        );
        mapEvent.put(CardUpdateDone.class, List.of(new CardUpdatedJavafxListener()));
        return new SimpleEventBus(mapEvent);
    }

    public static CommandBus commandBus() {
        final Map<Class<? extends Command>, CommandHandler> commandHandlerMap = new HashMap<>();
        CardRepository repository = CardsModule.cardsRepository();
        QueryBus queryBus = CardsModule.queryBus();
        EventBus<ApplicationEvent> eventBus = CardsModule.applicationEventBus();
        commandHandlerMap.put(AddCardInProject.class, new AddCardInProjectHandler(repository, queryBus, eventBus));
        commandHandlerMap.put(UpdateCard.class, new UpdateCardHandler(repository, eventBus));
        return new SimpleCommandBus(commandHandlerMap);
    }

    public static QueryBus queryBus() {
        final Map<Class<? extends Query>, QueryHandler> queryHandlerMap = new HashMap<>();
        queryHandlerMap.put(DoesProjectExists.class, new DoesProjectExistsHandler(ProjectModule.projectRepository()));
        queryHandlerMap.put(RetrieveCardsInProject.class, new RetrieveCardsInProjectHandler(CardsModule.cardsRepository()));
        queryHandlerMap.put(RetrieveOneCard.class, new RetrieveOneCardHandler(CardsModule.cardsRepository()));
        return new SimpleQueryBus(queryHandlerMap);
    }


}
