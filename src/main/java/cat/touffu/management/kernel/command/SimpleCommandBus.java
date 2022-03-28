package cat.touffu.management.kernel.command;

import java.util.Map;

public record SimpleCommandBus(
        Map<Class<? extends Command>, CommandHandler> commandMap
) implements CommandBus {

    @Override
    public <TCommand extends Command, TResponse> TResponse send(TCommand command) {
        return dispatch(command);
    }

    private <TCommand extends Command, TResponse> TResponse dispatch(TCommand command) {
        final CommandHandler commandHandler = commandMap.get(command.getClass());
        if (commandHandler == null) {
            throw new RuntimeException("No such command handler for " + command.getClass().getName());
        }

        return (TResponse) commandHandler.handle(command);
    }
}
