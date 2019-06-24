package Moudle;
import View.View;

import java.util.ArrayList;
public class Card {
    private static ArrayList<Card> cards = new ArrayList<>();
    private String name;
    private String image;
    private int cardID;
    private int shopPrice;
    private int manaPrice;
    //0:spell   1:minion
    private int cardType;
 public Card(){

 }
    public static void addMAndH ( ArrayList<MinionAndHero> minionAndHeroes ) {
        for ( MinionAndHero minionAndHero:minionAndHeroes ){
            cards.add ( minionAndHero );
        }
    }
    public static void addSpells(ArrayList<Spell> spells){
        for ( Spell spell:spells ){
            cards.add ( spell );
        }
    }

    public String getImage () {
        return image;
    }

    public int getCardType () {
        return cardType;
    }

    public static ArrayList<Card> getCards () {
        return cards;
    }

    public int getManaPrice () {
        return manaPrice;
    }
    public void showCard (){
        if ( this.getCardType ()==1 ){
        	MinionAndHero fighter = ( MinionAndHero ) this;
            View.printMinion ( fighter );
        }
        if(this.getCardType ()==0){
        	Spell spell = ( Spell ) this;
            View.showSpell ( spell );
        }
    }
    protected Card( String name, int shopPrice, int manaPrice, int cardType,String image) {
        this.name = name;
        this.shopPrice = shopPrice;
        this.manaPrice = manaPrice;
        this.cardType= cardType;
        this.image = image;
        cards.add(this);
    }
    public String getName() {
        return name;
    }
    public int getShopPrice() {
        return shopPrice;
    }
    public static void showCardInfo(int cardID) {
        Card card = findCard(cardID);
        printCardInfo(card);
    }
    public static void showCardInfo(int cardID, ArrayList<Card> cards) {
        Card card = findCard(cardID, cards);
        printCardInfo(card);
    }
    private static void printCardInfo(Card card) {
        if (card == null) {
            System.out.println("there is no card with this ID");
            return;
        }
        System.out.println("name: " + card.name);
        System.out.println("cardID: " + card.cardID);
        System.out.println("price: " + card.shopPrice + " derick");
        System.out.println("mana price: " + card.manaPrice);
    }
    public static Card findCard(int cardID) {
        for (Card card : cards) {
            if (card.cardID == cardID)
                return card;
        }
        return null;
    }
    public static Card findCard(String name) {
        for (Card card : cards) {
            if (card.name.equals(name))
                return card;
        }
        return null;
    }

    public static Card findCard(String name, ArrayList<Card> cards) {
        for (Card card : cards) {
            if (card.name.equals(name))
                return card;
        }
        return null;
    }

    public static Card findCard(int cardID, ArrayList<Card> cards) {
        for (Card card : cards) {
            if (card.cardID == cardID)
                return card;
        }
        return null;
    }
}