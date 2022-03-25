package cat.touffu.management.components.list.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ListTest {

    @Test
    void shouldCreateAProjectObject() {
        ListOfCard listOfProject = ListOfCard.of(ListId.of("0308eb60-bb8c-422e-a356-a0f5434a5328"), "Amazing Project", "0308eb60-bb8c-422e-a356-a0f5434a5325");
        assertEquals("0308eb60-bb8c-422e-a356-a0f5434a5328", listOfProject.id().value());
        assertEquals("Amazing Project", listOfProject.title());
        assertEquals("0308eb60-bb8c-422e-a356-a0f5434a5325", listOfProject.projectId().value());
    }

}
