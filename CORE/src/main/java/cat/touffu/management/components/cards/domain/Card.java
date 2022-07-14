package cat.touffu.management.components.cards.domain;

public record Card(
        CardId id,
        String title,
        ProjectId projectId,
        CardStatus cardStatus
){

    public static Card of(CardId id, String title, ProjectId projectId, CardStatus status) {
        return new Card(id, title, projectId, status);
    }
}
