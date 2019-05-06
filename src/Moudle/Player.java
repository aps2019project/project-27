package Moudle;

import java.util.ArrayList;

public class Player extends Account {
    private ArrayList<Fighter> fighters;
    private Hand hand;
    private int mana;
    private ArrayList<Card> graveYard;
    private ArrayList<Item> items;
    private ArrayList<Buff> buffs;

    public void addToBuffs(Buff buff) {

    }

    public ArrayList<Fighter> getFighters() {
        return fighters;
    }

    public boolean equals(Object object) {
        if (object.getClass() == Player.class) {
            Account account = (Player) object;
            if (account.getUserName().equals(this.getUserName()))
                return true;
            return false;
        } else return false;
    }

    public void insert() {

    }

    public void showCollectables() {

    }

    public void showFighters() {
        System.out.println(fighters);
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