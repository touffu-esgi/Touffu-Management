package cat.touffu.management.components.cards.domain;

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

    @Override
    public boolean equals(EntityId<String> id) {
        return Objects.equals(this._value, id.value());
    }

    public static ListId of(String id) {
        return new ListId(Objects.requireNonNull(id));
    }

    public static ListId fromUUID(UUID uuid) {
        return new ListId(Objects.requireNonNull(uuid).toString());
    }
}
