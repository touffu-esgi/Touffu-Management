package cat.touffu.management.features.list.infrastructure;

import cat.touffu.management.features.list.domain.AList;
import cat.touffu.management.features.list.domain.ListId;
import cat.touffu.management.features.list.domain.ListRepository;
import org.apache.commons.lang3.NotImplementedException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryListOfProjectRepository implements ListRepository {
    private static final ListRepository INSTANCE = new InMemoryListOfProjectRepository();
    private final Map<ListId, AList> projectStore;

    private InMemoryListOfProjectRepository() {
        this.projectStore = new ConcurrentHashMap<>();
    }

    public static ListRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public void save(AList project) {
        projectStore.put(project.id(), project);
    }

    @Override
    public AList findById(ListId projectId) {
        throw new NotImplementedException();
    }

    @Override
    public java.util.List<AList> findAll() {
        throw new NotImplementedException();
    }

    @Override
    public void remove(AList item) {
        throw new NotImplementedException();
    }
}
