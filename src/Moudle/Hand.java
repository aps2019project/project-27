package Moudle;

import java.util.ArrayList;

public class Hand extends Deck {
    private Deck deck;
    private ArrayList<Card> cards = new ArrayList<>();
    private Item item;
    private Card nextCard;

    public void addCard() {
        cards.add ( nextCard );
        nextCard = null;
    }
    private void setNextCard(){
        int size = deck.getCards ().size ();
        int i = ( int ) (Math.random ()%size);
        nextCard = deck.getCards ().get ( i );
    }
    public void addItem() {

    }

    public void show() {
        //todo
        for (Card card : cards) {
            card.showCard();
        }
    }

    @Override
    public ArrayList<Card> getCards() {
        return cards;
    }

    public void showNextCard() {
        nextCard.showCard();
    }

    public Item getItem() {
        return this.item;
    }

    public void selectNextCard() {

    }

    public Card getNextCard() {
        return this.nextCard;
    }

    public void removeCard() {

    }

    public void removeItem() {

    }
}
