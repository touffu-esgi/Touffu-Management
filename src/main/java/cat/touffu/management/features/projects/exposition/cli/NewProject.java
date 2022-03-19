package cat.touffu.management.features.projects.exposition.cli;

import cat.touffu.management.features.projects.application.command.create_new_project.CreateNewProject;
import cat.touffu.management.features.projects.domain.ProjectId;
import cat.touffu.management.kernel.command.CommandBus;
import com.google.inject.Inject;
import picocli.CommandLine;

@CommandLine.Command(
        name = "new-project",
        description = "Create a new project."
)
public class NewProject implements Runnable{
    @CommandLine.Parameters(index = "0", paramLabel = "title", description = "Project title")
    String title;

    @Inject
    CommandBus commandBus;

    @Override
    public void run() {
        System.out.println("create a new project '" + this.title + "'.");
        CreateNewProject createNewProject = new CreateNewProject(title);
        ProjectId projectId = this.commandBus.send(createNewProject);
        System.out.println("New project '" + this.title + "' have been created with id " + projectId.value());
    }
}
