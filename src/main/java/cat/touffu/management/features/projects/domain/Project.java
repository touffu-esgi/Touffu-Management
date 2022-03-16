package cat.touffu.management.features.projects.domain;

import cat.touffu.management.shared_kernel.EntityId;
import cat.touffu.management.shared_kernel.NotEmptyString;

import java.util.Objects;

public record Project(
        ProjectId id,
        NotEmptyString title
) {
    public Project {
        Objects.requireNonNull(title);
    }


    public static Project of(ProjectId id, NotEmptyString title) {
        return new Project(id, title);
    }
}
