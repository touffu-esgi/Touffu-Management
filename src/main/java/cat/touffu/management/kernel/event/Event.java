package cat.touffu.management.kernel.event;

import java.time.ZonedDateTime;

public interface Event {
    EventId eventId();
    ZonedDateTime occurredDate();
}
