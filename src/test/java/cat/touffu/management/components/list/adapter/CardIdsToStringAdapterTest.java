package cat.touffu.management.components.list.adapter;

import cat.touffu.management.components.list.domain.CardId;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CardIdsToStringAdapterTest {
    private final CardIdsToStringAdapter adapter = new CardIdsToStringAdapter();

    @Test
    void shouldSerializeCardIdsInJsonArray() {
        Set<CardId> set = new HashSet<>(Arrays.asList(
                CardId.of("id1"),
                CardId.of("id2")
        ));
        assertTrue(List.of(
                        "[\"id1\",\"id2\"]",
                        "[\"id2\",\"id1\"]"
                ).contains(adapter.adapt(set))
        );
    }

}