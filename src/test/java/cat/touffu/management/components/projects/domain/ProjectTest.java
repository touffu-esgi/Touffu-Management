package cat.touffu.management.components.projects.domain;

import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProjectTest {

    @Test
    void shouldCreateAProjectObject() {
        Project project = Project.of(ProjectId.of("id"), "title", new HashSet<CardListId>());
        assertEquals("id", project.id().value());
        assertEquals("title", project.title());
    }

    @Test
    void shouldAddACardListIdInProject() {
        Project project = Project.of(ProjectId.of("id"), "title", new HashSet<CardListId>());
        CardListId cardListId = CardListId.of("Id_of_card_list");
        project.addCardList(cardListId);
        assertTrue(project.cardListIds().contains(cardListId));
    }

}
