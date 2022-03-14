package cat.touffu.management.features.projects.domain;

import cat.touffu.management.shared_kernel.EntityId;
import cat.touffu.management.shared_kernel.NotEmptyString;

import java.util.Objects;

public record Project(
        EntityId projectId,
        NotEmptyString projectTitle
) {
    public Project {
        Objects.requireNonNull(projectTitle);
    }
}
