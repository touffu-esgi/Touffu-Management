package cat.touffu.management.components.cards.domain;

import cat.touffu.management.kernel.Repository;

import java.util.List;
import java.util.UUID;

public interface CardRepository extends Repository<Card, CardId> {
    default CardId newId() {
        return CardId.fromUUID(UUID.randomUUID());
    }

    List<Card> findInProject(String projectId);
}
