package cat.touffu.management.components.list.domain;

import java.util.Objects;

public record ListOfCard(
        ListId id,
        String content,
        String project_id
) {
    public ListOfCard {
        Objects.requireNonNull(content, project_id);
    }


    public static ListOfCard of(ListId id, String content, String project_id) {
        return new ListOfCard(id, content, project_id);
    }
}
