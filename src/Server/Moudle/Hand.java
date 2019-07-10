package Server.Moudle;

import java.util.ArrayList;
import java.util.Random;

public class Hand extends Deck {
    private Deck deck;
    private ArrayList<Card> handCards = new ArrayList<>();
    private Item handItem;
    private Card nextCard;

    public void addCard() {
        setNextCard();
        handCards.add(nextCard);
        nextCard = null;
    }

    public void setNextCard() {
        Random random = new Random();
        if (nextCard != null)
            return;
        int size = deck.getCards().size();
        nextCard = deck.getCards().get(random.nextInt(size));
    }

    public Deck getDeck() {
        return deck;
    }

    public void addItem() {

    }

    public Hand(Deck deck) {
        handCards = new ArrayList<Card>();
        this.deck = deck;
    }

    public void show() {
        if (handCards.size() == 0) {
            System.out.println("hand is empty");
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

    public void removeCard(Card card) {
        handCards.remove(card);
    }

    public void removeItem() {

    }
}
