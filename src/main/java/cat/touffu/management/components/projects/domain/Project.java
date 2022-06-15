package cat.touffu.management.components.projects.domain;

import java.util.Set;

public record Project(
        ProjectId id,
        String title,
        Set<CardId> cards
) {


    public static Project of(ProjectId id, String title, Set<CardId> cards) {
        return new Project(id, title, cards);
    }

    public boolean contains(CardId cardCreated) {
        return this.cards.contains(cardCreated);
    }

    public void addCard(CardId card) {
        this.cards.add(card);
    }
}
