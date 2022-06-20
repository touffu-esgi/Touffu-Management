package cat.touffu.management.javafx.card;

import javafx.scene.text.Text;

public class CardInListController {
    public Text ContentCard;


    public void initData(String content){
        this.ContentCard.setText(content);
    }
}
