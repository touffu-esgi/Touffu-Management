package cat.touffu.management.components.cards.application.command.createCardInList;

import cat.touffu.management.components.cards.domain.Card;
import cat.touffu.management.components.cards.domain.CardId;
import cat.touffu.management.components.cards.domain.CardRepository;
import cat.touffu.management.components.cards.domain.ListId;
import cat.touffu.management.kernel.command.CommandHandler;

public class CreateCardInListHandler implements CommandHandler<CreateCardInList, CardId> {
    private final CardRepository cardRepository;

    public CreateCardInListHandler(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public CardId handle(CreateCardInList command) {
        CardId cardId = cardRepository.newId();
        cardRepository.add(Card.of(
                cardId,
                command.title(),
                ListId.of(command.listId())
        ));
        return cardId;
    }
}
