package cat.touffu.management.kernel.exception;

public class CommandException extends RuntimeException{
    public CommandException() {}

    public CommandException(String message) {
        super(message);
    }
}
