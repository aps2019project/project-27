package Moudle;

import java.util.ArrayList;

public abstract class Card {
    private static ArrayList<Card> cards;
    private String name;
    private int cardID;
    private int shopPrice;
    private int manaPrice;
    private int cardType;//0:spell   1:minion   2:hero

    protected Card(String name, int shopPrice, int manaPrice, int cardType) {
        this.name = name;
        this.shopPrice = shopPrice;
        this.manaPrice = manaPrice;
        this.cardType = cardType;
        cards.add(this);
    }

    public static ArrayList<Card> getCards() {
        return cards;
    }

    public static void setCards(ArrayList<Card> cards) {
        Card.cards = cards;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCardID() {
        return cardID;
    }

    public void setCardID(int cardID) {
        this.cardID = cardID;
    }

    public int getShopPrice() {
        return shopPrice;
    }

    public void setShopPrice(int shopPrice) {
        this.shopPrice = shopPrice;
    }

    public int getManaPrice() {
        return manaPrice;
    }

    public void setManaPrice(int manaPrice) {
        this.manaPrice = manaPrice;
    }

    public int getCardType() {
        return cardType;
    }

    public void setCardType(int cardType) {
        this.cardType = cardType;
    }

    public static Card findCard(String name) {
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getName().equals(name)) {
                return cards.get(i);
            }
        }
        return null;
    }
}
