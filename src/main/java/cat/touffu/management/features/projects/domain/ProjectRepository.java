package cat.touffu.management.features.projects.domain;

import cat.touffu.management.kernel.Repository;

import java.util.UUID;

public interface ProjectRepository extends Repository<Project, ProjectId> {
    default ProjectId nextId() {
        return ProjectId.fromUUID(UUID.randomUUID());
    }
}
