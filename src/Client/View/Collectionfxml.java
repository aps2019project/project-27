package Client.View;

import ControlBox.ControlBox;
import Server.Moudle.Account;
import Server.Moudle.Collection;
import Server.Moudle.Deck;
import Server.Moudle.MinionAndHero;
import com.gilecode.yagson.YaGson;
import com.gilecode.yagson.YaGsonBuilder;
import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Collectionfxml implements Initializable {

    public Button back;
    public Button createDeck;
    public Button addToDeck;
    public Button removeFromDeck;
    public Button deleteDeck;
    public Button validateDeck;
    public Label cardSelected;
    public Label deckSelected;
    public TextField text;
    public ListView cardList;
    public ListView deckList;
    public ScrollPane cardPane;
    public ScrollPane deckPane;
    public AnchorPane insideCard;
    public AnchorPane insideDeck;
    public ListView infoList;
    public ScrollPane infoPane;
    public AnchorPane insideInfo;
    public TextField fileName;
    public Button impor;
    public Button export;
    private Deck selectedDeck;
    public ObservableList<Button> decks = FXCollections.observableArrayList();
    public Button mainDeck;

    public Account getMainAccount() {
        ControlBox controlBox = new ControlBox();
        return controlBox.getAccount();
    }

    public ArrayList<Deck> getDecks(){
        ControlBox controlBox = new ControlBox();
        return controlBox.getDecks();
    }

    public void showDecks() {
        for (int i = 0; i < getMainAccount().getDecks().size(); i++) {
            decks.add(new Button(getMainAccount().getDecks().get(i).getName()));
            decks.get(i).setLayoutX(146.0);
            decks.get(i).setLayoutY(53.0 + 40.0 * i);
        }
        deckList.setItems(decks);
        insideDeck.setPrefHeight(deckList.getPrefHeight());
        deckPane.setContent(deckList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (getMainAccount().getDecks().size() > 0) {
            showDecks();
        }
        Button[] cards = new Button[getMainAccount().getCollection().getCards().size()];
        ObservableList<Button> buttons = FXCollections.observableArrayList();

        for (int i = 0; i < getMainAccount().getCollection().getCards().size(); i++) {
            cards[i] = new Button(getMainAccount().getCollection().getCards().get(i).getName());
            cards[i].setLayoutX(14.0);
            cards[i].setLayoutY(53.0 + 40.0 * i);
            buttons.add(cards[i]);
        }
        Button[] items = new Button[getMainAccount().getCollection().getItems().size()];
        for (int i = 0; i < getMainAccount().getCollection().getItems().size(); i++) {
            items[i] = new Button(getMainAccount().getCollection().getItems().get(i).getName());
            items[i].setLayoutX(14.0);
            items[i].setLayoutY(cards[cards.length - 1].getLayoutY() + 40 * i);
            buttons.add(items[i]);
        }
        cardList.setItems(buttons);
        insideCard.setPrefHeight(cardList.getPrefHeight());
        cardPane.setContent(cardList);

        ControlBox controlBox = new ControlBox();
        controlBox.setRegion("Collection");
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                mainDeck.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Deck deck = null;
                        for (Deck deck1 : getMainAccount().getDecks()) {
                            if (deckSelected.getText().equals(deck1.getName()))
                                deck = deck1;
                        }
                        getMainAccount().setMainDeck(deck);
                    }
                });
                impor.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        String name = fileName.getText() + ".json";
                        File file = new File(fileName.getText() + ".json");
                        if (file.exists()) {
                            System.out.println("already exist");
                        } else {
                            YaGsonBuilder gsonBuilder = new YaGsonBuilder();
                            YaGson gson = gsonBuilder.create();
                            FileWriter fileWriter = null;
                            try {
                                fileWriter = new FileWriter(fileName.getText() + ".json");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            if (!deckSelected.getText().isEmpty()) {
                                for (Deck deck : getMainAccount().getDecks()) {
                                    if (deck.getName().equals(deckSelected.getText()))
                                        selectedDeck = deck;
                                }
                                String json = gson.toJson(selectedDeck);
                                try {
                                    fileWriter.write(json);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    fileWriter.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                //todo
                            }

                        }
                    }
                });
                export.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        File file = new File(fileName.getText() + ".json");
                        if (file.exists()) {
                            Deck deck = null;
                            YaGsonBuilder gsonBuilder = new YaGsonBuilder ();
                            YaGson gson = gsonBuilder.create();
                            try {
                                deck = gson.fromJson(new FileReader(file), Deck.class);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                            getMainAccount().getDecks().add(deck);
                            Graphic.setRegion("");
                            Graphic.setRegion("Collection");
                        } else {
                            //todo
                        }
                    }
                });
                for (int i = 0; i < cards.length; i++) {
                    int finalI = i;
                    cards[i].setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            cardSelected.setText(cards[finalI].getText());
                            ObservableList<Label> info = FXCollections.observableArrayList();
                            info.add(new Label(cards[finalI].getText()));
                            if (getMainAccount().getCollection().getCards().get(finalI).getCardType() == 1) {
                                MinionAndHero minionAndHero = (MinionAndHero) getMainAccount().getCollection().getCards().get(finalI);
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
                                    info.add(new Label(String.format("Sell cost : %d", minionAndHero.getShopPrice())));
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
                                    info.add(new Label(String.format("Sell cost : %d", minionAndHero.getShopPrice())));
                                }
                            } else {
                                info.add(new Label("Spell"));
                                info.add(new Label(String.format("MP : %d", getMainAccount().getCollection().getCards().get(finalI).getManaPrice())));
                                info.add(new Label(String.format("Sell Cost : %d", getMainAccount().getCollection().getCards().get(finalI).getShopPrice())));
                            }
                            infoList.setItems(info);
                            insideInfo.setPrefHeight(infoList.getPrefHeight());
                            insideInfo.setPrefWidth(infoList.getPrefWidth());
                            infoPane.setContent(infoList);
                        }
                    });
                }
                for (int i = 0; i < items.length; i++) {
                    int finalI = i;
                    items[i].setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            cardSelected.setText(items[finalI].getText());
                            ObservableList<Label> info = FXCollections.observableArrayList();
                            info.add(new Label(items[finalI].getText()));
                            info.add(new Label(String.format("Sell cost : %d", getMainAccount().getCollection().getItems().get(finalI).getPrice())));
                            info.add(new Label(getMainAccount().getCollection().getItems().get(finalI).getDescription()));
                            infoList.setItems(info);
                            insideInfo.setPrefHeight(infoList.getPrefHeight());
                            insideInfo.setPrefWidth(infoList.getPrefWidth());
                            infoPane.setContent(infoList);
                        }
                    });
                }
                if (getMainAccount().getDecks().size() > 0) {
                    for (int i = 0; i < getMainAccount().getDecks().size(); i++) {
                        int finalI = i;
                        int size = getMainAccount().getDecks().size();
                        if (i < decks.size()) {
                            decks.get(i).setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    deckSelected.setText(decks.get(finalI).getText());
                                    ObservableList<Label> info = FXCollections.observableArrayList();
                                    for (int j = 0; j < getMainAccount().getDecks().get(finalI).getCards().size(); j++) {
                                        info.add(new Label(getMainAccount().getDecks().get(finalI).getCards().get(j).getName()));
                                        if (getMainAccount().getDecks().get(finalI).getItem() != null) {
                                            info.add(new Label(getMainAccount().getDecks().get(finalI).getItem().getName()));
                                        }
                                    }
                                    infoList.setItems(info);
                                    insideInfo.setPrefHeight(infoList.getPrefHeight());
                                    insideInfo.setPrefWidth(infoList.getPrefWidth());
                                    infoPane.setContent(infoList);
                                }
                            });
                        }
                    }
                }
                back.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Graphic.setRegion("MainMenu");
                    }
                });
                createDeck.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        controlBox.setDeckName(text.getText());
                        controlBox.setType("createDeck");
                        boolean found = false;
                        for (Button button : decks) {
                            if (button.getText().equals(text.getText())) {
                                found = true;
                            }
                        }
                        if (found == false) {
                            decks.add(new Button(text.getText()));
                        }
                        Collection.input(controlBox);
                    }
                });
                addToDeck.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        controlBox.setCardName(cardSelected.getText());
                        controlBox.setDeckName(deckSelected.getText());
                        controlBox.setType("add");
                        Collection.input(controlBox);
                    }
                });
                removeFromDeck.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        controlBox.setCardName(cardSelected.getText());
                        controlBox.setDeckName(deckSelected.getText());
                        controlBox.setType("remove");
                        Collection.input(controlBox);
                    }
                });
                deleteDeck.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        controlBox.setDeckName(deckSelected.getText());
                        controlBox.setType("deleteDeck");
                        Collection.input(controlBox);
                        for (int i = 0; i < decks.size(); i++) {
                            if (decks.get(i).getText().equals(controlBox.getDeckName())) {
                                decks.remove(i);
                            }
                        }
                    }
                });
                validateDeck.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        controlBox.setDeckName(deckSelected.getText());
                        controlBox.setType("validateDeck");
                        Collection.input(controlBox);

                    }
                });
            }
        };
        animationTimer.start();
    }

    public void update() {
        deckList.getItems().clear();
        decks.clear();
        for (int i = 0; i < getMainAccount().getDecks().size(); i++) {
            decks.add(new Button(getMainAccount().getDecks().get(i).getName()));
        }
        deckList.setItems(decks);
        insideDeck.setPrefHeight(deckList.getPrefHeight());
        deckPane.setContent(deckList);
    }
}
