package cat.touffu.management.kernel.exception;

public class ProjectNotFoundException extends NotFoundException{
    public ProjectNotFoundException(String projectId) {
        super("Project with id " + projectId + " not found.");
    }
}
