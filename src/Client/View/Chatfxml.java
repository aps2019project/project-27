package Client.View;

import Client.Controller.Controller;
import ControlBox.ControlBox;
import Server.Moudle.Account;
import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class Chatfxml implements Initializable {

    public ListView list;
    public Button back;
    public Button send;
    public TextField text;
    public AnchorPane insideList;
    public ScrollPane Scroll;

    public Account getMainAccount() {
        return Accountfxml.getMainAccount();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                back.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Graphic.setRegion("MainMenu");
                    }
                });
                send.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        ControlBox controlBox = new ControlBox();
                        controlBox.setRegion("chat");
                        controlBox.messages.add(new Label(getMainAccount().getUserName() + ": " + text.getText()));
                        Controller.giveFromGraphic(controlBox);
                        text.clear();
                    }
                });
                list.setItems(getMainAccount().getMessages());
                insideList.setPrefHeight(list.getPrefHeight());
                Scroll.setContent(list);
            }
        };
        animationTimer.start();
    }

}
