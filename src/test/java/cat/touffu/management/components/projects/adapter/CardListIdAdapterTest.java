package cat.touffu.management.components.projects.adapter;

import cat.touffu.management.components.projects.domain.CardListId;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CardListIdAdapterTest {

    @Test
    void shouldParseListOfCardListId() {
        List<CardListId> list = List.of(CardListId.of("id1"), CardListId.of("id2"), CardListId.of("id3"));
        String json = "[\"id1\", \"id2\", \"id3\"]";
        CardListIdAdapter adapter = new CardListIdAdapter();
        assertEquals(list.toString(), adapter.adapt(json).toString());
    }

}