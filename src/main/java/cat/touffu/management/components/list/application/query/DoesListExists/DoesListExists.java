package cat.touffu.management.components.list.application.query.DoesListExists;

import cat.touffu.management.kernel.query.Query;

public record DoesListExists(
        String listId
) implements Query {}
