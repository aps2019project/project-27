package Moudle;

import java.util.ArrayList;

public class Shop {
    private Account account;
    private ArrayList<MinionAndHero> heroesAndMinions;
    private ArrayList<Card> cards;
    private ArrayList<Item> items;

    public static void handleInput() {

    }

    public void search(String name) {
        boolean founded = false;
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getName().equals(name)) {
                founded = true;
            }
        }
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().equals(name)) {
                founded = true;
            }
        }
        if (founded == false) {
            System.out.println("We don't have this card/item!");
        }
    }

    public void buy(String name) {
        if (Card.findCard(name) == null && Item.findItem(name) == null) {
            System.out.println("This card/item doesn't exist!");
        } else if (Card.findCard(name) != null && Item.findItem(name) == null) {
            if (account.getMoney() < Card.findCard(name).getShopPrice()) {
                System.out.println("You don't have enough money!");
            } else {
                account.spendMoney(Card.findCard(name).getShopPrice());
                account.getCollection().addCard(Card.findCard(name));
            }
        } else if (Card.findCard(name) == null && Item.findItem(name) != null) {
            if (account.getMoney() < Item.findItem(name).getPrice()) {
                System.out.println("You don't have enough money!");
            } else if (account.getCollection().getItems().size() >= 3) {
                System.out.println("You can't buy more than 3 items!");
            } else {
                account.spendMoney(Item.findItem(name).getPrice());
                account.getCollection().addItem(Item.findItem(name));
            }
        }
    }

    public void sell(String name) {
        if (Card.findCard(name) == null && Item.findItem(name) == null) {
            System.out.println("This card/item doesn't exist!");
        } else if (Card.findCard(name) != null && Item.findItem(name) == null){
            account.getCollection().removeCard(Card.findCard(name));
            account.addMoney(Card.findCard(name).getShopPrice());
        } else if (Card.findCard(name) == null && Item.findItem(name) !=null){
            account.getCollection().removeItem(Item.findItem(name));
            account.addMoney(Item.findItem(name).getPrice());
        }
    }

    public void showCollection() {

    }

    public void searchCollection(String name) {
        boolean founded = false;
        for (int i = 0; i < account.getCollection().getCards().size(); i++) {
            if (account.getCollection().getCards().get(i).getName().equals(name)) {
                founded = true;
            }
        }
        for (int i = 0; i < account.getCollection().getItems().size(); i++) {
            if (account.getCollection().getItems().get(i).getName().equals(name)) {
                founded = true;
            }
        }
        if (founded == false) {
            System.out.println("This card/item doesn't exist!");
        }
    }

    public void show() {

    }

    public void exit() {

    }

    public void help() {
        System.out.println("exit\nshow collection\nsearch [item name|card name]\nsearch collection [item name|card name]\n" +
                "buy [card name|item name]\nsell [card id|card id]\nshow");
    }
}