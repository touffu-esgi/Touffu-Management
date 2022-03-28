package cat.touffu.management.components.projects.domain;

import cat.touffu.management.shared_kernel.EntityId;

import java.util.Objects;
import java.util.UUID;

public class CardListId implements EntityId<String> {
    private final String _value;

    private CardListId(String value) {
        this._value = value;
    }

    public String value() {
        return this._value;
    }

    @Override
    public boolean equals(EntityId<String> id) {
        return Objects.equals(this._value, id.value());
    }

    public static CardListId of(String id) {
        return new CardListId(id);
    }

    public static CardListId fromUUID(UUID uuid) {
        return new CardListId(Objects.requireNonNull(uuid).toString());
    }

    @Override
    public String toString() {
        return "CardListId{" +
                "value='" + _value + '\'' +
                '}';
    }
}
