package cat.touffu.management.components.projects.application.command.CreateNewProject;


import cat.touffu.management.components.projects.domain.CardId;
import cat.touffu.management.components.projects.domain.Project;
import cat.touffu.management.components.projects.domain.ProjectRepository;
import cat.touffu.management.kernel.command.CommandHandler;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CreateNewProjectHandler implements CommandHandler<CreateNewProject> {

    private final ProjectRepository projectRepository;

    public CreateNewProjectHandler(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public void handle(CreateNewProject command) {
        Set<CardId> emptyCardId = Collections.emptySet();
        final Project project = Project.of(
                projectRepository.newId(),
                command.title(),
                new HashSet<>()
        );
        projectRepository.save(project);
    }
}
