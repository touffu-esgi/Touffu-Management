package cat.touffu.management.shared_kernel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EntityIdTest {
   @Test
   public void newEntityId() {
       EntityId id = new EntityId("f150e605-73db-4155-a67f-1a6202d996d5");
       assertEquals("f150e605-73db-4155-a67f-1a6202d996d5", id.value());
   }
}