package cat.touffu.management.components.projects.adapter;

import cat.touffu.management.components.projects.domain.CardId;
import cat.touffu.management.kernel.Adapter;
import org.json.JSONArray;

import java.util.Set;
import java.util.stream.Collectors;

public class CardListIdsToStringAdapter implements Adapter<Set<CardId>, String> {
    @Override
    public String adapt(Set<CardId> cardIds) {
        return new JSONArray(
                cardIds.stream()
                        .map(CardId::value)
                        .collect(Collectors.toList())
        ).toString();
    }
}
