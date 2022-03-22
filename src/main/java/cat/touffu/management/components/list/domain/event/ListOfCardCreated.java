package cat.touffu.management.components.list.domain.event;

import cat.touffu.management.kernel.event.DomainEvent;
import cat.touffu.management.kernel.event.EventId;
import cat.touffu.management.shared_kernel.EntityId;

import java.time.ZonedDateTime;

public record ListOfCardCreated(
        EventId eventId,
        ZonedDateTime occurredDate,
        EntityId<String> listId,
        EntityId<String> projectId
) implements DomainEvent {

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
