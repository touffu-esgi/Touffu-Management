package cat.touffu.management.components.list.application.command.CreateNewListOfCard;

import cat.touffu.management.components.list.domain.ListId;
import cat.touffu.management.components.list.domain.ListOfCard;
import cat.touffu.management.components.list.domain.ListRepository;
import org.apache.commons.lang3.NotImplementedException;

import java.util.HashMap;
import java.util.Map;

public class MockListRepository implements ListRepository {

    public Map<String, ListOfCard> store = new HashMap<>();

    @Override
    public ListOfCard findById(ListId listId) {
        throw new NotImplementedException("find List by id");
    }

    @Override
    public void save(ListOfCard listOfCards) {
        this.store.put(listOfCards.id().value(), listOfCards);
    }

    @Override
    public void add(ListOfCard listOfCard) {
        store.put(listOfCard.id().value(), listOfCard);
    }

    @Override
    public void update(ListOfCard listOfCard) {
        store.replace(listOfCard.id().value(), listOfCard);
    }

    @Override
    public java.util.List<ListOfCard> findAll() {
        throw new NotImplementedException("find all List");
    }

    @Override
    public void remove(ListOfCard item) {
        throw new NotImplementedException("remove List");
    }

}
