package cat.touffu.management.features.projects.domain;

import cat.touffu.management.shared_kernel.EntityId;
import cat.touffu.management.shared_kernel.NotEmptyString;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProjectTest {

    @Test
    void shouldCreateAProjectObject() {
        Project project = Project.of(ProjectId.of("id"), new NotEmptyString("title"));
        assertEquals("id", project.id().value());
        assertEquals("title", project.title().value());
    }

}
