package cat.touffu.management.features.projects.exposition.cli;

import picocli.CommandLine;

@CommandLine.Command(
        name = "new-project",
        description = "Create a new project."
)
public class NewProject implements Runnable{
    @CommandLine.Parameters(index = "0", paramLabel = "title", description = "Project title")
    String title;

    @Override
    public void run() {
        System.out.println("create a new project '" + this.title + "'.");
    }
}
