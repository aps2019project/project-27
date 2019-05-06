package Moudle;

import View.View;

import java.util.ArrayList;

public class Player extends Account {
    private ArrayList<Fighter> fighters;
    private Hand hand;
    private int mana;
    private ArrayList<Item> collectedItems;
    private ArrayList<Card> graveYard;
    private Item mainItem;
    private ArrayList<Buff> buffs;
    public int getMana () {
        return mana;
    }
    public void preTurnProcess(){
        if ( hand.getCards ().size ()<5 ){
            hand.addCard ();
        }
    }
    public void addToBuffs(Buff buff){
        mana+=buff.getChangeMana ();
        buffs.add ( buff );
    }
    public void removeFromBuff(Buff buff){
        buffs.remove ( buff );
    }
    public Item getMainItem () {
        return mainItem;
    }

    public Hand getHand () {
        return hand;
    }

    public void addToGraveYard( Fighter fighter){
        graveYard.add ( fighter );
    }
    public void decreaseMana(int mony){
        mana-=mony;
    }
    public void addItem( Item item){
        collectedItems.add ( item );
    }
    public ArrayList<Fighter> getFighters () {
        return fighters;
    }

    public boolean equals ( Object object){
        if ( object.getClass () == Player.class )
        {
            Account account = (Player)object;
            if ( account.getUserName ().equals ( this.getUserName ()) )
                return true;
            return false;
        }
        else return false;
    }
    public void insert() {

    }

    public void showCollectables() {

    }
    public void showFighters(){
        for ( Fighter fighter:fighters ){
            View.showFighter ( fighter );
        }
    }
    public void useItem(Item item) {

    }

    public void showGraveYard() {

    }

    public void showNextCard() {

    }

    public void showHand() {
        hand.show ();
    }
}