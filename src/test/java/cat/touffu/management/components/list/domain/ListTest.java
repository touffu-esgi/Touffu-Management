package cat.touffu.management.components.list.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ListTest {

    @Test
    void shouldCreateAProjectObject() {
        String listId = "0308eb60-bb8c-422e-a356-a0f5434a5328";
        String projectId = "0308eb60-bb8c-422e-a356-a0f5434a5325";
        ListOfCard listOfProject = ListOfCard.of(ListId.of(listId), "Amazing Project", projectId);
        assertEquals(listId, listOfProject.id().value());
        assertEquals("Amazing Project", listOfProject.title());
        assertEquals(projectId, listOfProject.projectId().value());
    }



}
