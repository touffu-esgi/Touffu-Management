package cat.touffu.management.kernel.exception;

import cat.touffu.management.kernel.command.Command;

import java.util.NoSuchElementException;

public class NoSuchCommandHandlerException extends CommandException {
    public NoSuchCommandHandlerException(Class<? extends Command> command) {
        super("No such command handler for " + command.getSimpleName() + ".");
    }
}
