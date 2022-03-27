package cat.touffu.management.components.list.domain;

import org.json.JSONArray;

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


    public static ListOfCard of(ListId id, String title, String projectId, Set<CardId> cardIds) {
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
