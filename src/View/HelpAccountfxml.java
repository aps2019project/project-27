package View;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class HelpAccountfxml implements Initializable {
    public Button Back;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
