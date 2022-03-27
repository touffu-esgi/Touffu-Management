package cat.touffu.management.kernel.command;

@FunctionalInterface
public interface CommandHandler<TCommand extends Command> {
    void handle(TCommand command) throws Exception;
}
