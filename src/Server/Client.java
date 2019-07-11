package Server;

import ControlBox.ControlBox;
import Server.Moudle.*;
import com.gilecode.yagson.YaGson;
import com.gilecode.yagson.YaGsonBuilder;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class Client implements Runnable {
    private static ArrayList<Client> clients = new ArrayList<>();
    private Account account;
    private Battle battle;
    private Socket socket;
    private Scanner scanner;
    private Formatter formatter;
    private static ArrayList<String> list = new ArrayList<>();
    private static YaGson yaGson;
    private String winner;
    private String gift;
    private boolean isWating;

    static {
        YaGsonBuilder yaGsonBuilder = new YaGsonBuilder();
        yaGson = yaGsonBuilder.create();
    }

    public static YaGson getYaGson() {
        return yaGson;
    }

    public Client(Socket socket) {
        this.socket = socket;
        clients.add(this);
        try {
            scanner = new Scanner(socket.getInputStream());
            formatter = new Formatter(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Battle getBattle() {
        return battle;
    }

    public void setBattle(Battle battle) {
        this.battle = battle;
    }

    public Socket getSocket() {
        return socket;
    }

    public Account getAccount() {
        return account;
    }

    public static ArrayList<Client> getClients() {
        return clients;
    }

    public ControlBox recieve() {
        YaGson gson = getYaGson();
        String get = "";
        while (true) {
            if (scanner.hasNextLine()) {
                get = scanner.nextLine();
                break;
            }
        }
        ControlBox controlBox = gson.fromJson(get, ControlBox.class);
        return controlBox;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void send(ControlBox controlBox) {
        YaGson gson = getYaGson();
        String str = gson.toJson(controlBox);
        formatter.format(str + "\n");
        formatter.flush();
    }

    public static void addToList(String name) {
        list.add(name);
    }

    public static void removeFromList(String name) {
        list.remove(name);
    }

    public static boolean isLogin(String name) {
        return list.contains(name);
    }

    @Override
    public void run() {
        int a = 1;
        while (true) {
            if (battle != null && battle.isEnd()) {
                this.winner = battle.getWinner();
                this.gift = String.valueOf(battle.getGift());
                battle = null;
            }
            if (!socket.isConnected()) {
                clients.remove(this);
                return;
            }
            ControlBox controlBox = this.recieve();
            ControlBox answer = new ControlBox();
            switch (controlBox.getRegion()) {
                case "Account":
                    answer = Account.input(controlBox, this);
                    break;
                case "Battle":
                    answer = Battle.input(controlBox, this);
                    //	Objects.requireNonNull ( answer ).setBattle ( battle );
                    answer.setBattle(battle);
                    break;
                case "add":
                    System.out.println("inside client add");
                    switch (controlBox.getType()) {
                        case "Card":
//                            Card.addToCards(controlBox.getCard());
                            if (Card.getCards() == null) {
                                System.out.println("cards are null");
                            }
                            answer.setCards(Card.getCards());
                            break;
                        case "Item":
                            if (Item.getItems() == null) {
                                System.out.println("items are null");
                            }
                            answer.setItems(Item.getItems());
                    }
                    int c = 1;
                    break;
                case "addToCollection":
                    switch (controlBox.getType()) {
                        case "Card":
                            answer.setCards(Account.getMainAccount().getCollection().getCards());
                        case "Item":
                            answer.setItems(Account.getMainAccount().getCollection().getItems());
                    }
                case "save":
                    Account.save();
                    try {
                        Load.saveMAndH();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "Client":
                    System.out.println("inside client client");
                    String type = controlBox.getType();
                    switch (type) {
                        case "detail":
                            answer.setDescription(winner);
                            answer.setType(gift);
                            break;
                        case "getMainAccount":
                            answer.setAccount(this.account);
                            break;
                        case "getCurrentBattle":
                            answer.setBattle(battle);
                            answer.setPass("battle");
                            break;
                        case "matchMaking":
                            if (controlBox.getDescription() != null && controlBox.getDescription().equals("check")) {
                                if (this.battle != null) {
                                    answer.setSucces(true);
                                    answer.setType("matchMaking");
                                    answer.setPass("finishwait");
                                }
                            } else if (controlBox.getDescription() != null && controlBox.getDescription().equals("cancel")) {
                                waitForBattle.remove(this);
                            } else {
                                answer.setType("matchMaking");
                                waitForBattle waitForBattle = Server.waitForBattle.find(controlBox.getBattleType(), controlBox.getNumberOfFlags());
                                if (waitForBattle == null) {
                                    new waitForBattle(controlBox.getNumberOfFlags(), controlBox.getBattleType(), this);
                                    answer.setDescription("wait");
                                    answer.setSucces(false);
                                    this.isWating = true;
                                } else {
                                    answer.setSucces(Battle.newOnlineBattle(waitForBattle.getClient(), this, controlBox.getBattleType(), controlBox.getNumberOfFlags()));
                                    //waitForBattle.getClient ( ).send ( answer );
                                    waitForBattle.getClient().isWating = false;
                                    this.isWating = false;
                                }
                            }
                            break;
                    }
                    break;
                case "mainAccount":
                    switch (controlBox.getType()) {
                        case "money":
                            answer.setMoney(Account.getMainAccount().getMoney());
                    }
                case "chat":
                    if (controlBox.getType().equals("send")) {
                        String label = controlBox.getLabel();
                        ArrayList<String> l = Account.getMessages();
                        l.add(label);
                    }
                    else{
                        answer.setMessages(Account.getMessages());
                    }
            }
            this.send(answer);
        }
    }
}

class waitForBattle {
    private static ArrayList<waitForBattle> waitForBattles = new ArrayList<>();
    private int battleType;
    private Client client;
    private int numberOfFlags;

    public waitForBattle(int numberOfFlags, int battleType, Client client) {
        this.client = client;
        this.numberOfFlags = numberOfFlags;
        this.battleType = battleType;
        remove(client);
        waitForBattles.add(this);
    }

    public static void remove(Client client) {
        for (waitForBattle waitForBattle : waitForBattles) {
            if (waitForBattle.client == client) {
                waitForBattles.remove(waitForBattle);
                return;
            }
        }
    }

    public static waitForBattle find(int battleType, int numberOfFlags) {
        for (waitForBattle waitForBattle : waitForBattles)
            if (battleType == waitForBattle.battleType && numberOfFlags == waitForBattle.numberOfFlags) {
                waitForBattles.remove(waitForBattle);
                return waitForBattle;
            }
        return null;
    }

    public Client getClient() {
        return client;
    }
}
