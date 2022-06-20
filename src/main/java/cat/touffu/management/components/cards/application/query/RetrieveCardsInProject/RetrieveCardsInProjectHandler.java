package cat.touffu.management.components.cards.application.query.RetrieveCardsInProject;

import cat.touffu.management.components.cards.domain.Card;
import cat.touffu.management.components.cards.domain.CardRepository;
import cat.touffu.management.kernel.query.QueryHandler;

import java.util.List;

public class RetrieveCardsInProjectHandler implements QueryHandler<RetrieveCardsInProject, List<Card>> {
    private final CardRepository cardRepository;

    public RetrieveCardsInProjectHandler(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public List<Card> handle(RetrieveCardsInProject query) {
        return cardRepository.findInProject(query.projectId());
    }
}
