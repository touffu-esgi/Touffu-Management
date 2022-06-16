package cat.touffu.management.components.projects.application.command.CreateNewProject;


import cat.touffu.management.components.projects.application.event.ProjectCreationDone;
import cat.touffu.management.components.projects.domain.CardId;
import cat.touffu.management.components.projects.domain.Project;
import cat.touffu.management.components.projects.domain.ProjectId;
import cat.touffu.management.components.projects.domain.ProjectRepository;
import cat.touffu.management.kernel.command.CommandHandler;
import cat.touffu.management.kernel.event.EventBus;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CreateNewProjectHandler implements CommandHandler<CreateNewProject> {

    private final ProjectRepository projectRepository;
    private final EventBus eventBus;

    public CreateNewProjectHandler(ProjectRepository projectRepository, EventBus eventBus) {
        this.projectRepository = projectRepository;
        this.eventBus = eventBus;
    }

    @Override
    public void handle(CreateNewProject command) {
        Set<CardId> emptyCardId = Collections.emptySet();
        final var project = Project.of(
                projectRepository.newId(),
                command.title(),
                new HashSet<>()
        );
        //projectRepository.save(project);
        this.eventBus.send(ProjectCreationDone.of(
                project.id(),
                project.title()
        ));
    }
}
