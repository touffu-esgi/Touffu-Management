package cat.touffu.management.components.cards.application.command.createCard;

import cat.touffu.management.components.cards.adapter.CardStatusFromStringAdapter;
import cat.touffu.management.components.cards.application.event.CardCreationDone;
import cat.touffu.management.components.cards.domain.Card;
import cat.touffu.management.components.cards.domain.CardRepository;
import cat.touffu.management.components.cards.domain.ProjectId;
import cat.touffu.management.components.projects.application.query.DoesProjectExists.DoesProjectExists;
import cat.touffu.management.kernel.command.CommandHandler;
import cat.touffu.management.kernel.event.ApplicationEvent;
import cat.touffu.management.kernel.event.EventBus;
import cat.touffu.management.kernel.exception.ProjectNotFoundException;
import cat.touffu.management.kernel.query.QueryBus;

public class AddCardInProjectHandler implements CommandHandler<AddCardInProject> {
    private final CardRepository cardRepository;
    private final QueryBus queryBus;
    private final EventBus<ApplicationEvent> applicationEventBus;
    private final CardStatusFromStringAdapter cardStatusFromStringAdapter = new CardStatusFromStringAdapter();

    public AddCardInProjectHandler(CardRepository cardRepository, QueryBus queryBus, EventBus<ApplicationEvent> applicationEventBus) {
        this.cardRepository = cardRepository;
        this.queryBus = queryBus;
        this.applicationEventBus = applicationEventBus;
    }

    @Override
    public void handle(AddCardInProject command) {
        Boolean projectExists = queryBus.request(new DoesProjectExists(command.projectId()));
        if(!projectExists) {
            throw new ProjectNotFoundException(command.projectId());
        }
        var card = Card.of(
                cardRepository.newId(),
                command.title(),
                ProjectId.of(command.projectId()),
                cardStatusFromStringAdapter.adapt(command.status())
        );
        cardRepository.save(card);
        applicationEventBus.send(CardCreationDone.of(
                command.title(),
                card.id(),
                card.projectId())
        );
    }
}
