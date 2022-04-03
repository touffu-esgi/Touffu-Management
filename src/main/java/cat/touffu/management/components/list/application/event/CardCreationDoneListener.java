package cat.touffu.management.components.list.application.event;

import cat.touffu.management.components.cards.application.event.CardCreationDone;
import cat.touffu.management.components.list.domain.CardId;
import cat.touffu.management.components.list.domain.ListId;
import cat.touffu.management.components.list.domain.ListOfCard;
import cat.touffu.management.components.list.domain.ListRepository;
import cat.touffu.management.kernel.event.Subscriber;

public record CardCreationDoneListener(
        ListRepository repository
) implements Subscriber<CardCreationDone> {
    @Override
    public void accept(CardCreationDone event) {
        ListId listId = ListId.of(event.listId().value());
        ListOfCard list = repository.findById(listId);

        list.addCard(CardId.of(event.cardId().value()));
        repository.save(list);
    }
}
