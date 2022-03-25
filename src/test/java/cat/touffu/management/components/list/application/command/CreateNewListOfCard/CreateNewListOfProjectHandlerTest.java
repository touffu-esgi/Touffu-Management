package cat.touffu.management.components.list.application.command.CreateNewListOfCard;


import cat.touffu.management.components.list.application.command.CreateNewListInProject.CreateNewListOfCardsHandler;
import cat.touffu.management.components.list.application.command.CreateNewListInProject.CreateNewListOfCardsInProject;
import cat.touffu.management.components.list.domain.ListId;
import cat.touffu.management.kernel.event.ApplicationEvent;
import cat.touffu.management.kernel.event.EventBus;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CreateNewListOfProjectHandlerTest {

    @Test
    void shouldCreateANewListOfCard() {
        MockListRepository repository = new MockListRepository();
        EventBus<ApplicationEvent> eventBus = new MockEventBus(Map.of());
        CreateNewListOfCardsHandler handler = new CreateNewListOfCardsHandler(repository, eventBus);

        ListId createdId = handler.handle(new CreateNewListOfCardsInProject("Title of your amazing project", "0308eb60-bb8c-422e-a356-a0f5434a5325"));

        assertTrue(repository.store.containsKey(createdId.value()));
        assertEquals("Title of your amazing project", repository.store.get(createdId.value()).title());
    }
}