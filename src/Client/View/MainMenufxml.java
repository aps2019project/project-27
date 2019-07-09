package Client.View;

import Client.Controller.Controller;
import ControlBox.ControlBox;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenufxml implements Initializable {
    public Button Account;
    public Button CostumeCard;
    public Button Battle;
    public Button Shop;
    public Button Exit;
    public Button Collection;
    public Label AccountLable;
    public Button Help;
    public Button save;
    public Button chat;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (Server.Moudle.Account.getMainAccount() != null) {
                    AccountLable.setText(Accountfxml.getMainAccount().getUserName());
                }
                Account.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Graphic.setRegion("Account");
                    }
                });
                Help.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Graphic.setRegion("HelpMenu");
                    }
                });
                save.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        ControlBox controlBox = new ControlBox (  );
                        controlBox.setRegion ( "save" );
                        Controller.giveFromGraphic ( controlBox );
                    }
                });
                Collection.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Graphic.setRegion("Collection");
                    }
                });
                Shop.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Graphic.setRegion("Shop");
                    }
                });
                Battle.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Graphic.setRegion("BattleMenu");
                    }
                });
                CostumeCard.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Graphic.setRegion("CostomCard");
                    }
                });
                chat.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Graphic.setRegion("Chat");
                    }
                });
            }
        };
        animationTimer.start();
    }

    ;

    @FXML
    private void closeButtonAction() {
        Stage stage = (Stage) Exit.getScene().getWindow();
        stage.close();
    }
}
