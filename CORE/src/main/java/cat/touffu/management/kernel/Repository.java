package cat.touffu.management.kernel;

import java.util.List;

public interface Repository<Entity, EntityId> {

    boolean exists(EntityId id);

    void save(Entity entity);

    void add(Entity entity);

    void update(Entity entity);

    Entity findById(EntityId id);

    List<Entity> findAll();

    void remove(Entity item);

    EntityId newId();
}