package cat.touffu.management.components.projects.domain;
import java.util.Objects;

public record Project(
        ProjectId id,
        String title
) {
    public Project {
        Objects.requireNonNull(title);
    }


    public static Project of(ProjectId id, String title) {
        return new Project(id, title);
    }
}
