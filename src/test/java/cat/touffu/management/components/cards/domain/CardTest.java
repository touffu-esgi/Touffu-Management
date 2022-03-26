package cat.touffu.management.components.cards.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @Test
    void shouldCreateCard() {
        Card card = Card.of(CardId.of("cardId"), "CardTitle", ListId.of("listId"));
        assertEquals("cardId", card.id().value());
        assertEquals("CardTitle", card.title());
        assertEquals("listId", card.listId().value());
    }

}