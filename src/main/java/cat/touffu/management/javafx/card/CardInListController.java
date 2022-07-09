package cat.touffu.management.javafx.card;

import cat.touffu.management.components.cards.domain.Card;
import cat.touffu.management.javafx.board.Board;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
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

    public void onClickCard(MouseEvent mouseEvent) {
        var id = mouseEvent.getPickResult().getIntersectedNode().getId();
        Board.getInstance().controller.kanbanBoard.onClickOnCard(id);
    }
}
