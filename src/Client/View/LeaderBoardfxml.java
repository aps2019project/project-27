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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LeaderBoardfxml implements Initializable {

    public Button Back;
    public ScrollPane scroll;
    public AnchorPane insideList;
    public ListView list;
    ControlBox controlBox = new ControlBox();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        controlBox.setRegion("leader");
        ControlBox answer = Controller.giveFromGraphic(controlBox);
        update(answer.getLeaderBoard());

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

    public void update(ArrayList<String> a) {
        ObservableList<Label> observableList = FXCollections.observableArrayList();
        for (String s : a) {
            observableList.add(new Label(s));
        }
        list.setItems(observableList);
        insideList.setPrefHeight(list.getPrefHeight());
        scroll.setContent(list);
    }
}
