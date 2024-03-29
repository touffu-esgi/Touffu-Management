package cat.touffu.management.components.projects.domain;

import cat.touffu.management.kernel.Repository;

import java.util.UUID;

public interface ProjectRepository extends Repository<Project, ProjectId> {
    default ProjectId newId() {
        return ProjectId.fromUUID(UUID.randomUUID());
    }
}
