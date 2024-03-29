package cat.touffu.management.components.projects.domain;

import cat.touffu.management.kernel.EntityId;

import java.util.Objects;
import java.util.UUID;

public class CardId implements EntityId<String> {
    private final String _value;

    private CardId(String value) {
        this._value = value;
    }

    public String value() {
        return this._value;
    }

    @Override
    public boolean equals(Object o) {
        EntityId<String> id = (EntityId<String>) o;
        return Objects.equals(this._value, id.value());
    }

    @Override
    public int hashCode() {
        return Objects.hash(_value);
    }

    public static CardId of(String id) {
        return new CardId(id);
    }

    public static CardId fromUUID(UUID uuid) {
        return new CardId(Objects.requireNonNull(uuid).toString());
    }

    @Override
    public String toString() {
        return "CardListId{" +
                "value='" + _value + '\'' +
                '}';
    }
}
