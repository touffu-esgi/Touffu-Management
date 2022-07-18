package cat.touffu.management.components.cards.infrastructure;

import cat.touffu.management.components.cards.adapter.CardStatusFromStringAdapter;
import cat.touffu.management.components.cards.adapter.CardStatusToStringAdapter;
import cat.touffu.management.components.cards.domain.Card;
import cat.touffu.management.components.cards.domain.CardId;
import cat.touffu.management.components.cards.domain.ProjectId;
import cat.touffu.management.kernel.database.JsonAdapter;
import kong.unirest.JsonNode;
import kong.unirest.json.JSONObject;
import org.apache.commons.lang3.NotImplementedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonAdapters {

    public static class CardsJsonAdapter implements JsonAdapter<List<Card>> {

        @Override
        public List<Card> adapt(JsonNode from) {
            var arr = from.getArray();
            List<JsonNode> cards = new ArrayList<>();
            for (int i = 0; i < arr.length(); i++) cards.add(new JsonNode(arr.get(i).toString()));
            var cardAdapter = new CardJsonAdapter();

            return cards.stream().map(cardAdapter::adapt).toList();
        }

        @Override
        public Map<String, Object> adapt(List<Card> cards) {
            throw new NotImplementedException("list card to map for rest api");
        }
    }

    public static class CardJsonAdapter implements JsonAdapter<Card> {

        @Override
        public Card adapt(JsonNode from) {
            var c = from.getObject();
            var cardStatusFromStringAdapter = new CardStatusFromStringAdapter();

            return Card.of(
                    CardId.of(c.getString("id")),
                    c.getString("title"),
                    ProjectId.of(c.getString("projetId")),
                    cardStatusFromStringAdapter.adapt(c.getString("cardStatus"))
            );
        }

        @Override
        public Map<String, Object> adapt(Card card) {
            var statusAdapter = new CardStatusToStringAdapter();
            var map = new HashMap<String, Object>();
            map.put("id", card.id().value());
            map.put("title", card.title());
            map.put("projetId", card.projectId().value());
            map.put("cardStatus", statusAdapter.adapt(card.cardStatus()));

            return map;
        }
    }
}
