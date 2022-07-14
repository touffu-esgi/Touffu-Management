package cat.touffu.management.components.cards.adapter;

import cat.touffu.management.components.cards.domain.CardStatus;
import cat.touffu.management.kernel.Adapter;

public class CardStatusFromStringAdapter implements Adapter<String, CardStatus> {

    @Override
    public CardStatus adapt(String source) {
        String cleanSource = source.trim().toUpperCase().replace(" ", "_");
        switch (cleanSource) {
            case "TODO": return CardStatus.TODO;
            case "IN_PROGRESS": return CardStatus.IN_PROGRESS;
            case "DONE": return CardStatus.DONE;
            default: throw new CardStatusException("Unknown card status " + source);
        }
    }
}
