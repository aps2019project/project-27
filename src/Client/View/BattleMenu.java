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
    public ToggleGroup mode;
    public Button menu;
    public TextField number;
    public Button matchMaking;
    public Button check;
    private boolean wait = false;
    public Button visit;
    public Button cancel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                cancel.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        ControlBox controlBox = new ControlBox();
                        controlBox.setRegion("Client");
                        controlBox.setType("matchMaking");
                        controlBox.setDescription("cancel");
                        Controller.giveFromGraphic(controlBox);
                    }
                });
                visit.setOnAction ( new EventHandler<ActionEvent> ( ) {
                    @Override
                    public void handle ( ActionEvent event ) {
                        ControlBox controlBox = new ControlBox (  );
                        controlBox.setRegion ( "Client" );
                        controlBox.setType ( "matchMaking" );
                        controlBox.setDescription ( "visit" );
                    }
                } );
                matchMaking.setOnAction(event -> {
                    ControlBox controlBox = new ControlBox();
                    controlBox.setRegion("Client");
                    controlBox.setType("matchMaking");
                    if (hero.isSelected())
                        controlBox.setBattleType(0);
                    else if (flags.isSelected())
                        controlBox.setBattleType(1);
                    else if (oneFlag.isSelected())
                        controlBox.setBattleType(2);
                    controlBox.setNumberOfFlags(Integer.parseInt(number.getText()));
                    wait = true;
                    ControlBox answer = Controller.giveFromGraphic(controlBox);
                });
                check.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if (!wait) {

                        } else {
                            ControlBox controlBox = new ControlBox();
                            controlBox.setType("matchMaking");
                            controlBox.setRegion("Client");
                            controlBox.setDescription("check");
                            ControlBox answer = Controller.giveFromGraphic(controlBox);
                        }
                    }
                });
                menu.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Graphic.setRegion("MainMenu");
                    }
                });
            }
        };
        animationTimer.start();

    }
}
