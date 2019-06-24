package View;

import Controller.ControlBox;
import Moudle.Collection;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Collectionfxml implements Initializable {

    public Button back;
    public Button save;
    public Button createDeck;
    public Button addToDeck;
    public Button removeFromDeck;
    public Button deleteDeck;
    public Button validateDeck;
    public Label cardSelected;
    public Label deckSelected;
    public TextField text;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ControlBox controlBox = new ControlBox();
        controlBox.setRegion("Collection");
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                back.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Graphic.setRegion("MainMenu");
                    }
                });
                save.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Collection.save();
                    }
                });
                createDeck.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        controlBox.setDeckName(text.getText());
                        controlBox.setType("createDeck");
                    }
                });
                addToDeck.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        controlBox.setCardName(cardSelected.getText());
                        controlBox.setDeckName(deckSelected.getText());
                        controlBox.setType("add");
                    }
                });
                removeFromDeck.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        controlBox.setCardName(cardSelected.getText());
                        controlBox.setDeckName(deckSelected.getText());
                        controlBox.setType("remove");
                    }
                });
                deleteDeck.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        controlBox.setDeckName(deckSelected.getText());
                        controlBox.setType("deleteDeck");
                    }
                });
                validateDeck.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        controlBox.setDeckName(deckSelected.getText());
                        controlBox.setType("validateDeck");
                    }
                });
            }
        };
        animationTimer.start();
    }
}
