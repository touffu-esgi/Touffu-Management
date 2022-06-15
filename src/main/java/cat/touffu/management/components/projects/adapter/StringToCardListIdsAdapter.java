package cat.touffu.management.components.projects.adapter;

import cat.touffu.management.components.projects.domain.CardId;
import cat.touffu.management.kernel.Adapter;
import org.json.JSONArray;

import java.util.HashSet;
import java.util.Set;

public class StringToCardListIdsAdapter implements Adapter<String, Set<CardId>> {
    @Override
    public Set<CardId> adapt(String source) {
        Set<CardId> listIds = new HashSet<>();
        JSONArray array = new JSONArray(source);
        array.toList().forEach(id -> listIds.add(CardId.of((String) id) ));
        return listIds;
    }
}
