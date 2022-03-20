package cat.touffu.management.features.list.domain;

import cat.touffu.management.kernel.Repository;

import java.util.UUID;

public interface ListRepository extends Repository<ListOfCard, ListId> {
    default ListId nextId() {
        return ListId.fromUUID(UUID.randomUUID());
    }

    void save(ListOfCard listOfProject);

    ListOfCard findById(ListId listId);

    void remove(ListOfCard item);
}
