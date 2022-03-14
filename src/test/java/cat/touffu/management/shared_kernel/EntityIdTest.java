package cat.touffu.management.shared_kernel;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class EntityIdTest {

    @Test
    public void shouldCreateNewEntityIdFromUUID() {
        UUID uuid = UUID.randomUUID();
        EntityId id = EntityId.fromUUID(uuid);
        assertEquals(uuid.toString(), id.value());
    }

    @Test
    public void shouldCreateEntityIdOfString() {
        String id = "f150e605-73db-4155-a67f-1a6202d996d5";
        assertEquals(id, EntityId.of(id).value());
    }
}

