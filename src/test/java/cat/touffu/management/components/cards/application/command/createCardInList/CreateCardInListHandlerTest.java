package cat.touffu.management.components.cards.application.command.createCardInList;

import cat.touffu.management.components.list.application.command.CreateNewListOfCard.MockEventBus;
import cat.touffu.management.components.list.application.query.DoesListExists.DoesListExists;
import cat.touffu.management.kernel.event.ApplicationEvent;
import cat.touffu.management.kernel.event.EventBus;
import cat.touffu.management.kernel.exception.NotFoundException;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CreateCardInListHandlerTest {
    private EventBus<ApplicationEvent> emptyApplicationEventBus =  new MockEventBus<ApplicationEvent>(new HashMap<>());

    @Test
    void shouldHandleCreateCardInListCommand() {
        CreateCardInList command = CreateCardInList.of(
                "title of card",
                "id_of_list"
        );
        MockCardRepository cardRepository = new MockCardRepository();
        MockQueryBus queryBus = new MockQueryBus(Map.of(DoesListExists.class, new MockDoesListExistsHandler(Boolean.TRUE)));
        CreateCardInListHandler handler = new CreateCardInListHandler(cardRepository, queryBus, this.emptyApplicationEventBus);
        try {
            handler.handle(command);
        } catch (Exception e) {}
        assertEquals(1, cardRepository.store.size());
        String idCreated = cardRepository.store.keySet().toArray()[0].toString();
        assertEquals("title of card", cardRepository.store.get(idCreated).title());
    }

    @Test
    void shouldThrowNotFoundExceptionWhenListDoesntExists() {
        CreateCardInList command = CreateCardInList.of(
                "title of card",
                "id_of_list"
        );
        MockCardRepository cardRepository = new MockCardRepository();
        MockQueryBus queryBus = new MockQueryBus(Map.of(DoesListExists.class, new MockDoesListExistsHandler(
                Boolean.FALSE
        )));
        CreateCardInListHandler handler = new CreateCardInListHandler(cardRepository, queryBus, this.emptyApplicationEventBus);
        assertThrows(NotFoundException.class, () -> handler.handle(command));
        assertEquals(0, cardRepository.store.size());
    }
}