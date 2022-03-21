package cat.touffu.management.components.list.application.command.CreateNewListOfCard;

import cat.touffu.management.components.list.application.command.CreateNewListInProject.CreateNewListOfCards;
import cat.touffu.management.components.list.application.command.CreateNewListInProject.CreateNewListOfCardsHandler;
import cat.touffu.management.components.list.domain.ListId;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CreateNewListOfProjectHandlerTest {

    @Test
    void shouldCreateANewListOfCard() {
        MockListRepository repository = new MockListRepository();
        CreateNewListOfCardsHandler handler = new CreateNewListOfCardsHandler(repository);

        ListId createdId = handler.handle(new CreateNewListOfCards("Title of your amazing project", "0308eb60-bb8c-422e-a356-a0f5434a5325"));

        assertTrue(repository.store.containsKey(createdId));
        assertEquals("Title of your amazing project", repository.store.get(createdId).content());
    }
}
