package cat.touffu.management.kernel.exception;

public interface ExceptionFilter {
    void grab(Exception exception);
}
