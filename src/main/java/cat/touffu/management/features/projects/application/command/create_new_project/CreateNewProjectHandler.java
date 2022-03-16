package cat.touffu.management.features.projects.application.command.create_new_project;


import cat.touffu.management.features.projects.domain.Project;
import cat.touffu.management.features.projects.domain.ProjectId;
import cat.touffu.management.features.projects.domain.ProjectRepository;
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
