package cat.touffu.management.kernel.event;

import java.util.UUID;

public class EventId {
    private final String _value;

    private EventId(String value) {
        this._value = value;
    }

    public static EventId create() {
        return new EventId(UUID.randomUUID().toString());
    }

    public String value() {
        return this._value;
    }
}
