package cat.touffu.management.components.cards.application.command.createCardInList;

import cat.touffu.management.components.cards.domain.CardId;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateCardInListHandlerTest {
    @Test
    void shouldHandleCreateCardInListCommand() {
        CreateCardInList command = CreateCardInList.of(
                "title of card",
                "id_of_list_where_card_is_created"
        );
        MockCardRepository cardRepository = new MockCardRepository();
        CreateCardInListHandler handler = new CreateCardInListHandler(cardRepository);
        CardId createdCardId = handler.handle(command);
        assertTrue(cardRepository.store.containsKey(createdCardId.value()));
        assertEquals("title of card", cardRepository.store.get(createdCardId.value()).title());
    }
}