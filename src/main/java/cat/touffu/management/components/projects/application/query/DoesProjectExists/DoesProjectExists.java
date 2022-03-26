package cat.touffu.management.components.projects.application.query.DoesProjectExists;

import cat.touffu.management.kernel.query.Query;

public record DoesProjectExists(
        String projectId
) implements Query {}
