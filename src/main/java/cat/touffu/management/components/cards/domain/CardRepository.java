package cat.touffu.management.components.cards.domain;

import cat.touffu.management.components.projects.domain.ProjectId;
import cat.touffu.management.kernel.Repository;

import java.util.UUID;

public interface CardRepository extends Repository<Card, CardId> {
    default CardId newId() {
        return CardId.fromUUID(UUID.randomUUID());
    }
}
