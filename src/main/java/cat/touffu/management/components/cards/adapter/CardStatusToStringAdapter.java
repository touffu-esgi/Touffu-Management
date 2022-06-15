package cat.touffu.management.components.cards.adapter;

import cat.touffu.management.components.cards.domain.CardStatus;
import cat.touffu.management.kernel.Adapter;

public class CardStatusToStringAdapter implements Adapter<CardStatus, String> {

    @Override
    public String adapt(CardStatus source) {
        switch (source) {
            case TODO: return "todo";
            case IN_PROGRESS: return "in_progress";
            case DONE: return "done";
            default: throw new CardStatusException();
        }
    }
}
