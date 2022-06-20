package cat.touffu.management.javafx.card;

import cat.touffu.management.components.cards.domain.Card;
import javafx.scene.text.Text;

public class CardInListController {
    public Text contentCard;


    public void initData(Card card){
        this.contentCard.setText(card.title());
        this.contentCard.setId(card.id().value());
    }
}
