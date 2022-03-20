package cat.touffu.management.components.projects;

import cat.touffu.management.components.projects.application.command.CreateNewProject.CreateNewProject;
import cat.touffu.management.components.projects.application.command.CreateNewProject.CreateNewProjectHandler;
import cat.touffu.management.components.projects.domain.ProjectRepository;
import cat.touffu.management.components.projects.infrastructure.SqliteProjectRepository;
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
        return SqliteProjectRepository.getInstance();
    }

    @Dependent
    public static CommandBus commandBus() {
        final Map<Class<? extends Command>, CommandHandler> commandHandlerMap = new HashMap<>();
        commandHandlerMap.put(CreateNewProject.class, new CreateNewProjectHandler(ProjectModule.projectRepository()));
        return new SimpleCommandBus(commandHandlerMap);
    }
}
