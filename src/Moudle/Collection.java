package Moudle;

import java.util.ArrayList;

public class Collection {

    private static ArrayList<Collection> collections = new ArrayList<>();

    private ArrayList<Card> cards = new ArrayList<>();
    private ArrayList<Item> items = new ArrayList<>();
    private ArrayList<Deck> decks = new ArrayList<>();

    public ArrayList<Card> getCards() {
        return cards;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public static void save() {

    }

    public static void showAllDecks() {

    }

    public void show() {

    }

    public void search(String name) {

    }

    public void createDeck(String deckName) {

    }

    public void deleteDeck(String deckName) {

    }

    public void addToDeck(int ID, String deckName) {

    }

    public void removeFromDeck(int ID, String deckName) {

    }

    public boolean validateDeck(String deckName) {
        return false;
    }

    public void selectDeck(String deckName) {

    }

    public void showDeck(String deckName) {

    }

    public void help() {
        //$%^$^&%*%%%#^&^$&@#$@#$@#$&$&&#@$$%$????
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeCard(Card card) {
        cards.remove(card);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }
}
