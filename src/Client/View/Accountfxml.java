package Client.View;

import Client.Controller.Controller;
import ControlBox.ControlBox;
import Server.Moudle.Account;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Accountfxml implements Initializable {
    public Button save;
    public Button logout;
//    public Button help;
    public Button createAccount;
    public Button login;
    public Button showLeaderBoard;
    public TextField userName;
    public TextField passWord;
    public Label user;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ControlBox controlBox = new ControlBox();
        controlBox.setRegion("Account");
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (getMainAccount () != null) {
                    user.setText(getMainAccount ().getUserName());
                } else {
                    user.setText("");
                }
                createAccount.setOnAction(event -> {
                    if (isValidPress(userName, passWord)) {
                        controlBox.setUserName(userName.getText());
                        controlBox.setPass(passWord.getText());
                        controlBox.setType("create account");
                        ControlBox answer = Controller.giveFromGraphic(controlBox);

                        if (answer.isSucces()) {
                            Graphic.setRegion("MainMenu");
//                            Account.input(controlBox);
                        }
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText("username or password is not valid");
                        alert.setContentText("");
                        alert.showAndWait();
                    }

                });
                login.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        controlBox.setUserName(userName.getText());
                        controlBox.setPass(passWord.getText());
                        controlBox.setType("login");
                        ControlBox answer = Controller.giveFromGraphic(controlBox);
                        if (answer.isSucces()) {
                            Graphic.setRegion("MainMenu");
                        }
//                        Account.input(controlBox);
                    }
                });
                save.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        controlBox.setType("save");
                        Account.input(controlBox,null);
                        //todo
                    }
                });
                logout.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("logout3");
                        controlBox.setType("logout");
                        Account.input(controlBox,null);
                        // TODO: 7/6/2019
                    }
                });
                showLeaderBoard.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Graphic.setRegion("LeaderBoard");
                    }
                });
//                help.setOnAction(new EventHandler<ActionEvent>() {
//                    @Override
//                    public void handle(ActionEvent event) {
//                        Graphic.setRegion("HelpAccount");
//                    }
//                });

            }
        };
        animationTimer.start();
    }
    private static Account getMainAccount(){
        ControlBox controlBox = new ControlBox (  );
        controlBox.setRegion ( "Client" );
        controlBox.setType ( "getMainAccount" );
        ControlBox answer = Controller.giveFromGraphic ( controlBox );
        return answer.getAccount();
    }
    private static boolean isValidPress(TextField userName, TextField passWord) {
        return !(passWord.getText().isEmpty() && userName.getText().isEmpty());
    }
}
