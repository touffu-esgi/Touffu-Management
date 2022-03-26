package cat.touffu.management.components.projects.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

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

    @Test
    void shouldSerializeCardListIdsInJsonArray() {
        Project project = Project.of( ProjectId.of("id"),"title",
                new HashSet<CardListId>(Arrays.asList(
                        CardListId.of("id1"),
                        CardListId.of("id2")
                        ))
        );
        assertTrue(List.of(
                "[\"id1\",\"id2\"]",
                "[\"id2\",\"id1\"]"
        ).contains(project.cardListIdsToString())
        );
    }

    @Test
    void shouldSerializeEmptyCardListIdsInEmptyJsonArray() {
        Project project = Project.of( ProjectId.of("id"),"title",
                new HashSet<>()
        );
        assertEquals("[]", project.cardListIdsToString());
    }

}
