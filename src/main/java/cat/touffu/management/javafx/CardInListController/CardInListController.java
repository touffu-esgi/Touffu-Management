package cat.touffu.management.javafx.CardInListController;

import javafx.scene.text.Text;

public class CardInListController {
    public Text ContentCard;


    public void initData(String content){
        this.ContentCard.setText(content);
    }
}
