package cat.touffu.management.components.projects.adapter;

import cat.touffu.management.components.projects.domain.CardListId;
import cat.touffu.management.shared_kernel.Adapter;
import org.json.JSONArray;

import java.util.HashSet;
import java.util.Set;

public class CardListIdAdapter implements Adapter<String, Set<CardListId>> {
    @Override
    public Set<CardListId> adapt(String source) {
        Set<CardListId> listIds = new HashSet<>();
        JSONArray array = new JSONArray(source);
        array.toList().forEach(id -> listIds.add(CardListId.of((String) id) ));
        return listIds;
    }
}
