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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Chatfxml implements Initializable {

    public ListView list;
    public Button back;
    public Button send;
    public TextField text;
    public AnchorPane insideList;
    public ScrollPane Scroll;
    public ControlBox controlBox = new ControlBox();

    public Account getMainAccount() {
        return Accountfxml.getMainAccount();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                controlBox.setRegion("chat");
                controlBox.setType("get");
                ControlBox answer = Controller.giveFromGraphic(controlBox);
                update(answer.getMessages());
                back.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Graphic.setRegion("MainMenu");
                    }
                });
                send.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        controlBox.setRegion("chat");
                        controlBox.setType("send");
                        controlBox.setLabel(getMainAccount().getUserName() + ": " + text.getText());
                        Controller.giveFromGraphic(controlBox);
                        controlBox.setType("get");
                        ControlBox answer = Controller.giveFromGraphic(controlBox);
                        update(answer.getMessages());
                        text.clear();
                    }
                });
            }
        };
        animationTimer.start();
    }
    private void update(ArrayList<String> list) {
        ObservableList<Label> list1 = FXCollections.observableArrayList();
        for (String label : list) {
            list1.add(new Label(label));
        }
        this.list.setItems(list1);
        insideList.setPrefHeight(this.list.getPrefHeight());
        Scroll.setContent(this.list);
    }
}
