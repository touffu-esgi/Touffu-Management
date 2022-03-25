package cat.touffu.management.components.list.domain;

import java.util.Objects;

public record ListOfCard(
        ListId id,
        String title,
        ProjectId projectId
) {
    public ListOfCard {
        Objects.requireNonNull(title);
        Objects.requireNonNull(projectId);
    }


    public static ListOfCard of(ListId id, String content, String projectId) {
        return new ListOfCard(id, content, ProjectId.of(projectId));
    }
}
