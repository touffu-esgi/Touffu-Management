package cat.touffu.management.components.projects.adapter;

import cat.touffu.management.components.projects.domain.CardListId;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringToCardListIdsAdapterTest {
    private final StringToCardListIdsAdapter adapter = new StringToCardListIdsAdapter();

    @Test
    void shouldAdaptStringJsonArrayToSetOfCardListId() {
        Set<CardListId> set = adapter.adapt("[\"id1\",\"id2\"]");
        assertEquals(2, set.size());
        assertTrue(set.contains(CardListId.of("id1")));
        assertTrue(set.contains(CardListId.of("id2")));
    }

    @Test
    void shouldAdaptEmptyStringJsonArrayToEmptySet() {
        Set<CardListId> set = adapter.adapt("[]");
        assertEquals(0, set.size());
    }

    @Test
    void shouldThrowExceptionWhenBadJsonGiven() {
        Assertions.assertThrows(JSONException.class, () -> adapter.adapt("bad json array"));
    }
}