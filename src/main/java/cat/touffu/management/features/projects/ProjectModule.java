package cat.touffu.management.features.projects;

import cat.touffu.management.features.projects.application.command.create_new_project.CreateNewProject;
import cat.touffu.management.features.projects.application.command.create_new_project.CreateNewProjectHandler;
import cat.touffu.management.features.projects.domain.ProjectRepository;
import cat.touffu.management.features.projects.infrastructure.InMemoryProjectRepository;
import cat.touffu.management.kernel.command.Command;
import cat.touffu.management.kernel.command.CommandBus;
import cat.touffu.management.kernel.command.CommandHandler;
import cat.touffu.management.kernel.command.SimpleCommandBus;

import javax.enterprise.context.Dependent;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

public class ProjectModule {

    @Singleton
    public static ProjectRepository projectRepository() {
        return InMemoryProjectRepository.getInstance();
    }

    @Dependent
    public static CommandBus commandBus() {
        final Map<Class<? extends Command>, CommandHandler> commandHandlerMap = new HashMap<>();
        commandHandlerMap.put(CreateNewProject.class, new CreateNewProjectHandler(ProjectModule.projectRepository()));
        return new SimpleCommandBus(commandHandlerMap);
    }
}
