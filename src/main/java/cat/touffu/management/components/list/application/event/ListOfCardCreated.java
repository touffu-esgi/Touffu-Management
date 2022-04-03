package cat.touffu.management.components.list.application.event;

import cat.touffu.management.kernel.EntityId;
import cat.touffu.management.kernel.event.ApplicationEvent;
import cat.touffu.management.kernel.event.EventId;

import java.time.ZonedDateTime;

public record ListOfCardCreated(
        EventId eventId,
        ZonedDateTime occurredDate,
        EntityId<String> listId,
        EntityId<String> projectId
) implements ApplicationEvent {

    public static ListOfCardCreated of(
            EntityId<String> listId,
            EntityId<String> projectId
    ) {
        return new ListOfCardCreated(
                EventId.create(),
                ZonedDateTime.now(),
                listId,
                projectId
        );
    }
}
