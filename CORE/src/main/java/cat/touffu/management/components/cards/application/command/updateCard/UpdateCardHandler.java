package cat.touffu.management.components.cards.application.command.updateCard;

import cat.touffu.management.components.cards.adapter.CardStatusFromStringAdapter;
import cat.touffu.management.components.cards.adapter.CardStatusToStringAdapter;
import cat.touffu.management.components.cards.application.command.createCard.AddCardInProject;
import cat.touffu.management.components.cards.application.event.CardCreationDone;
import cat.touffu.management.components.cards.application.event.CardUpdateDone;
import cat.touffu.management.components.cards.domain.Card;
import cat.touffu.management.components.cards.domain.CardId;
import cat.touffu.management.components.cards.domain.CardRepository;
import cat.touffu.management.components.cards.domain.ProjectId;
import cat.touffu.management.components.projects.application.query.DoesProjectExists.DoesProjectExists;
import cat.touffu.management.kernel.command.CommandHandler;
import cat.touffu.management.kernel.event.ApplicationEvent;
import cat.touffu.management.kernel.event.EventBus;
import cat.touffu.management.kernel.exception.NotFoundException;
import cat.touffu.management.kernel.exception.ProjectNotFoundException;
import cat.touffu.management.kernel.query.QueryBus;

public class UpdateCardHandler implements CommandHandler<UpdateCard> {
    private final CardRepository cardRepository;
    private final EventBus<ApplicationEvent> applicationEventBus;
    private final CardStatusFromStringAdapter cardStatusFromStringAdapter = new CardStatusFromStringAdapter();
    private final CardStatusToStringAdapter cardStatusToStringAdapter = new CardStatusToStringAdapter();

    public UpdateCardHandler(CardRepository cardRepository, EventBus<ApplicationEvent> applicationEventBus) {
        this.cardRepository = cardRepository;
        this.applicationEventBus = applicationEventBus;
    }

    @Override
    public void handle(UpdateCard command) {
        var old = cardRepository.findById(CardId.of(command.id()));
        var cardExists = old != null;
        if(!cardExists) throw new NotFoundException(command.id());

        var card = Card.of(
                old.id(),
                command.title(),
                old.projectId(),
                cardStatusFromStringAdapter.adapt(command.status())
        );
        cardRepository.save(card);
        applicationEventBus.send(CardUpdateDone.of(
                command.title(),
                card.id().value(),
                cardStatusToStringAdapter.adapt(card.cardStatus())
                )
        );
    }


}
