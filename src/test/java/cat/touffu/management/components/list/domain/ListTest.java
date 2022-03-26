package cat.touffu.management.components.list.domain;

import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ListTest {

    @Test
    void shouldCreateAProjectObject() {
        String listId = "0308eb60-bb8c-422e-a356-a0f5434a5328";
        String projectId = "0308eb60-bb8c-422e-a356-a0f5434a5325";
        ListOfCard listOfProject = ListOfCard.of(ListId.of(listId), "Amazing Project", projectId, new HashSet<CardId>());
        assertEquals(listId, listOfProject.id().value());
        assertEquals("Amazing Project", listOfProject.title());
        assertEquals(projectId, listOfProject.projectId().value());
    }

    @Test
    void shouldAddCardInList() {
        ListOfCard list = ListOfCard.of(ListId.of("idList"), "title", "projectId", new HashSet<CardId>());
        CardId cardId = CardId.of("idCard");
        list.addCard(cardId);
        assertTrue(list.cardIds().contains(cardId));
    }

}
