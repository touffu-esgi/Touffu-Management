package cat.touffu.management.components.cards.application.event;

import cat.touffu.management.kernel.event.ApplicationEvent;
import cat.touffu.management.kernel.event.EventId;
import cat.touffu.management.shared_kernel.EntityId;

import java.time.ZonedDateTime;

public record CardCreationDone(
        EventId eventId,
        ZonedDateTime occurredDate,
        String cardTitle,
        EntityId<String> cardId,
        EntityId<String> listId
) implements ApplicationEvent {

    public static CardCreationDone of(String cardTitle, EntityId<String> cardId, EntityId<String> listId) {
        return new CardCreationDone(
                EventId.create(),
                ZonedDateTime.now(),
                cardTitle,
                cardId,
                listId
        );
    }
}
