package cat.touffu.management.components.projects.adapter;

import cat.touffu.management.components.projects.domain.CardListId;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CardListIdsToStringAdapterTest {
    private final CardListIdsToStringAdapter adapter = new CardListIdsToStringAdapter();
    @Test
    void shouldSerializeCardListIdsInJsonArray() {
        Set<CardListId> set = new HashSet<CardListId>(Arrays.asList(
                CardListId.of("id1"),
                CardListId.of("id2")
        ));
        assertTrue(List.of(
                        "[\"id1\",\"id2\"]",
                        "[\"id2\",\"id1\"]"
                ).contains(adapter.adapt(set))
        );
    }

    @Test
    void shouldSerializeEmptyCardListIdsInEmptyJsonArray() {
        assertEquals("[]", adapter.adapt(new HashSet<>()));
    }

}