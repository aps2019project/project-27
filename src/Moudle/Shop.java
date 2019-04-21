package Moudle;

import java.util.ArrayList;

public class Shop {
    private Account account;
    private ArrayList<MinionsAndHero> heroesAndMinions;
    private ArrayList<Card> cards;
    private ArrayList<Item> items;

    public static void handleInput() {

    }

    public void search(String name) {
        int founded = 0;
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).name.equals(name)) {
                founded = 1;
                System.out.println("We have this card!");
            }
        }
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).name.equals(name)) {
                founded = 1;
                System.out.println("We have this item!");
            }
        }
        if (founded == 0) {
            System.out.println("We don't have this card/item!");
        }
    }

    public void buy(String name) {

    }

    public void sell(String name) {

    }

    public void searchCollection(String name) {

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