package Client.View;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class HelpAccountfxml implements Initializable {
    public Button Back;
    public Label help;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        help.setText("Create account: To create an account enter your username\nand password and then click create account\n" +
                "Login: To login enter your username and password\nand then click login\nSave: To save your progress you have to come\n" +
                "to this page and click save\nLogout: To logout of your account click logout");
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
