package cat.touffu.management.components.list.application.event;

import cat.touffu.management.components.cards.application.event.CardCreationDone;
import cat.touffu.management.components.list.application.command.CreateNewListOfCard.MockListRepository;
import cat.touffu.management.components.list.domain.CardId;
import cat.touffu.management.components.list.domain.ListId;
import cat.touffu.management.components.list.domain.ListOfCard;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class CardCreationDoneListenerTest {
    @Test
    void shouldSaveNewCardInList() {
        MockListRepository repository = new MockListRepository();
        ListOfCard list = ListOfCard.of(ListId.of("listId"), "list title", "projectId", new HashSet<CardId>());
        repository.store.put(list.id().value(), list);
        CardCreationDoneListener listener = new CardCreationDoneListener(repository);

        CardId cardId = CardId.of("cardId");
        CardCreationDone event = CardCreationDone.of(
                "card title",
                cardId,
                list.id()
        );
        listener.accept(event);
        assertTrue(repository.store.get("listId").cardIds().iterator().next().equals(cardId));
    }

}