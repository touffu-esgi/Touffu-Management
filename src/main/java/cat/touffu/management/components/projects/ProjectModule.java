package cat.touffu.management.components.projects;

import cat.touffu.management.components.projects.application.command.CreateNewProject.CreateNewProject;
import cat.touffu.management.components.projects.application.command.CreateNewProject.CreateNewProjectHandler;
import cat.touffu.management.components.projects.application.query.DoesProjectExists.DoesProjectExists;
import cat.touffu.management.components.projects.application.query.DoesProjectExists.DoesProjectExistsHandler;
import cat.touffu.management.components.projects.application.query.RetrieveProjects.RetrieveProjects;
import cat.touffu.management.components.projects.application.query.RetrieveProjects.RetrieveProjectsHandler;
import cat.touffu.management.components.projects.domain.ProjectRepository;
import cat.touffu.management.components.projects.infrastructure.SqliteProjectRepository;
import cat.touffu.management.kernel.command.Command;
import cat.touffu.management.kernel.command.CommandBus;
import cat.touffu.management.kernel.command.CommandHandler;
import cat.touffu.management.kernel.command.SimpleCommandBus;
import cat.touffu.management.kernel.query.Query;
import cat.touffu.management.kernel.query.QueryBus;
import cat.touffu.management.kernel.query.QueryHandler;
import cat.touffu.management.kernel.query.SimpleQueryBus;

import java.util.HashMap;
import java.util.Map;

public class ProjectModule {

    public static ProjectRepository projectRepository() {
        return SqliteProjectRepository.getInstance();
    }

    public static CommandBus commandBus() {
        final Map<Class<? extends Command>, CommandHandler> commandHandlerMap = new HashMap<>();
        ProjectRepository repository = ProjectModule.projectRepository();
        commandHandlerMap.put(CreateNewProject.class, new CreateNewProjectHandler(repository));
        return new SimpleCommandBus(commandHandlerMap);
    }

    public static QueryBus queryBus() {
        final Map<Class<? extends Query>, QueryHandler> queryHandlerMap = new HashMap<>();
        ProjectRepository repository = ProjectModule.projectRepository();
        queryHandlerMap.put(RetrieveProjects.class, new RetrieveProjectsHandler(repository));
        queryHandlerMap.put(DoesProjectExists.class, new DoesProjectExistsHandler(repository));
        return new SimpleQueryBus(queryHandlerMap);
    }
}
