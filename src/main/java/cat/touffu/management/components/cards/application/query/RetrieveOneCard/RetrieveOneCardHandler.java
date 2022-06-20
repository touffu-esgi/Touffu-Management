package cat.touffu.management.components.cards.application.query.RetrieveOneCard;

import cat.touffu.management.components.cards.application.query.RetrieveCardsInProject.RetrieveCardsInProject;
import cat.touffu.management.components.cards.domain.Card;
import cat.touffu.management.components.cards.domain.CardId;
import cat.touffu.management.components.cards.domain.CardRepository;
import cat.touffu.management.kernel.query.QueryHandler;

import java.util.List;

public class RetrieveOneCardHandler implements QueryHandler<RetrieveOneCard, Card> {
    private final CardRepository cardRepository;

    public RetrieveOneCardHandler(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public Card handle(RetrieveOneCard query) {
        return cardRepository.findById(CardId.of(query.cardId()));
    }
}
