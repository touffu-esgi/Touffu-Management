package cat.touffu.management.features.list.application.command.create_new_project;

import cat.touffu.management.features.list.domain.ListId;
import cat.touffu.management.features.list.domain.ListOfCard;
import cat.touffu.management.features.list.domain.ListRepository;
import org.apache.commons.lang3.NotImplementedException;

import java.util.HashMap;
import java.util.Map;

public class MockListRepository implements ListRepository {

    public Map<ListId, ListOfCard> store = new HashMap<>();

    @Override
    public ListOfCard findById(ListId listId) {
        throw new NotImplementedException("find List by id");
    }

    @Override
    public void save(ListOfCard listOfProject) {
        this.store.put(listOfProject.id(), listOfProject);
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
