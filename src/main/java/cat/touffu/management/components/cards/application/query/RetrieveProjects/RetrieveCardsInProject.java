package cat.touffu.management.components.cards.application.query.RetrieveProjects;

import cat.touffu.management.kernel.query.Query;

public record RetrieveCardsInProject(
        String projectId
) implements Query {}
