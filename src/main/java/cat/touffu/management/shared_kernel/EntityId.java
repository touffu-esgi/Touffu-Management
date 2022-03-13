package cat.touffu.management.shared_kernel;

import java.util.Objects;
import java.util.UUID;

public record EntityId(String value) {
    public EntityId {
        Objects.requireNonNull(value);
    }

    public static EntityId fromUUID(UUID uuid) {
        return new EntityId(Objects.requireNonNull(uuid).toString());
    }

    public static EntityId of(String value) {
        return new EntityId(value);
    }
}
