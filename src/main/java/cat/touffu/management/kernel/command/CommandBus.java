package cat.touffu.management.kernel.command;

public interface CommandBus {
    <TCommand extends Command> void send(TCommand command);
}
