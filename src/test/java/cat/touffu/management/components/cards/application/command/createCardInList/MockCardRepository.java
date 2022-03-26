package cat.touffu.management.components.cards.application.command.createCardInList;

import cat.touffu.management.components.cards.domain.Card;
import cat.touffu.management.components.cards.domain.CardId;
import cat.touffu.management.components.cards.domain.CardRepository;
import org.apache.commons.lang3.NotImplementedException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockCardRepository implements CardRepository {
    public Map<String, Card> store = new HashMap<>();

    @Override
    public void save(Card card) {
        throw new NotImplementedException();
    }

    @Override
    public void add(Card card) {
        store.put(card.id().value(), card);
    }

    @Override
    public void update(Card card) {
        throw new NotImplementedException();
    }

    @Override
    public Card findById(CardId cardId) {
        throw new NotImplementedException();
    }

    @Override
    public List<Card> findAll() {
        throw new NotImplementedException();
    }

    @Override
    public void remove(Card item) {
        throw new NotImplementedException();
    }
}
