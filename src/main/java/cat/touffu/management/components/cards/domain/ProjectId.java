package cat.touffu.management.components.cards.domain;

import cat.touffu.management.kernel.EntityId;

import java.util.Objects;
import java.util.UUID;

public final class ProjectId implements EntityId<String> {

    private final String _value;

    private ProjectId(String value) {
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

    public static ProjectId of(String id) {
        return new ProjectId(Objects.requireNonNull(id));
    }
}
