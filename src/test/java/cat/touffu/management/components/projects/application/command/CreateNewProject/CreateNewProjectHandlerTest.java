package cat.touffu.management.components.projects.application.command.CreateNewProject;

import cat.touffu.management.components.projects.domain.ProjectId;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CreateNewProjectHandlerTest {

    @Test
    void shouldCreateANewProject() {
        MockProjectRepository repository = new MockProjectRepository();
        CreateNewProjectHandler handler = new CreateNewProjectHandler(repository);

        ProjectId createdId = handler.handle(new CreateNewProject("Title of your amazing project"));

        assertTrue(repository.store.containsKey(createdId));
        assertEquals("Title of your amazing project", repository.store.get(createdId).title());
    }
}