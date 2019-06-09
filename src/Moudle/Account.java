package Moudle;

import Controller.ControlBox;
import Controller.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Account {
    private static ArrayList<Account> accounts = new ArrayList<>();
    private static Account mainAccount = new Account();
    private String userName;
    private String passWord;
    private Collection collection = new Collection();
    private int money = 100000;
    private ArrayList<Deck> decks = new ArrayList<>();
    private Deck mainDeck = new Deck ();
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

    public static int input(ControlBox controlBox) {
        String in = controlBox.getType();
        if (in.equals("create account")) {
            createAccount(controlBox.getUserName());
        }
        if (in.equals("login")) {
            if (login(controlBox.getUserName())) {
                Controller.setRegion("MainMenu");
                Controller.printInMenu ();
                return 2;
            }
        }
        if (in.equals("show leaderboard")) {
            showLeaderBoard();
        }
        if (in.equals("save")) {
            save();
        }
        if (in.equals("logout")) {
            logout();
        }
        if (in.equals("help")) {
            help();
        }
        return 0;
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

    public static void createAccount(String userName) {
        if (findAccount(userName) != null) {
            System.out.println("There is an account with this userName!");
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter password:");
            String passWord = scanner.next();
            Account account = new Account();
            account.userName = userName;
            account.passWord = passWord;
            accounts.add(account);
            System.out.println("Account's been created!");
        }

    }

    public static boolean login(String userName) {
        if (findAccount(userName) == null) {
            System.out.println("There is no account with this userName!");
            return false;
        } else {
            System.out.println("Enter password:");
            Scanner scanner = new Scanner(System.in);
            String passWord = scanner.next();
            if (passWord.equals(findAccount(userName).passWord)) {
                System.out.println("Login successful!");
                mainAccount = findAccount(userName);
                return true;
            } else {
                System.out.println("Wrong password!");
                return false;
            }
        }
    }

    public static void showLeaderBoard() {
        for (int i = 0; i < accounts.size(); i++) {
            for (int j = i + 1; j < accounts.size(); j++) {
                if (accounts.get(j).wins > accounts.get(i).wins) {
                    Collections.swap(accounts, i, j);
                }
            }
        }
        for (int i = 0; i < accounts.size(); i++) {
            System.out.printf("%d-UserName : %s-Wins : %d\n", i + 1, accounts.get(i).userName, accounts.get(i).wins);
        }
    }

    public static void save() {
        try {
            Load.saveAccount();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void logout() {
        mainAccount = null;
        System.out.println ("logout successfully" );
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
    }

    public void increaseWins() {
        wins++;
    }
    public void increaseLosses() {
        losses++;
    }
}
