package Moudle;

import Controller.ControlBox;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Account {
    private static ArrayList<Account> accounts;
    private String username;
    private String password;
    private Collection collection;
    private int money;
    private ArrayList<Deck> decks;
    private Deck mainDeck;
    private int wins;
    private int losses;

    public static void input(ControlBox controlBox) {
        if(controlBox.getType().equals("createAccount"));
    }

    public boolean isValidDeck() {
        return false;
    }

    public String getUsername() {
        return username;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void addDeck(Deck deck) {
        this.decks.add(deck);
    }

    public void setMainDeck(Deck deck) {
        this.mainDeck = deck;
    }

    public Collection getCollection() {
        return collection;
    }

    public int getMoney() {
        return money;
    }

    public void spendMoney(int price) {
        money -= price;
    }

    public void addMoney(int price) {
        money += price;
    }

    public ArrayList<Deck> getDecks() {
        return decks;
    }

    public Deck getMainDeck() {
        return mainDeck;
    }

    public static void createAccount(String username) {
        Scanner scanner = new Scanner(System.in);
        int accountFounded = 0;
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).username.equals(username)) {
                accountFounded = 1;
            }
        }
        if (accountFounded == 1) {
            System.out.println("This user has already been created!");
        } else {
            String password = scanner.next();
            Account account = new Account();
            account.username = username;
            account.password = password;
            accounts.add(account);
        }
    }

    public static void login(String username) {
        Scanner scanner = new Scanner(System.in);
        int accountFounded = 0;
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).username.equals(username)) {
                accountFounded = 1;
            }
        }
        if (accountFounded == 0) {
            System.out.println("There is no such username!");
        } else {
            String password = scanner.next();
            for (int i = 0; i < accounts.size(); i++) {
                if (accounts.get(i).password.equals(password)) {

                } else {
                    System.out.println("Wrong password!");
                }
            }
        }
    }

    public void logout() {

    }

    public static void save() {

    }

    public void increaseWins() {
        wins++;
    }

    public void increaseLosses() {
        losses++;
    }

    public static void showLeaderBoard() {
        for (int i = 0; i < accounts.size(); i++) {
            for (int j = i + 1; j < accounts.size(); j++) {
                if (accounts.get(i).wins < accounts.get(j).wins) {
                    Collections.swap(accounts, i, j);
                }
            }
        }
        for (int i = 0; i < accounts.size(); i++) {
            System.out.println(i + "-" + "UserName" + ":" + accounts.get(i).username + "-" + "Wins" + ":" + accounts.get(i).wins);
        }
    }

    public void help() {
        System.out.println("create account [user name]\nlogin [user name]\nshow leaderboard\nsave\nlogout\n");
    }
}