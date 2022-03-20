package cat.touffu.management.components.projects.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProjectTest {

    @Test
    void shouldCreateAProjectObject() {
        Project project = Project.of(ProjectId.of("id"), "title");
        assertEquals("id", project.id().value());
        assertEquals("title", project.title());
    }

}
