package cat.touffu.management.kernel.command;

import cat.touffu.management.kernel.exception.CommandException;
import cat.touffu.management.kernel.exception.ExceptionFilter;
import cat.touffu.management.kernel.exception.NoSuchCommandHandlerException;

import java.util.Map;

public class SimpleCommandBus implements CommandBus {
    private final Map<Class<? extends Command>, CommandHandler> commandMap;
    private ExceptionFilter exceptionFilter;

    public SimpleCommandBus(Map<Class<? extends Command>, CommandHandler> commandMap) {
        this.commandMap = commandMap;
    }

    @Override
    public <TCommand extends Command> void send(TCommand command) {
        try {
            dispatch(command);
        } catch (Exception e) {
            if(exceptionFilter != null) {
                exceptionFilter.grab(e);
            } else {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void setExceptionFilter(ExceptionFilter exceptionFilter) {
        this.exceptionFilter = exceptionFilter;
    }

    private <TCommand extends Command> void dispatch(TCommand command) throws Exception {
        final CommandHandler commandHandler = commandMap.get(command.getClass());
        if (commandHandler == null) {
            throw new NoSuchCommandHandlerException(command.getClass());
        }

        commandHandler.handle(command);
    }
}
