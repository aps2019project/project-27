package View;

import Controller.ControlBox;
import Moudle.Account;
import Moudle.Shop;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Shopfxml implements Initializable {
    public Button Back;
    public Button Sell;
    public Button Buy;
    public Label label;
    public Label money;
    public TextField search;
    @FXML
    public Button hesan;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        hesan = new Button();
        hesan.setText("HESAN");
        hesan.setLayoutX(200.0);
        hesan.setLayoutY(200.0);
        ControlBox controlBox = new ControlBox();
        controlBox.setRegion("Shop");
        money.setText(String.format("%d", Account.getMainAccount().getMoney()));
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                Back.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Graphic.setRegion("MainMenu");
                    }
                });
                Buy.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        controlBox.setCardName(label.getText());
                        controlBox.setType("buy");
//                        Shop.buy(label.getText());
                        money.setText(String.format("%d", Account.getMainAccount().getMoney()));
                    }
                });
                Sell.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        controlBox.setCardName(label.getText());
                        controlBox.setType("sell");
//                        Shop.sell(label.getText());
                        money.setText(String.format("%d", Account.getMainAccount().getMoney()));
                    }
                });
                search.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        controlBox.setCardName(search.getText());
                        controlBox.setType("search");
//                        Shop.search(search.getText());
                    }
                });
            }
        };
        animationTimer.start();
    }
}
