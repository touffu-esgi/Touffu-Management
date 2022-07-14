package cat.touffu.management.components.projects.application.query.RetrieveOneProject;

import cat.touffu.management.components.projects.domain.Project;
import cat.touffu.management.components.projects.domain.ProjectId;
import cat.touffu.management.components.projects.domain.ProjectRepository;
import cat.touffu.management.kernel.query.QueryHandler;

import java.util.List;

public class RetrieveOneProjectHandler implements QueryHandler<RetrieveOneProject, Project> {
    private final ProjectRepository projectRepository;

    public RetrieveOneProjectHandler(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Project handle(RetrieveOneProject command) {
        return projectRepository.findById(ProjectId.of(command.id()));
    }
}
