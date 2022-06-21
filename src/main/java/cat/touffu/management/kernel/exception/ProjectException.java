package cat.touffu.management.kernel.exception;

public class ProjectException extends NotFoundException{
    public ProjectException(String msg) {
        super(msg);
    }
}
