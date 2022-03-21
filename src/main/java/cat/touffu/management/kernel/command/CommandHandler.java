package cat.touffu.management.kernel.command;

@FunctionalInterface
public interface CommandHandler<TCommand extends Command, TResponse> {
    TResponse handle(TCommand command);
}
