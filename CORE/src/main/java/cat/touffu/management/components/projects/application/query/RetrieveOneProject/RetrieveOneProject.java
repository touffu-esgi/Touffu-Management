package cat.touffu.management.components.projects.application.query.RetrieveOneProject;

import cat.touffu.management.kernel.query.Query;

public record RetrieveOneProject(
        String id
) implements Query {}
