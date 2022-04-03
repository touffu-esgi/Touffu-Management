package cat.touffu.management.components.projects.application.query.DoesProjectExists;

import cat.touffu.management.components.projects.domain.Project;
import cat.touffu.management.components.projects.domain.ProjectId;
import cat.touffu.management.components.projects.domain.ProjectRepository;
import cat.touffu.management.kernel.query.QueryHandler;

public class DoesProjectExistsHandler implements QueryHandler<DoesProjectExists, Boolean> {
    private final ProjectRepository projectRepository;

    public DoesProjectExistsHandler(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Boolean handle(DoesProjectExists query) {
        Project project = projectRepository.findById(ProjectId.of(query.projectId()));
        return project != null;
    }
}
