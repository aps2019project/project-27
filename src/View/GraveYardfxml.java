package View;

import Moudle.Account;
import Moudle.Battle;
import Moudle.Card;
import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

import javax.swing.text.html.ListView;
import java.net.URL;
import java.util.ResourceBundle;

public class GraveYardfxml implements Initializable {
    public Button Back;
    public ScrollPane infoPane;
    public ScrollPane cardPane;
    public AnchorPane insideInfo;
    public AnchorPane insideCard;
    public ListView infoList;
    public ListView cardList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Button> card = FXCollections.observableArrayList();
//        for (Card cards : Battle.getCurrentBattle().ge)

        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {

            }
        };
        animationTimer.start();
    }
}
