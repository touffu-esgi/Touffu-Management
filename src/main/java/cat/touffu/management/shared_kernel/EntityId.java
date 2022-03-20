package cat.touffu.management.shared_kernel;

import java.util.Objects;
import java.util.UUID;

public class EntityId{
    private final String _value;
    protected EntityId(String value)  {
        this._value = Objects.requireNonNull(value);
    }

    public static EntityId fromUUID(UUID uuid) {
        return new EntityId(Objects.requireNonNull(uuid).toString());
    }

    public static EntityId of(String value) {
        return new EntityId(value);
    }

    public String value() {
        return this._value;
    }
}
