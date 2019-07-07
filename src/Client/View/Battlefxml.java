package Client.View;

import Client.Controller.Controller;
import ControlBox.ControlBox;
import Server.Moudle.*;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Battlefxml implements Initializable {
    public Label name;
    public Label mana;
    public Button grave;
    public Label detail;
    public ArrayList<cardGroup> cardGroups = new ArrayList<>();
    public Button endTurn;
    public ImageView img;
    public AnchorPane mainPane;
    public Button heroPower;
    private boolean fighterSelected = false;
    private boolean hendSelected = false;
    public Pane[][] panes = new Pane[5][9];
    public Pane[] handPane = new Pane[6];

    public static void winner() {
        Graphic.setRegion("endBattle");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        preProccess();
        try {
            update();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: Blue;");
        mainPane.getChildren().add(new Label("asdsadad"));
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                heroPower.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        ControlBox controlBox = new ControlBox();
                        controlBox.setRegion("Battle");
                        controlBox.setType("use special power");
                        controlBox.setX(0);
                        controlBox.setY(0);
                        Controller.giveFromGraphic(controlBox);
                    }
                });
                for (int i = 0; i < 5; i++) {
                    int finalI = i;
                    int finalI1 = i;
                    handPane[i].setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            ControlBox controlBox = new ControlBox();
                            controlBox.setType("insert select");
                            controlBox.setRegion("Battle");
                            controlBox.setN(finalI);
                            ControlBox answer = Controller.giveFromGraphic(controlBox);
                            hendSelected = true;
                            handPane[finalI1].setStyle("-fx-background-color: Red;");
                        }
                    });
                }
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 9; j++) {
                        Pane pane1 = panes[i][j];
                        int finalI = i;
                        int finalJ = j;
                        pane1.setOnMouseClicked(event -> {
                            if (hendSelected) {
                                ControlBox controlBox = new ControlBox();
                                controlBox.setRegion("Battle");
                                controlBox.setType("insert");
                                controlBox.setX(finalI);
                                controlBox.setY(finalJ);
                                ControlBox answer = Controller.giveFromGraphic(controlBox);
                                if (answer.isSucces()) {
                                    try {
                                        update();
                                    } catch (FileNotFoundException e) {
                                        e.printStackTrace();
                                    }
                                    hendSelected = true;
                                }
                            } else {
                                if (!fighterSelected) {
                                    ControlBox controlBox = new ControlBox();
                                    controlBox.setRegion("Battle");
                                    controlBox.setType("select card");
                                    controlBox.setX(finalI);
                                    controlBox.setY(finalJ);
                                    ControlBox answer = Controller.giveFromGraphic(controlBox);
                                    if (answer.isSucces()) {
                                        pane1.setStyle("-fx-background-color: Red;");
                                        fighterSelected = true;
                                    }
                                } else {
                                    if (Battle.getCurrentBattle().getGround().getCell(finalI, finalJ).getCardOnCell() == null) {
                                        ControlBox controlBox = new ControlBox();
                                        controlBox.setRegion("Battle");
                                        controlBox.setType("move");
                                        controlBox.setX(finalI);
                                        controlBox.setY(finalJ);
                                        ControlBox answer = Controller.giveFromGraphic(controlBox);
                                        if (answer.isSucces()) {
                                            fighterSelected = false;
                                            try {
                                                update();
                                            } catch (FileNotFoundException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    } else {
                                        ControlBox controlBox = new ControlBox();
                                        controlBox.setRegion("Battle");
                                        controlBox.setX(finalI);
                                        controlBox.setY(finalJ);
                                        controlBox.setType("attack");
                                        ControlBox answer = Controller.giveFromGraphic(controlBox);
                                        if (answer.isSucces()) {
                                            try {
                                                update();
                                            } catch (FileNotFoundException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                                }
                            }
                        });
                        grave.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {

                                Graphic.setRegion("grave");
                            }
                        });
                    }
                }
                endTurn.setOnAction(event -> {
                    ControlBox controlBox = new ControlBox();
                    controlBox.setRegion("Battle");
                    controlBox.setType("end turn");
                    ControlBox answer = Controller.giveFromGraphic(controlBox);
//					if ( answer.isSucces () ){
//						Graphic.setRegion ( "endBattle" );
//					}
                    try {
                        update();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                });
            }
        };
        animationTimer.start();
    }

    private void preProccess() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                panes[i][j] = new Pane();
                panes[i][j].relocate(j * 140, i * 140);
                panes[i][j].setPrefWidth(140);
                panes[i][j].setPrefHeight(140);
                panes[i][j].resize(140, 140);
                panes[i][j].setPrefSize(140, 140);
                panes[i][j].setStyle("-fx-background-color: Orange;");
                panes[i][j].toBack();
                mainPane.getChildren().add(panes[i][j]);
            }
        }
        for (int i = 0; i < 6; i++) {
            handPane[i] = new Pane();
            handPane[i].setPrefWidth(140);
            handPane[i].setPrefHeight(140);
            handPane[i].relocate(180 + 140 * i, 720);
            handPane[i].resize(140, 140);
            handPane[i].setPrefSize(140, 140);
            handPane[i].setStyle("-fx-background-color: Orange;");
            mainPane.getChildren().add(handPane[i]);
        }
        for (int i = 1; i < 5; i++) {
            Rectangle rectangle = new Rectangle();
            rectangle.height = 1260;
            rectangle.width = 20;
            rectangle.setLocation(i * 140 - 10, 20);
            Pane pane = new Pane();
            pane.relocate(1260, 20);
            pane.setPrefHeight(i * 140 - 10);
            pane.setPrefWidth(20);
            pane.setStyle("-fx-background-color: Blue;");
            mainPane.getChildren().add(pane);
        }
    }

    public void update() throws FileNotFoundException {
        hendSelected = false;
        fighterSelected = false;
        for (cardGroup cardGroup : cardGroups)
            mainPane.getChildren().removeAll(cardGroup.getNodes());
        cardGroups.clear();
        Battle battle = Battle.getCurrentBattle();
        switch (battle.getBattleType()) {
            case 1:
                detail.setText("flags you collect" + String.valueOf(battle.getPlayerInTurn().getFlagInHand()));
                break;
            case 0:
                if (battle.getPlayerInTurn() == battle.getPlayer1())
                    detail.setText("enemy hero ap" + (battle.getHeroP2().getHP()));
                else
                    detail.setText("enemy hero ap" + String.valueOf(battle.getHeroP1().getHP()));
                break;
            case 2:
                detail.setText("turns you have flag" + String.valueOf(battle.getPlayerInTurn().getFlagInHand()));
                break;
        }
        Player player = battle.getPlayerInTurn();
        name.setText(player.getUserName());
        mana.setText(String.valueOf(player.getMana()));
        Cell[][] cell = battle.getGround().getCells();
        ArrayList<Card> cards = battle.getPlayerInTurn().getHand().getCards();
        int a = 1;
        for (int n = 0; n < cards.size(); n++) {
            Card card = cards.get(n);
            cardGroup cardGroup = new cardGroup(0, 0, card, true, n);
            cardGroups.add(cardGroup);
            mainPane.getChildren().addAll(cardGroup.getNodes());
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                panes[i][j].setStyle("-fx-background-color: Orange;");
                if (cell[i][j].getItemOnCell() != null) {
                    Item item = cell[i][j].getItemOnCell();
                    cardGroup cardGroup = new cardGroup(i, j, item, false, 0);
                    cardGroups.add(cardGroup);
                    mainPane.getChildren().addAll(cardGroup.getNodes());
                }
                if (cell[i][j].getCardOnCell() != null) {
                    cardGroup cardGroup = new cardGroup(i, j, (Card) cell[i][j].getCardOnCell(), false, 0);
                    cardGroups.add(cardGroup);
                    mainPane.getChildren().addAll(cardGroup.getNodes());

                }
            }
        }
        Card next = battle.getPlayerInTurn().getHand().getNextCard();
        if (next != null) {
            cardGroup cardGroup = new cardGroup(0, 0, next, true, 5);
            cardGroups.add(cardGroup);
            mainPane.getChildren().addAll(cardGroup.getNodes());
        }
    }
}

class cardGroup {
    private ImageView imageView;
    private Card card;
    private ArrayList<Node> nodes = new ArrayList<>();
    private int x;
    private int y;
    private int i;
    private boolean isHand = false;

    public cardGroup(int x, int y, Card card, boolean isHand, int i) {
        this.card = card;
        newGroup(x, y, isHand, i, card.getName());
        File file;
        if (card.getImage() == null || card.getImage().isEmpty())
            file = new File("src\\View\\images\\DiveSepid.gif");
        else
            file = new File(card.getImage());
        try {
            this.imageView.setImage(new Image(new FileInputStream(file)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void newGroup(int x, int y, boolean isHand, int i, String name) {
        imageView = new ImageView();
        if (isHand) {
            imageView.relocate(150 + 140 * i, 720);
        } else {
            imageView.relocate(y * 140, x * 140);
        }
        imageView.setFitHeight(140);
        imageView.setFitWidth(140);
        this.x = x;
        this.y = y;
        this.i = i;
        Label AP = new Label();
        Label HP = new Label();
        Label nameLable = new Label();
        nameLable.setText(name);
        if (isHand) {
            nameLable.relocate(150 + 140 * i + 50, 880);
        } else {
            nameLable.relocate(y * 140 + 50, x * 140 + 120);
        }
        if (card != null) {
            if (isHand) {
                if (card.getCardType() == 1) {
                    MinionAndHero minionAndHero = (MinionAndHero) card;
                    HP = new Label(String.valueOf(minionAndHero.getHP()));
                    AP = new Label(String.valueOf(minionAndHero.getAP()));

                }
            } else {
                Fighter fighter = (Fighter) card;
                HP = new Label(String.valueOf(fighter.getHP()));
                AP = new Label(String.valueOf(fighter.getAP()));
            }
            if (isHand) {
                AP.relocate(150 + 140 * i, 820);
                HP.relocate(150 + 140 * i + 100, 820);
            } else {
                AP.relocate(y * 140, x * 140 + 100);
                HP.relocate(y * 140 + 100, x * 140 + 100);
            }
        }
        nodes.add(nameLable);
        nodes.add(imageView);
        nodes.add(AP);
        nodes.add(HP);
    }

    public cardGroup(int x, int y, Item item, boolean isHand, int i) {
        newGroup(x, y, isHand, i, item.getName());
        File file;
        if (item.getImage() == null || item.getImage().isEmpty())
            file = new File("src\\View\\images\\images.png");
        else
            file = new File(item.getImage());
        try {
            this.imageView.setImage(new Image(new FileInputStream(file)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }
}