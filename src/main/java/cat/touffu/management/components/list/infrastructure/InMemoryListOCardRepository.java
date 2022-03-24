package cat.touffu.management.components.list.infrastructure;

import cat.touffu.management.components.list.domain.ListOfCard;
import cat.touffu.management.components.list.domain.ListId;
import cat.touffu.management.components.list.domain.ListRepository;
import org.apache.commons.lang3.NotImplementedException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryListOCardRepository implements ListRepository {
    private static final ListRepository INSTANCE = new InMemoryListOCardRepository();
    private final Map<ListId, ListOfCard> projectStore;

    private InMemoryListOCardRepository() {
        this.projectStore = new ConcurrentHashMap<>();
    }

    public static ListRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public void save(ListOfCard project) {
        projectStore.put(project.id(), project);
    }

    @Override
    public ListOfCard findById(ListId projectId) {
        throw new NotImplementedException();
    }

    @Override
    public java.util.List<ListOfCard> findAll() {
        throw new NotImplementedException();
    }

    @Override
    public void remove(ListOfCard item) {
        throw new NotImplementedException();
    }
}
