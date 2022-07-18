import cat.touffu.management.components.projects.domain.Project;

import java.util.Objects;

public record ProjectChoice(
        String id,
        String title,
        Project entity
) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectChoice that = (ProjectChoice) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
