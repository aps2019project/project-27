package View;

import Moudle.Account;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class LeaderBoardfxml implements Initializable {

    public Label label;
    public Button Back;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        label.setText(Account.showLeaderBoard());
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                Back.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Graphic.setRegion("Account");
                    }
                });
            }
        };
        animationTimer.start();
    }
}
