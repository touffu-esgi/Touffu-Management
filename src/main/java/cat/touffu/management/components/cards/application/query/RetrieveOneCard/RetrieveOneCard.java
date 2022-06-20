package cat.touffu.management.components.cards.application.query.RetrieveOneCard;

import cat.touffu.management.kernel.query.Query;

public record RetrieveOneCard(
        String cardId
) implements Query {}