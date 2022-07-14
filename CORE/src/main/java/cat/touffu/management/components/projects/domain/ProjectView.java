package cat.touffu.management.components.projects.domain;

import cat.touffu.management.kernel.View;

public record ProjectView(
        String id,
        String title
) implements View {}
