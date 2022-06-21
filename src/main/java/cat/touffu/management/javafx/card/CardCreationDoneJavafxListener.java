package cat.touffu.management.javafx.card;

import cat.touffu.management.components.cards.CardsModule;
import cat.touffu.management.components.cards.application.event.CardCreationDone;
import cat.touffu.management.components.cards.application.query.RetrieveOneCard.RetrieveOneCard;
import cat.touffu.management.components.cards.domain.Card;
import cat.touffu.management.javafx.board.Board;
import cat.touffu.management.kernel.event.Subscriber;
import cat.touffu.management.kernel.query.QueryBus;

public record CardCreationDoneJavafxListener() implements Subscriber<CardCreationDone> {

    private static final QueryBus queryBus = CardsModule.queryBus();

    @Override
    public void accept(CardCreationDone event) {
        Card card = queryBus.request(new RetrieveOneCard(event.cardId().value()));
        Board.getInstance().controller.kanbanBoard.addCardInListByItsStatus(card);
    }
}
