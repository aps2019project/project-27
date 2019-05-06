package Moudle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Account {
    private static ArrayList<Account> accounts = new ArrayList<>();
    private String userName;
    private String passWord;
    private Collection collection;
    private int money;
    private ArrayList<Deck> decks;
    private Deck mainDeck;
    private int wins;
    private int losses;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
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
            String passWord = scanner.next();
            Account account = new Account();
            account.userName = userName;
            account.passWord = passWord;
            accounts.add(account);
            System.out.println("created");
        }

    }

    public boolean login(String userName) {
        if (findAccount(userName) == null) {
            System.out.println("There is no account with this userName!");
            return false;
        } else {
            Scanner scanner = new Scanner(System.in);
            String passWord = scanner.next();
            if (passWord.equals(findAccount(userName).passWord)) {

                return true;
            } else {
                System.out.println("Wrong passWord!");
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

    }

    public static void logout() {

    }

    public static void help() {
        System.out.print("create account [user name}\nlogin [user name]\nshow leaderboard\nsave\nlogout\nhelp\n");
    }
}
