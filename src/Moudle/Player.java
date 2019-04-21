package Moudle;

import java.util.ArrayList;

public class Player extends Account {
    private ArrayList<Fighter> fighters;
    private Hand hand;
    private int mana;
    private ArrayList<Card> graveYard;
    private ArrayList<Item> items;
    public boolean equals (Object object){
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

    public void useItem(Item item) {

    }

    public void showGraveYard() {

    }

    public void showNextCard() {

    }

    public void showHand() {

    }
}