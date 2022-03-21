package cat.touffu.management.components.projects.domain;

import java.util.Objects;
import java.util.UUID;

public class CardListId {
    private final String _value;

    private CardListId(String value) {
        this._value = value;
    }

    public String value() {
        return this._value;
    }

    public static CardListId of(String id) {
        return new CardListId(id);
    }

    public static CardListId fromUUID(UUID uuid) {
        return new CardListId(Objects.requireNonNull(uuid).toString());
    }
}
