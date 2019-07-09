package Server.Moudle;

import Client.Controller.Controller;
import ControlBox.ControlBox;
import Server.Client;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Account {
    private static ArrayList<Account> accounts = new ArrayList<>();
    private static Account mainAccount = new Account();
    private String userName;
    private String passWord;
    private Collection collection = new Collection();
    private int money = 100000;
    private ArrayList<Deck> decks = new ArrayList<>();
    private Deck mainDeck = new Deck();
    private int wins;
    private int losses;

    public Deck findDeck(String name) {
        for (Deck deck : decks) {
            if (deck.getName().equals(name)) {
                return deck;
            }
        }
        return null;
    }

    public static Account getMainAccount() {
        return mainAccount;
    }

    public static void setAccounts(ArrayList<Account> accounts) {
        Account.accounts = accounts;
    }

    public static ArrayList<Account> getAccounts() {
        return accounts;
    }

    public static ControlBox input(ControlBox controlBox, Client client) {
        String in = controlBox.getType();
        if (in.equals("create account")) {
            return createAccount(controlBox.getUserName(), controlBox.getPass(), client);
        }
        if (in.equals("login")) {
            ControlBox controlBox1 = login(controlBox, client);
            if (controlBox1.isSucces()) {
                Controller.setRegion("MainMenu");
                Controller.printInMenu();
            }
            return controlBox1;
        }
        if (in.equals("getMainAccount")) {
            ControlBox controlBox1 = new ControlBox();
            controlBox1.setAccount(mainAccount);
            return controlBox1;
        }
        if (in.equals("show leaderboard")) {
            showLeaderBoard();
        }
        if (in.equals("save")) {
            save();
        }
        if (in.equals("logout")) {
            System.out.println("logout1");
            logout();
        }
        if (in.equals("help")) {
            help();
        }
        return new ControlBox();
    }

    public static Account findAccount(String userName) {
        if (accounts.size() == 0) {
            return null;
        } else {
            for (int i = 0; i < accounts.size(); i++) {
                if (accounts.get(i).userName.equals(userName)) {
                    return accounts.get(i);
                }
            }
        }
        return null;
    }

    public static ControlBox createAccount(String userName, String passWord, Client client) {
        ControlBox controlBox = new ControlBox();
        if (findAccount(userName) != null) {
            controlBox.setSucces(false);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("This account already exists");
            alert.showAndWait();
        } else {
            Account account = new Account();
            account.userName = userName;
            account.passWord = passWord;
            accounts.add(account);
            mainAccount = account;
            client.setAccount(mainAccount);
            controlBox.setSucces(true);
            controlBox.setAccount(mainAccount);
        }
        return controlBox;
    }

    public static ControlBox login(ControlBox in, Client client) {
        ControlBox controlBox = new ControlBox();
        if (findAccount(in.getUserName()) == null) {
            controlBox.setSucces(false);
            controlBox.setDescription("no user");
            // Alert alert = new Alert(Alert.AlertType.ERROR);
            // alert.setHeaderText("There is no account with this username");
        } else {
            String passWord = in.getPass();
            if (passWord.equals(findAccount(in.getUserName()).passWord)) {
                System.out.println("Login successful!");
                mainAccount = findAccount(in.getUserName());
                client.setAccount(mainAccount);
                controlBox.setSucces(true);
            } else {
                controlBox.setDescription("wrong pass");
                controlBox.setSucces(false);
                //Alert alert = new Alert(Alert.AlertType.ERROR);
                //alert.setHeaderText("Wrong pass");
                // alert.showAndWait();
            }
        }
        return controlBox;
    }

    public static String showLeaderBoard() {
        for (int i = 0; i < accounts.size(); i++) {
            for (int j = i + 1; j < accounts.size(); j++) {
                if (accounts.get(j).wins > accounts.get(i).wins) {
                    Collections.swap(accounts, i, j);
                }
            }
        }
        String answer = new String();
        for (int i = 0; i < accounts.size(); i++) {
            System.out.printf("%d-UserName : %s-Wins : %d\n", i + 1, accounts.get(i).userName, accounts.get(i).wins);
            answer += String.format("%d-UserName : %s-Wins : %d\n", i + 1, accounts.get(i).userName, accounts.get(i).wins);
        }
        return answer;
    }

    public static void save() {
        try {
            Load.saveAccount();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ControlBox logout() {
        System.out.println("logout successful");
        mainAccount = null;
        ControlBox controlBox = new ControlBox();
        controlBox.setDescription("logout successfully");
//        controlBox.setSucces(true);
        return controlBox;
    }

    public static void help() {
        System.out.print("create account [user name]\nlogin [user name]\nshow leaderboard\nsave\nlogout\nhelp\n");
    }

    public String getUserName() {
        return userName;
    }


    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    public Collection getCollection() {
        return collection;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void addMoney(int money) {
        this.money += money;
    }

    public void spendMoney(int money) {
        this.money -= money;
    }

    public ArrayList<Deck> getDecks() {
        return decks;
    }

    public Deck getMainDeck() {
        return mainDeck;
    }

    public void setMainDeck(Deck mainDeck) {
        this.mainDeck = mainDeck;
        System.out.printf("mainDeck is %s", mainDeck.getName());
    }

    public void increaseWins() {
        wins++;
    }

    public void increaseLosses() {
        losses++;
    }
}
