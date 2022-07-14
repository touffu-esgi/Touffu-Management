package cat.touffu.management.components.cards.application.query.RetrieveCardsInProject;

import cat.touffu.management.kernel.query.Query;

public record RetrieveCardsInProject(
        String projectId
) implements Query {}
