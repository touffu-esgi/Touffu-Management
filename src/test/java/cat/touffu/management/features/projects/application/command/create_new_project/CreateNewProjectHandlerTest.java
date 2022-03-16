package cat.touffu.management.features.projects.application.command.create_new_project;

import cat.touffu.management.features.projects.domain.ProjectId;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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