package cat.touffu.management.components.list.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public record ListOfCard(
        ListId id,
        String title,
        ProjectId projectId,
        Set<CardId> cardIds
) {

    public ListOfCard {
        Objects.requireNonNull(title);
        Objects.requireNonNull(projectId);
    }


    public static ListOfCard of(ListId id, String title, String projectId, HashSet<CardId> cardIds) {
        return new ListOfCard(id, title, ProjectId.of(projectId), cardIds);
    }

    public void addCard(CardId cardId) {
        this.cardIds.add(cardId);
    }
}
