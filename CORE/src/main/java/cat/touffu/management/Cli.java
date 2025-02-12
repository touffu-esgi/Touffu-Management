package cat.touffu.management;

import cat.touffu.management.components.cards.exposition.cli.NewCard;
import cat.touffu.management.components.projects.exposition.cli.ListAllProjects;
import cat.touffu.management.components.projects.exposition.cli.NewProject;
import picocli.CommandLine;

@CommandLine.Command(
        subcommands = {
                NewProject.class,
                ListAllProjects.class,
                NewCard.class
        },
        mixinStandardHelpOptions = true,
        version = "Touffu Management CLI 0.1.0"
)
public class Cli implements Runnable{

    public static void main(String[] args) {
        int exitCode = new CommandLine(new Cli()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public void run() {
        System.out.println("Touffu management CLI.  Try --help.");
    }
}
