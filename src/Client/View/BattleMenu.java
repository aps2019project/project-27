package Client.View;

import Client.Controller.Controller;
import ControlBox.ControlBox;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class BattleMenu implements Initializable {
    public RadioButton hero;
    public RadioButton oneFlag;
    public RadioButton flags;
    public Button start;
    public TextField secondPlayer;
    public ToggleGroup mode;
    public Button menu;
    public Button select;
    public Label secondPlayerName;
    public TextField number;
    private boolean selected = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                menu.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Graphic.setRegion("MainMenu");
                    }
                });
                start.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if (true) {
                            ControlBox controlBox = new ControlBox();
                            controlBox.setRegion("Battle");
                            controlBox.setType("new mp");
                            controlBox.setN(Integer.parseInt(number.getText()));
                            if (hero.isSelected())
                                controlBox.setBattleType(0);
                            else if (flags.isSelected())
                                controlBox.setBattleType(1);
                            else if (oneFlag.isSelected())
                                controlBox.setBattleType(2);
                            ControlBox answer = Controller.giveFromGraphic(controlBox);
                            if (answer.isSucces()) {
                                Graphic.setRegion("Battle");
                            }
                        }
                    }
                });
                select.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        ControlBox controlBox = new ControlBox();
                        controlBox.setRegion("Battle");
                        controlBox.setType("select user");
                        controlBox.setUserName(secondPlayer.getText());
                        ControlBox answer = Controller.giveFromGraphic(controlBox);
                        if (answer.isSucces()) {
                            secondPlayerName.setText(secondPlayer.getText());
                            secondPlayer.setText("");
                        }
                    }
                });
            }
        };
        animationTimer.start();

    }

    public boolean validStart() {
        if (!selected)
            return false;
//		return ( hero.isSelected ( ) ) || oneFlag.isSelected ( ) || flags.isSelected ( );
        return true;
    }
}
