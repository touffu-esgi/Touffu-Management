package cat.touffu.management.components.projects.application.command.CreateNewProject;


import cat.touffu.management.components.projects.domain.Project;
import cat.touffu.management.components.projects.domain.ProjectId;
import cat.touffu.management.components.projects.domain.ProjectRepository;
import cat.touffu.management.kernel.command.CommandHandler;

public class CreateNewProjectHandler implements CommandHandler<CreateNewProject, ProjectId> {

    private final ProjectRepository projectRepository;

    public CreateNewProjectHandler(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public ProjectId handle(CreateNewProject command) {
        final Project project = new Project(
                projectRepository.nextId(),
                command.title()
        );
        projectRepository.save(project);
        return project.id();
    }
}
