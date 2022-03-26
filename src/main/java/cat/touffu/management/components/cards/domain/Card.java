package cat.touffu.management.components.cards.domain;

import java.util.Objects;

public record Card(
        CardId id,
        String title,
        ListId listId
){
    public Card {
        Objects.requireNonNull(title);
        Objects.requireNonNull(id);
        Objects.requireNonNull(listId);
    }

    public static Card of(CardId id, String title, ListId listId) {
        return new Card(id, title, listId);
    }
}
