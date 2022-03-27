package cat.touffu.management.components.projects.adapter;

import cat.touffu.management.components.projects.domain.CardListId;
import cat.touffu.management.kernel.Adapter;
import org.json.JSONArray;

import java.util.Set;
import java.util.stream.Collectors;

public class CardListIdsToStringAdapter implements Adapter<Set<CardListId>, String> {
    @Override
    public String adapt(Set<CardListId> cardListIds) {
        return new JSONArray(
                cardListIds.stream()
                        .map(CardListId::value)
                        .collect(Collectors.toList())
        ).toString();
    }
}
