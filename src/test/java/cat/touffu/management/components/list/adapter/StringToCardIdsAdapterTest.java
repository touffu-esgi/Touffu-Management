package cat.touffu.management.components.list.adapter;

import cat.touffu.management.components.list.domain.CardId;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringToCardIdsAdapterTest {
    private final StringToCardIdsAdapter adapter = new StringToCardIdsAdapter();

    @Test
    void shouldAdaptStringToSetOfCardId() {
        Set<CardId> set = adapter.adapt("[\"id1\",\"id2\"]");
        assertEquals(2, set.size());
        assertTrue(set.contains(CardId.of("id1")));
        assertTrue(set.contains(CardId.of("id2")));
    }

}