package cat.touffu.management.features.list.domain;

import cat.touffu.management.features.projects.domain.Project;
import cat.touffu.management.features.projects.domain.ProjectId;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ListTest {

    @Test
    void shouldCreateAProjectObject() {
        AList listOfProject = AList.of(ListId.of("0308eb60-bb8c-422e-a356-a0f5434a5328"), "Amazing Project", "0308eb60-bb8c-422e-a356-a0f5434a5325");
        assertEquals("0308eb60-bb8c-422e-a356-a0f5434a5328", listOfProject.id().value());
        assertEquals("Amazing Project", listOfProject.content());
        assertEquals("0308eb60-bb8c-422e-a356-a0f5434a5325", listOfProject.project_id());
    }

}
