package cat.touffu.management.kernel.exception;

public class CommandException extends Exception{
    public CommandException() {}

    public CommandException(String message) {
        super(message);
    }
}
