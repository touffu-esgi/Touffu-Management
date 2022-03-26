package cat.touffu.management.components.cards.application.command.createCardInList;

import cat.touffu.management.components.cards.domain.Card;
import cat.touffu.management.components.cards.domain.CardId;
import cat.touffu.management.components.cards.domain.CardRepository;
import cat.touffu.management.components.cards.domain.ListId;
import cat.touffu.management.components.cards.application.event.CardCreationDone;
import cat.touffu.management.components.list.application.query.DoesListExists.DoesListExists;
import cat.touffu.management.kernel.command.CommandHandler;
import cat.touffu.management.kernel.event.ApplicationEvent;
import cat.touffu.management.kernel.event.EventBus;
import cat.touffu.management.kernel.exception.NotFoundException;
import cat.touffu.management.kernel.query.QueryBus;

public class CreateCardInListHandler implements CommandHandler<CreateCardInList, Void> {
    private final CardRepository cardRepository;
    private final QueryBus queryBus;
    private final EventBus<ApplicationEvent> applicationEventBus;

    public CreateCardInListHandler(CardRepository cardRepository, QueryBus queryBus, EventBus<ApplicationEvent> applicationEventBus) {
        this.cardRepository = cardRepository;
        this.queryBus = queryBus;
        this.applicationEventBus = applicationEventBus;
    }

    @Override
    public Void handle(CreateCardInList command) {
        Boolean listExists = queryBus.send(new DoesListExists(command.listId()));
        if(!listExists) {
            throw new NotFoundException("List " + command.listId());
        }
        CardId cardId = cardRepository.newId();
        ListId listId = ListId.of(command.listId());
        cardRepository.save(Card.of(
                cardId,
                command.title(),
                listId
                )
        );
        applicationEventBus.send(CardCreationDone.of(command.title(), cardId, listId));
        return null;
    }
}
