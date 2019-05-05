package Moudle;

import View.View;

import java.util.ArrayList;

public class Player extends Account {
    private ArrayList<Fighter> fighters;
    private Hand hand;
    private int mana;
    private ArrayList<Card> graveYard;
    private ArrayList<Item> items;
    private ArrayList<Buff> buffs;
    public void addToBuffs(Buff buff){

    }

    public int getMana () {
        return mana;
    }
    public void decreaseMana(int mony){
        mana-=mony;
    }
    public void addItem( Item item){
        items.add ( item );
    }
    public ArrayList<Fighter> getFighters () {
        return fighters;
    }

    public boolean equals ( Object object){
        if ( object.getClass () == Player.class )
        {
            Account account = (Player)object;
            if ( account.getUsername ().equals ( this.getUsername () ) )
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