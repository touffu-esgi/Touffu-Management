package cat.touffu.management.components.projects.domain;

import cat.touffu.management.kernel.exception.ProjectException;

import java.util.Objects;
import java.util.Set;

public record Project(
        ProjectId id,
        String title,
        Set<CardId> cards
) {


    public static Project of(ProjectId id, String title, Set<CardId> cards) {
        if(title.isBlank()) throw new ProjectException("Empty title");
        return new Project(id, title, cards);
    }

    public boolean contains(CardId cardCreated) {
        return this.cards.contains(cardCreated);
    }

    public void addCard(CardId card) {
        this.cards.add(card);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return id.value().equals(project.id.value());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id.value());
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id.value() +
                ", title='" + title + '\'' +
                ", cards=" + cards +
                '}';
    }
}
