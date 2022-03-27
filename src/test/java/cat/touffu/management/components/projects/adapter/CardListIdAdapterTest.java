package cat.touffu.management.components.projects.adapter;

import cat.touffu.management.components.projects.domain.CardListId;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CardListIdAdapterTest {

    @Test
    void shouldParseListOfCardListId() {
        Set<CardListId> list = new HashSet<>(Arrays.asList(CardListId.of("id1"), CardListId.of("id2"), CardListId.of("id3")));
        StringToCardListIdsAdapter adapter = new StringToCardListIdsAdapter();
        Set<CardListId> adapted = adapter.adapt("[\"id1\", \"id2\", \"id3\"]");
        assertEquals(3, adapted.size());

        List<String> expectedStringIds = list.stream().map(CardListId::value).collect(Collectors.toList());
        List<String> actualStringIds = adapted.stream().map(CardListId::value).collect(Collectors.toList());
        assertTrue(expectedStringIds.containsAll(actualStringIds) && actualStringIds.containsAll(expectedStringIds));
    }

}
