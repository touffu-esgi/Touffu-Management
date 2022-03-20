package cat.touffu.management.features.list.application.command.create_new_project;

import cat.touffu.management.features.list.domain.ListId;
import cat.touffu.management.features.list.domain.AList;
import cat.touffu.management.features.list.domain.ListRepository;
import org.apache.commons.lang3.NotImplementedException;

import java.util.HashMap;
import java.util.Map;

public class MockListRepository implements ListRepository {

    public Map<ListId, AList> store = new HashMap<>();

    @Override
    public AList findById(ListId listId) {
        throw new NotImplementedException("find List by id");
    }

    @Override
    public void save(AList listOfProject) {
        this.store.put(listOfProject.id(), listOfProject);
    }

    @Override
    public java.util.List<AList> findAll() {
        throw new NotImplementedException("find all List");
    }

    @Override
    public void remove(AList item) {
        throw new NotImplementedException("remove List");
    }

}
