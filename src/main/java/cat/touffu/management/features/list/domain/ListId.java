package cat.touffu.management.features.list.domain;

import java.util.Objects;
import java.util.UUID;

public final class ListId {

    private final String _value;

    private ListId(String value) {
        this._value = value;
    }

    public String value() {
        return this._value;
    }

    public static ListId of(String id) {
        return new ListId(id);
    }

    public static ListId fromUUID(UUID uuid) {
        return new ListId(Objects.requireNonNull(uuid).toString());
    }
}
