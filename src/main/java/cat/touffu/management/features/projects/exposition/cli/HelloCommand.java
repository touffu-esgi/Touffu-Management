package cat.touffu.management.features.projects.exposition.cli;

import picocli.CommandLine;

@CommandLine.Command(
        name = "hello",
        description = "Says hello."
)
public class HelloCommand implements Runnable{
    public static void main(String[] args) {
        CommandLine.run(new HelloCommand(), args);
    }

    @Override
    public void run() {
        System.out.println("Hello !");
    }
}
