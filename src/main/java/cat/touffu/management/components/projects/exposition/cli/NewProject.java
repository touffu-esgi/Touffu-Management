package cat.touffu.management.components.projects.exposition.cli;

import cat.touffu.management.components.projects.ProjectModule;
import cat.touffu.management.components.projects.application.command.CreateNewProject.CreateNewProject;
import cat.touffu.management.components.projects.domain.ProjectId;
import cat.touffu.management.kernel.command.CommandBus;
import picocli.CommandLine;

@CommandLine.Command(
        name = "new-project",
        description = "Create a new project."
)
public class NewProject implements Runnable{
    @CommandLine.Parameters(index = "0", paramLabel = "title", description = "Project title")
    String title;

    CommandBus commandBus = ProjectModule.commandBus();

    @Override
    public void run() {
        CreateNewProject createNewProject = new CreateNewProject(title);
        this.commandBus.send(createNewProject);
        System.out.println("New project '" + this.title + "' have been created.");
    }
}
