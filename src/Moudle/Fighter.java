package Moudle;

import java.util.ArrayList;

public class Fighter extends MinionAndHero {
	private int AP;
	private int HP;
	private int x;
	private int y;
	private String ID;
	private ArrayList<Fighter> attackedFighter = new ArrayList<> (  );
	private boolean canCounterAttack;
	private boolean canMove;
	private boolean canAttack;
	private int holyDefence;
	ArrayList<Buff> buffs = new ArrayList<> (  );
	private Cell currentCell;
	private Player player;
	public void preTurnProcces(){
		for ( Buff buff:this.getSpecialPowers () ){
			if ( this.getSpecialPowerType ()==1 ){
				addToBuff ( buff );
			}
		}
		this.enableBuffBoolEssence ();
	}
	public void addToBuff(Buff buff){
		if ( buff.getAgeType ()==3 ){
			return;
		}
		AP+=buff.getChangeAP ();
		HP+=buff.getChangeHP ();
		holyDefence+=buff.getChangeHollynes ();
		buffs.add ( buff );
		if ( buff.isDisableNegativeBuffs () ) {
			for ( Buff buff1:this.buffs )
			if ( !buff1.isPositive ( ) ) {
				removeFromBuff ( buff1 );
			}
		}
		if ( buff.isDisablePositiveBuffs () ) {
			for ( Buff buff1:this.buffs )
				if ( buff1.isPositive ( ) ) {
					removeFromBuff ( buff1 );
				}
		}
	}
	public int howManyAttacked(Fighter fighter){
		int counter=0;
		for ( Fighter fighter1:attackedFighter ){
			if ( fighter==fighter1 ){
				counter++;
			}
		}
		return counter;
	}
	public int getX () {
		return x;
	}

	public int getY () {
		return y;
	}

	public boolean CanAttack () {
		return canAttack;
	}
	public void disableCanAttack(){
		canAttack=false;
	}
	public void disableCanMove(){
		this.canMove=false;
	}
	public void setLocation(int x,int y){
		this.x=x;
		this.y=y;
	}
	public boolean isCanCounterAttack () {
		return canCounterAttack;
	}
	@Override
	public int getHP () {
		return HP;
	}
	public void decreaseHP(int decrease){
		HP-=decrease;
	}
	@Override
	public int getAP () {
		return AP;
	}

	public int getHolyDefence () {
		return holyDefence;
	}

	public boolean isCanMove () {
		return canMove;
	}

	public Player getPlayer () {
		return player;
	}

	public void removeFromBuff( Buff buff){
		AP-=buff.getChangeAP ();
		HP-=buff.getChangeHP ();
		holyDefence-=buff.getChangeHollynes ();
		buffs.remove ( buff );
	}
	public void enableBuffBoolEssence(){
		for ( Buff buff:buffs ){
			booleanChangeInt ( canAttack,buff.getChangeCanAttack () );
			booleanChangeInt ( canMove,buff.getChangeCanMove () );
			booleanChangeInt ( canCounterAttack,buff.getChangeCanCounterAttack () );
		}
	}
	public void addAttackedFighter(Fighter fighter){
		attackedFighter.add ( fighter );
	}
	public String getID () {
		return ID;
	}
	private void booleanChangeInt( boolean target, int changer){
		if ( changer==1 )
			target=true;
		if ( changer==2 )
			target=false;
		if ( changer==3 ){
			if ( target )
				target=false;
			else target=true;
		}
	}
	protected Fighter ( MinionAndHero minionAndHero,ArrayList<MinionAndHero> minionAndHeroes,Player player ) {
		super ( minionAndHero);
		AP = super.getAP ();
		HP = super.getHP ();
		int counter=0;
		for ( MinionAndHero minionAndHero1:minionAndHeroes ){
			if ( minionAndHero==minionAndHero1 ){
				counter++;
			}
		}
		this.ID=player.getUsername ()+"_"+minionAndHero.getName ()+"_"+counter+1;
	}
}
