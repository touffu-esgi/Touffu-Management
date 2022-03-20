package cat.touffu.management.features.list.domain;

import cat.touffu.management.kernel.Repository;

import java.util.UUID;

public interface ListRepository extends Repository<AList, ListId> {
    default ListId nextId() {
        return ListId.fromUUID(UUID.randomUUID());
    }

    void save(AList listOfProject);

    AList findById(ListId listId);

    void remove(AList item);
}
