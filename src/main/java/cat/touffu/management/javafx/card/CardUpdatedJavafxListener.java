package cat.touffu.management.javafx.card;

import cat.touffu.management.components.cards.CardsModule;
import cat.touffu.management.components.cards.adapter.CardStatusFromStringAdapter;
import cat.touffu.management.components.cards.application.event.CardCreationDone;
import cat.touffu.management.components.cards.application.event.CardUpdateDone;
import cat.touffu.management.components.cards.application.query.RetrieveOneCard.RetrieveOneCard;
import cat.touffu.management.components.cards.domain.Card;
import cat.touffu.management.components.cards.domain.CardId;
import cat.touffu.management.components.cards.domain.ProjectId;
import cat.touffu.management.javafx.board.Board;
import cat.touffu.management.kernel.event.Subscriber;
import cat.touffu.management.kernel.query.QueryBus;

public record CardUpdatedJavafxListener() implements Subscriber<CardUpdateDone> {

    private static final QueryBus queryBus = CardsModule.queryBus();
    private static final CardStatusFromStringAdapter cardStatusFromStringAdapter = new CardStatusFromStringAdapter();

    @Override
    public void accept(CardUpdateDone event) {
        Card card = Card.of(
                CardId.of(event.cardId()),
                event.title(),
                null,
                cardStatusFromStringAdapter.adapt(event.status())
                );
        Board.getInstance().controller.updateCard(card);
    }
}
