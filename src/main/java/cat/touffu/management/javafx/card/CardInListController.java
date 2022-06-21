package cat.touffu.management.javafx.card;

import cat.touffu.management.components.cards.domain.Card;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.text.Text;

public class CardInListController {
    public Text contentCard;
    private Node view;
    private Card card;


    public void initData(Card card, Node view){
        this.card = card;
        this.contentCard.setText(card.title());
        this.view = view;
    }

    public Node getView() {
        return view;
    }

    public Card getCard() {
        return card;
    }
}
