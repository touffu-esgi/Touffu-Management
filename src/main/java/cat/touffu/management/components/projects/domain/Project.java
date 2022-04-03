package cat.touffu.management.components.projects.domain;

import java.util.Objects;
import java.util.Set;

public record Project(
        ProjectId id,
        String title,
        Set<CardListId> cardListIds
) {
    public Project {
        Objects.requireNonNull(title);
        Objects.requireNonNull(cardListIds);
    }


    public static Project of(ProjectId id, String title, Set<CardListId> cardListIds) {
        return new Project(id, title, cardListIds);
    }

    public void addCardList(CardListId cardListId) {
        cardListIds.add(cardListId);
    }

}
