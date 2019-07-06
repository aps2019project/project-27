package Client.View;

import ControlBox.ControlBox;
import Server.Moudle.*;
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

public class Shopfxml implements Initializable {
    public Button Back;
    public Button Sell;
    public Button Buy;
    public Label selected;
    public Label money;
    public TextField search;
    public ListView listView;
    public ScrollPane scrollPane;
    public AnchorPane insideScrollPane;
    public ScrollPane information;
    public ListView informationList;
    public AnchorPane insideInformation;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Button[] cards = new Button[Card.getCards().size()];
        ObservableList<Button> buttons = FXCollections.observableArrayList();

        for (int i = 0; i < Card.getCards().size(); i++) {
            cards[i] = new Button(Card.getCards().get(i).getName());
            cards[i].setLayoutX(14.0);
            cards[i].setLayoutY(14.0 + 40.0 * i);
            buttons.add(cards[i]);
        }
        Button[] items = new Button[Item.getItems().size()];
        for (int i = 0; i < Item.getItems().size(); i++) {
            if (!Item.getItems().get(i).isCollectible()) {
                items[i] = new Button(Item.getItems().get(i).getName());
                items[i].setLayoutX(14.0);
                items[i].setLayoutY(cards[cards.length - 1].getLayoutY() + 40 * i);
                buttons.add(items[i]);
            }
        }
        listView.setItems(buttons);
        listView.setPrefHeight(items[items.length - 1].getLayoutY() + 20);
        insideScrollPane.setPrefHeight(listView.getPrefHeight());
        scrollPane.setContent(listView);

        ControlBox controlBox = new ControlBox();
        controlBox.setRegion("Shop");
        money.setText(String.format("%d", Account.getMainAccount().getMoney()));
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {

                for (int i = 0; i < cards.length; i++) {
                    int finalI = i;
                    cards[i].setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            selected.setText(cards[finalI].getText());
                            ObservableList<Label> info = FXCollections.observableArrayList();
                            info.add(new Label(cards[finalI].getText()));
                            if (Card.getCards().get(finalI).getCardType() == 1) {
                                MinionAndHero minionAndHero = (MinionAndHero) Card.getCards().get(finalI);
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
                                info.add(new Label(String.format("MP : %d", Card.getCards().get(finalI).getManaPrice())));
                                info.add(new Label(String.format("Buy Cost : %d", Card.getCards().get(finalI).getShopPrice())));
                            }
                            informationList.setItems(info);
                            insideInformation.setPrefHeight(informationList.getPrefHeight());
                            insideInformation.setPrefWidth(informationList.getPrefWidth());
                            information.setContent(informationList);
                        }
                    });
                }
                for (int i = 0; i < Item.getItems().size(); i++) {
                    if (!Item.getItems().get(i).isCollectible()) {
                        int finalI = i;
                        items[i].setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                selected.setText(items[finalI].getText());
                                ObservableList<Label> info = FXCollections.observableArrayList();
                                info.add(new Label(items[finalI].getText()));
                                info.add(new Label(String.format("Buy cost : %d", Item.getItems().get(finalI).getPrice())));
                                info.add(new Label(Item.getItems().get(finalI).getDescription()));
                                informationList.setItems(info);
                                insideInformation.setPrefHeight(informationList.getPrefHeight());
                                insideInformation.setPrefWidth(informationList.getPrefWidth());
                                information.setContent(informationList);
                            }
                        });
                    }
                }
                Back.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        for (Button card : buttons) {
                            card.setStyle("");
                        }
                        Graphic.setRegion("MainMenu");
                    }
                });
                Buy.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        for (Button card : buttons) {
                            card.setStyle("");
                        }
                        controlBox.setCardName(selected.getText());
                        controlBox.setType("buy");
//                        Shop.buy(label.getText());
                        Shop.input(controlBox);
                        money.setText(String.format("%d", Account.getMainAccount().getMoney()));
                    }
                });
                Sell.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        for (Button card : buttons) {
                            card.setStyle("");
                        }
                        controlBox.setCardName(selected.getText());
                        controlBox.setType("sell");
//                        Shop.sell(label.getText());
                        Shop.input(controlBox);
                        money.setText(String.format("%d", Account.getMainAccount().getMoney()));
                    }
                });
                search.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        for (Button card : buttons) {
                            card.setStyle("");
                        }
                        controlBox.setCardName(search.getText());
                        controlBox.setType("search");
//                        Shop.search(search.getText());
                        Shop.input(controlBox);
                        for (Button card : buttons) {
                            for (int i=1 ; i<search.getText().length() ; i++) {
                                if (card.getText().contains(search.getText().subSequence(0, i))) {
                                    card.setStyle("-fx-background-color: Blue;");
                                }
                            }
                        }
                    }
                });

            }
        };

        animationTimer.start();

    }
}
