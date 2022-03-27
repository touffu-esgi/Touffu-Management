package cat.touffu.management.kernel.command;

import cat.touffu.management.kernel.exception.ExceptionFilter;

public interface CommandBus {
    <TCommand extends Command> void send(TCommand command);
    void setExceptionFilter(ExceptionFilter exceptionFilter);
}
