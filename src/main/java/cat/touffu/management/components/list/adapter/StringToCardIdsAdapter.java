package cat.touffu.management.components.list.adapter;

import cat.touffu.management.components.list.domain.CardId;
import cat.touffu.management.kernel.Adapter;
import org.json.JSONArray;

import java.util.HashSet;
import java.util.Set;

public class StringToCardIdsAdapter implements Adapter<String, Set<CardId>> {
    @Override
    public Set<CardId> adapt(String jsonString) {
        Set<CardId> cardIds = new HashSet<>();
        JSONArray array = new JSONArray(jsonString);
        array.toList().forEach(id -> cardIds.add(CardId.of((String) id)));
        return cardIds;
    }
}