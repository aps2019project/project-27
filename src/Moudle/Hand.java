package Moudle;

import java.util.ArrayList;

public class Hand extends Deck {
    private Deck deck;
    private ArrayList<Card> handCards = new ArrayList<>();
    private Item handItem;
    private Card nextCard;

    public void addCard() {
        setNextCard ();
        handCards.add ( nextCard );
        nextCard = null;
    }
    public void setNextCard(){
        if ( nextCard!=null )
            return;
        int size = deck.getCards ().size ();
        int i = ( int ) (Math.random ()%size);
        nextCard = deck.getCards ().get ( i );
    }

    public Deck getDeck() {
        return deck;
    }

    public void addItem() {

    }
    public Hand(Deck deck){
        handCards = new ArrayList<Card> (  );
        this.deck = deck;
    }
    public void show() {
        //todo
        if(handCards.size ()==0){
            System.out.println ("hand is empty" );
            return;
        }
        for (Card card : handCards) {
            card.showCard();
        }
    }

    @Override
    public ArrayList<Card> getCards() {
        return handCards;
    }

    public void showNextCard() {
        nextCard.showCard();
    }

    public Item getItem() {
        return this.handItem;
    }

    public Card getNextCard() {
        return this.nextCard;
    }

    public void removeCard() {
    }
    public void removeItem() {

    }
}
