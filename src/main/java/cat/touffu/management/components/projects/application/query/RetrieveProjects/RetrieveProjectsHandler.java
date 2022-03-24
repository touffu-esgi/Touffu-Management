package cat.touffu.management.components.projects.application.query.RetrieveProjects;

import cat.touffu.management.components.projects.domain.Project;
import cat.touffu.management.components.projects.domain.ProjectRepository;
import cat.touffu.management.kernel.query.QueryHandler;

import java.util.List;

public class RetrieveProjectsHandler implements QueryHandler<RetrieveProjects, List<Project>> {
    private final ProjectRepository projectRepository;

    public RetrieveProjectsHandler(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<Project> handle(RetrieveProjects command) {
        return projectRepository.findAll();
    }
}
