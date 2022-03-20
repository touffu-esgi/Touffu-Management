package cat.touffu.management.features.list.application.command.create_new_project;

import cat.touffu.management.features.list.application.command.create_new_list_in_project.CreateNewList;
import cat.touffu.management.features.list.application.command.create_new_list_in_project.CreateNewListOfProjectHandler;
import cat.touffu.management.features.list.domain.ListId;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CreateNewListOfProjectHandlerTest {

    @Test
    void shouldCreateANewProject() {
        MockListRepository repository = new MockListRepository();
        CreateNewListOfProjectHandler handler = new CreateNewListOfProjectHandler(repository);

        ListId createdId = handler.handle(new CreateNewList("Title of your amazing project", "0308eb60-bb8c-422e-a356-a0f5434a5325"));

        assertTrue(repository.store.containsKey(createdId));
        assertEquals("Title of your amazing project", repository.store.get(createdId).content());
    }
}