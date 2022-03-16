package cat.touffu.management.kernel.command;

public interface CommandBus {
    <TCommand extends Command, TResponse> TResponse send(TCommand command);
}
