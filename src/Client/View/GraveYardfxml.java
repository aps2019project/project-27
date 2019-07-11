package Client.View;

import Client.Controller.Controller;
import ControlBox.ControlBox;
import Server.Moudle.Battle;
import Server.Moudle.Card;
import Server.Moudle.MinionAndHero;
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

public class GraveYardfxml implements Initializable {
    public Button Back;
    public ScrollPane infoPane;
    public ScrollPane cardPane;
    public AnchorPane insideInfo;
    public AnchorPane insideCard;
    public ListView infoList;
    public ListView cardList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Battle battle = Battlefxml.getCurrentBattle ();
        ObservableList<Button> cards = FXCollections.observableArrayList();
        if ( battle.getPlayerInTurn().getGraveYard().size()!=0) {
            for (int i = 0; i < battle.getPlayerInTurn().getGraveYard().size(); i++) {
                cards.add(new Button(battle.getPlayerInTurn().getGraveYard().get(i).getName()));
            }
        }
        cardList.setItems(cards);
        insideCard.setPrefHeight(cardList.getPrefHeight());
        cardPane.setContent(cardList);

        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {

                if (battle.getPlayerInTurn().getGraveYard().size()!=0){
                    for (int i=0 ; i<cards.size() ; i++){
                        int finalI = i;
                        cards.get(i).setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                ArrayList<Card> cards1;
                                ControlBox controlBox = new ControlBox (  );
                                controlBox.setRegion ( "card" );
                                controlBox.setType ( "get" );
                                ControlBox answer = Controller.giveFromGraphic ( controlBox );
                                cards1 = answer.getCards ();
                                ObservableList<Label> info = FXCollections.observableArrayList();
                                info.add(new Label(cards.get(finalI).getText()));
                                if ( cards1.get(finalI).getCardType() == 1) {
                                    MinionAndHero minionAndHero = (MinionAndHero) cards1.get(finalI);
                                    if (minionAndHero.isHero()) {
                                        info.add(new Label("Hero"));
                                        info.add(new Label(String.format("AP : %d", minionAndHero.getAP())));
                                        info.add(new Label(String.format("HP : %d", minionAndHero.getHP())));
                                        if (minionAndHero.getAttackType() == 0) {
                                            info.add(new Label("melee"));
                                        } else if (minionAndHero.getAttackType() == 1) {
                                            info.add(new Label("ranged"));
                                        } else if (minionAndHero.getAttackType() == 3) {
                                            info.add(new Label("hybrid"));
                                        }
                                        info.add(new Label(String.format("Buy cost : %d", minionAndHero.getShopPrice())));
                                    } else {
                                        info.add(new Label("Minion"));
                                        info.add(new Label(String.format("AP : %d", minionAndHero.getAP())));
                                        info.add(new Label(String.format("HP : %d", minionAndHero.getHP())));
                                        info.add(new Label(String.format("MP : %d", minionAndHero.getManaPrice())));
                                        if (minionAndHero.getAttackType() == 0) {
                                            info.add(new Label("melee"));
                                        } else if (minionAndHero.getAttackType() == 1) {
                                            info.add(new Label("ranged"));
                                        } else if (minionAndHero.getAttackType() == 3) {
                                            info.add(new Label("hybrid"));
                                        }
                                        info.add(new Label(String.format("Buy cost : %d", minionAndHero.getShopPrice())));
                                    }
                                } else {
                                    info.add(new Label("Spell"));
                                    info.add(new Label(String.format("MP : %d", cards1.get(finalI).getManaPrice())));
                                    info.add(new Label(String.format("Buy Cost : %d", cards1.get(finalI).getShopPrice())));
                                }
                                infoList.setItems(info);
                                insideInfo.setPrefHeight(infoList.getPrefHeight());
                                insideInfo.setPrefWidth(infoList.getPrefWidth());
                                infoPane.setContent(infoList);
                            }
                        });
                    }
                }
                Back.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Graphic.setRegion("Battle");
                    }
                });
            }
        };
        animationTimer.start();
    }
}
