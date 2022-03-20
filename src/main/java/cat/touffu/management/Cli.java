package cat.touffu.management;

import cat.touffu.management.features.list.exposition.cli.NewListOfCard;
import cat.touffu.management.features.projects.exposition.cli.NewProject;
import picocli.CommandLine;

@CommandLine.Command(
        subcommands = {
                NewProject.class,
                NewListOfCard.class
        }
)
public class Cli implements Runnable{

    public static void main(String[] args) {
        CommandLine.run(new Cli(), args);
    }

    @Override
    public void run() {
        System.out.println("Touffu management CLI.");
    }
}
