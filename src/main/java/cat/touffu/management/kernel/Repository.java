package cat.touffu.management.kernel;

import java.util.List;

public interface Repository<Entity, EntityId> {
    void save(Entity entity);

    Entity findById(EntityId id);

    List<Entity> findAll();

    void remove(Entity item);

    EntityId nextId();
}