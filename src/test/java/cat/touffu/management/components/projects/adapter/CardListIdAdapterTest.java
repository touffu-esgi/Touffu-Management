package cat.touffu.management.components.projects.adapter;

import cat.touffu.management.components.projects.domain.CardListId;
import com.google.common.collect.Maps;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class CardListIdAdapterTest {

    @Test
    void shouldParseListOfCardListId() {
        Set<CardListId> list = new HashSet<>(Arrays.asList(CardListId.of("id1"), CardListId.of("id2"), CardListId.of("id3")));
        CardListIdAdapter adapter = new CardListIdAdapter();
        Set<CardListId> adapted = adapter.adapt("[\"id1\", \"id2\", \"id3\"]");
        assertEquals(3, adapted.size());

        List<String> expectedStringIds = list.stream().map(CardListId::value).collect(Collectors.toList());
        List<String> actualStringIds = adapted.stream().map(CardListId::value).collect(Collectors.toList());
        assertTrue(expectedStringIds.containsAll(actualStringIds) && actualStringIds.containsAll(expectedStringIds));
    }

}
