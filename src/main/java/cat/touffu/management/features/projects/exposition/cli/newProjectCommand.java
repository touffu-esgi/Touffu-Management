package cat.touffu.management.features.projects.exposition.cli;

import picocli.CommandLine;

@CommandLine.Command(
        name = "newProject",
        description = "Create a new project."
)
public class newProjectCommand implements Runnable{
    @Override
    public void run() {
        System.out.println("create a new project");
    }
}
