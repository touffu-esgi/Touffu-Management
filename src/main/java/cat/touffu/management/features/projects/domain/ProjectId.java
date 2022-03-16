package cat.touffu.management.features.projects.domain;

import cat.touffu.management.shared_kernel.EntityId;

import java.util.Objects;
import java.util.UUID;

public class ProjectId implements EntityId{

    private final String _value;

    private ProjectId(String value) {
        this._value = value;
    }

    @Override
    public String value() {
        return this._value;
    }

    public static ProjectId of(String id) {
        return new ProjectId(id);
    }

    public static ProjectId fromUUID(UUID uuid) {
        return new ProjectId(Objects.requireNonNull(uuid).toString());
    }
}
