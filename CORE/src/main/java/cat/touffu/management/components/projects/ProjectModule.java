package cat.touffu.management.components.projects;

import cat.touffu.management.components.projects.application.command.CreateNewProject.CreateNewProject;
import cat.touffu.management.components.projects.application.command.CreateNewProject.CreateNewProjectHandler;
import cat.touffu.management.components.projects.application.event.ProjectCreationDone;
import cat.touffu.management.components.projects.application.query.DoesProjectExists.DoesProjectExists;
import cat.touffu.management.components.projects.application.query.DoesProjectExists.DoesProjectExistsHandler;
import cat.touffu.management.components.projects.application.query.RetrieveOneProject.RetrieveOneProject;
import cat.touffu.management.components.projects.application.query.RetrieveOneProject.RetrieveOneProjectHandler;
import cat.touffu.management.components.projects.application.query.RetrieveProjects.RetrieveProjects;
import cat.touffu.management.components.projects.application.query.RetrieveProjects.RetrieveProjectsHandler;
import cat.touffu.management.components.projects.domain.ProjectRepository;
import cat.touffu.management.components.projects.infrastructure.RestApiProjectRepository;
import cat.touffu.management.components.projects.infrastructure.SqliteProjectRepository;
import cat.touffu.management.javafx.projects.ProjectCreationDoneListener;
import cat.touffu.management.kernel.command.Command;
import cat.touffu.management.kernel.command.CommandBus;
import cat.touffu.management.kernel.command.CommandHandler;
import cat.touffu.management.kernel.command.SimpleCommandBus;
import cat.touffu.management.kernel.event.*;
import cat.touffu.management.kernel.query.Query;
import cat.touffu.management.kernel.query.QueryBus;
import cat.touffu.management.kernel.query.QueryHandler;
import cat.touffu.management.kernel.query.SimpleQueryBus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectModule {

    public static ProjectRepository projectRepository() {
        return RestApiProjectRepository.getInstance();
    }
    private static QueryBus queryBus;

    public static CommandBus commandBus() {
        final Map<Class<? extends Command>, CommandHandler> commandHandlerMap = new HashMap<>();
        ProjectRepository repository = ProjectModule.projectRepository();
        commandHandlerMap.put(CreateNewProject.class, new CreateNewProjectHandler(repository, ProjectModule.eventBus()));
        return new SimpleCommandBus(commandHandlerMap);
    }

    public static QueryBus queryBus() {
        if(queryBus != null) return queryBus;
        final Map<Class<? extends Query>, QueryHandler> queryHandlerMap = new HashMap<>();
        ProjectRepository repository = ProjectModule.projectRepository();
        queryHandlerMap.put(RetrieveProjects.class, new RetrieveProjectsHandler(repository));
        queryHandlerMap.put(RetrieveOneProject.class, new RetrieveOneProjectHandler(repository));
        queryHandlerMap.put(DoesProjectExists.class, new DoesProjectExistsHandler(repository));
        queryBus = new SimpleQueryBus(queryHandlerMap);
        return queryBus;
    }

    public static EventBus eventBus() {
        Map<Class<? extends Event>, List<Subscriber<? extends Event>>> mapEvent = new HashMap<>();
        mapEvent.put(
                ProjectCreationDone.class,
                List.of(new ProjectCreationDoneListener(ProjectModule.projectRepository()))
        );
        return new SimpleEventBus(mapEvent);
    }
}
