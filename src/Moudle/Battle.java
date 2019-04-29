package Moudle;

import Controller.ControlBox;

import java.util.ArrayList;

public class Battle {
    private static Battle currentBattle;
    private Ground ground;
    private int currentTurn;
    private Player player1;
    private Player player2;
    private Player playerInTurn;
    private ArrayList<Fighter> fighters;
    private Card selectedCard;
    private Item selectedItem;
    private int battleType;
    private ArrayList<Item> flags;
    private int numberOfFlags;
    private Item mainFlag;
    private Fighter heroP1;
    private Fighter heroP2;
    private ArrayList<Buff> buffs= new ArrayList<> (  );
    public Ground getGround () {
        return ground;
    }
    public static void input( ControlBox controllBox) {
        if (controllBox.getType().equals("game info")) {
            currentBattle.showInfo();
            return;
        }
    }
    public void insert(int x,int y){
        Card card = null;
        int type = card.getCardType ();
        if ( type==0 ){
            Spell spell = (Spell)card;
            if ( !spell.getTarget ().isValidTarget ( this,x,y,playerInTurn ) ){
                System.out.println ( "invalid target" );
                return;
            }
            ArrayList<Fighter> fighters = spell.getTarget ().targetFighters (this,x,y,playerInTurn);
            for ( Buff buff:spell.getMainBuffs () ){
                buff(buff,fighters,x,y);
            }
            for ( Buff buff:spell.getBuffs () ){
                buff(buff,fighters,x,y);
                buffs.add ( buff );
            }
        }
    }
    private void buff(Buff buff,ArrayList<Fighter> fighters,int x,int y){
        if ( buff.getIsCellBuff ()){
            ground.getCell ( x,y ).addCellEffect ( buff.getCellBuff () );

        }
        else {
            buff.setFighters ( fighters );
            for ( Fighter fighter : fighters ) {
                fighter.addToBuff ( buff );
            }
        }
    }
    private boolean isValidSelect(){
        return true;
    }
    public void checkWinner() {
    }
    public void setMana() {
    }

    public Battle(Player player1, Player player2, int battleType) {

    }
    private void checkBuffs(){
        for ( Buff buff:buffs ){
            if ( buff.getAgeType ()==1 ){
                if ( buff.getAge ()<1 ){
                    removeBuff ( buff );
                }
                else buff.decreesAge ();
            }
        }
    }
    private void removeBuff(Buff buff){
        if ( buff.getIsCellBuff () ){

        }
        else {
            for ( Fighter fighter : buff.getFighters ( ) ) {
                fighter.removeFromBuff ( buff );
            }
        }
    }
    public void nextTurn() {
        for ( Fighter fighter:fighters ){
            fighter.preTurnProcces ();
        }
        currentTurn++;
        setMana();
        setPlayerInTurn();
    }
    public void showInfo() {

    }
    private void setPlayerInTurn() {
        if (playerInTurn.equals(player1))
            playerInTurn = player2;
        playerInTurn = player1;
    }
}
