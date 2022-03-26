package cat.touffu.management.components.list.domain;

import cat.touffu.management.components.projects.domain.CardListId;
import org.json.JSONArray;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

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

    public String cardIdsToString() {
        return new JSONArray(
                this.cardIds.stream()
                        .map(CardId::value)
                        .collect(Collectors.toList())
        ).toString();
    }
}
