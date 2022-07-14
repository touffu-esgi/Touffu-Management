package cat.touffu.management.components.cards.application.event;

import cat.touffu.management.kernel.EntityId;
import cat.touffu.management.kernel.event.ApplicationEvent;
import cat.touffu.management.kernel.event.EventId;

import java.time.ZonedDateTime;

public record CardUpdateDone(
        EventId eventId,
        ZonedDateTime occurredDate,
        String cardId,
        String title,
        String status
) implements ApplicationEvent {

    public static CardUpdateDone of(String title, String cardId, String status) {
        return new CardUpdateDone(
                EventId.create(),
                ZonedDateTime.now(),
                cardId,
                title,
                status
        );
    }
}
