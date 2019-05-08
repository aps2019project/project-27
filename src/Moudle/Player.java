package Moudle;

import View.View;

import java.util.ArrayList;

public class Player {
    private String UserName;
    private ArrayList<Fighter> fighters = new ArrayList<> (  );
    private Hand hand;
    private int mana;
    private Account account;
    private ArrayList<Item> collectedItems;
    private ArrayList<Card> graveYard;
    private Item mainItem;
    private boolean haveLastFlag;
    private ArrayList<Buff> buffs;
    private int flagInHand=0;
    public void increaseFlagInHand(){
        flagInHand++;
    }

    public int getFlagInHand () {
        return flagInHand;
    }

    public ArrayList<Item> getCollectedItems () {
        return collectedItems;
    }

    public boolean isHaveLastFlag () {
        return haveLastFlag;
    }

    public void setHaveLastFlag ( boolean haveLastFlag ) {
        this.haveLastFlag = haveLastFlag;
    }

    public int getMana () {
        return mana;
    }
    public void preTurnProcess(){
        if ( hand.getCards ().size ()<5 ){
            hand.addCard ();
            hand.setNextCard ();
        }
    }

    public void setMana ( int mana ) {
        this.mana = mana;
    }

    public Player( Account account){
        this.account = account;
        hand = new Hand ( account.getMainDeck () );
    }
    public Account getAccount () {
        return account;
    }

    public String getUserName () {
        return UserName;
    }

    public void addToBuffs( Buff buff){
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
    public void addFighter(Fighter fighter){
        fighters.add ( fighter );
    }

    public boolean equals ( Object object){
        if ( object.getClass () == Player.class )
        {
            Account account = this.getAccount ();
            if ( account.getUserName ().equals ( this.getUserName ()) )
                return true;
            return false;
        }
        else return false;
    }
    public void insert() {

    }

    public void showCollectables() {
        for ( Item item:collectedItems ){
            item.showItem ();
        }
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