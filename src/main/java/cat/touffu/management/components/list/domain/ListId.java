package cat.touffu.management.components.list.domain;

import cat.touffu.management.shared_kernel.EntityId;

import java.util.Objects;
import java.util.UUID;

public final class ListId implements EntityId<String> {

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
