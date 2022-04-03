package cat.touffu.management.components.projects.application.command.CreateNewProject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateNewProjectHandlerTest {

    @Test
    void shouldCreateANewProject() {
        MockProjectRepository repository = new MockProjectRepository();
        CreateNewProjectHandler handler = new CreateNewProjectHandler(repository);

        handler.handle(new CreateNewProject("Title of your amazing project"));

        assertEquals(1, repository.store.size());
        assertEquals("Title of your amazing project", repository.store.values().iterator().next().title());
    }
}
