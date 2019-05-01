package Moudle;

import Controller.ControlBox;

import java.util.ArrayList;

public class Battle {
    private static Battle currentBattle;
    private ArrayList<MinionAndHero> minionAndHeroes = new ArrayList<> (  );
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
        if ( type==1 ){
            MinionAndHero minionAndHero = ( MinionAndHero ) card;
            if ( !isValidNewFighter ( minionAndHero, playerInTurn,x,y ) ){
                return;
            }
            minionAndHeroes.add ( minionAndHero );
            Fighter fighter = new Fighter ( minionAndHero,minionAndHeroes,playerInTurn );
            ground.getCell ( x, y ).moveInCell ( fighter );
            fighter.setLocation ( x, y );
        }
    }
    public void attack(Fighter fighter,int baseX,int baseY,int targetX,int targetY){
    if ( !isValidDistanceForAttack () ){
        //fosh
        return;
    }
    if ( !fighter.isCanAttack () ){
        //fosh
        return;
    }
    Fighter opponent = isValidTargetForAttack ( targetX, targetY );
        if ( opponent == null ) {
            //fosh
            return;
        }
        fighter.disableCanAttack ();
        fighter.disableCanMove ();
    opponent.decreaseHP ( fighter.getAP ()-opponent.getHolyDefence () );
    if ( opponent.isCanCounterAttack () ){
        fighter.decreaseHP ( opponent.getAP ()-fighter.getHolyDefence () );
    }
    isDeath ( opponent );
    isDeath ( fighter );

    }
    private boolean isDeath(Fighter fighter){

    }
    private boolean isValidDistanceForAttack(){

        return true;
    }
    private Fighter isValidTargetForAttack(int targetX,int targetY){
        Fighter fighter;
        fighter= ( Fighter ) ground.getCell ( targetX, targetY ).getCardOnCell ();
        if ( fighter.getPlayer ()== playerInTurn )
            return null;
        return fighter;
    }
    private boolean isValidNewFighter(MinionAndHero minionAndHero,Player player,int x,int y){
        return true;
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
    private boolean isValidMove(int x1,int y1,int x2,int y2){
        return  ( Ground.getDistance ( x1, y1, x2, y2 )<=2 );
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
    private void move(int targetX,int targetY,int x,int y){
    	if(!isValidMove ( targetX, targetY, x, y )){
    		//fosh
    		return;
		}
    	if ( !isValidTargetForMove ( targetX,targetY )){
    		//fosh
			return;
		}
        Fighter fighter = ( Fighter ) this.ground.getCell ( x, y ).getCardOnCell ();
    	if(fighter.isCanMove()){
    	    //fosh
    	    return;
        }
    	ground.getCell ( x, y ).moveInCell ( fighter );
    	ground.getCell ( targetX,targetY ).moveFromCell ();
	}
	private boolean isValidTargetForMove(int x,int y){
    	Fighter fighter = (Fighter ) this.ground.getCell ( x, y ).getCardOnCell ();
    	if ( fighter.getPlayer ().equals ( this.playerInTurn )){
    		return true;
		}
    	return false;
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
