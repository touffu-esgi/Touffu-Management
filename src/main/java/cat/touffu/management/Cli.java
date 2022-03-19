package cat.touffu.management;

import cat.touffu.management.configuration.CDIFactory;
import cat.touffu.management.features.projects.exposition.cli.NewProject;
import cat.touffu.management.kernel.command.CommandBus;
import cat.touffu.management.kernel.command.SimpleCommandBus;
import com.google.inject.Inject;
import picocli.CommandLine;

@CommandLine.Command(
        subcommands = {
                NewProject.class
        }
)
public class Cli implements Runnable{
    @Inject CommandBus commandBus;

    public static void main(String[] args) {
        new CommandLine(Cli.class, new CDIFactory()).execute(args);
    }

    @Override
    public void run() {
        System.out.println("Touffu management CLI.");
        System.out.println(this.commandBus);
        assert commandBus instanceof SimpleCommandBus;
    }
}
