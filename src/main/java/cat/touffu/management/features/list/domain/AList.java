package cat.touffu.management.features.list.domain;

import java.util.Objects;

public record AList(
        ListId id,
        String content,
        String project_id
) {
    public AList {
        Objects.requireNonNull(content, project_id);
    }


    public static AList of(ListId id, String content, String project_id) {
        return new AList(id, content, project_id);
    }
}
