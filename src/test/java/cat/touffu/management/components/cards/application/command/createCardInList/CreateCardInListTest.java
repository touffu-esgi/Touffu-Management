package cat.touffu.management.components.cards.application.command.createCardInList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateCardInListTest {
    @Test
    void shouldCreateCommandCreateCardInList() {
        CreateCardInList command = CreateCardInList.of(
                "Title of the card",
                "id_of_the_list_where_the_card_is_created"
        );
        assertEquals("Title of the card", command.title());
        assertEquals("id_of_the_list_where_the_card_is_created", command.listId());
    }
}