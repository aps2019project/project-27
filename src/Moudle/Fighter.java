package Moudle;

import java.util.ArrayList;

public class Fighter extends Card {
	private int AP;
	private int HP;
	private int x;
	private int y;
	private boolean canCounterAttack;
	private boolean canMove;
	private boolean canAttack;
	private int holyDefence;
	ArrayList<Buff> buffs = new ArrayList<> (  );
	private Cell currentCell;
	private Player player;
	public void addToBuff(Buff buff){
		AP+=buff.getChangeAP ();
		HP+=buff.getChangeHP ();
		holyDefence+=buff.getChangeHollynes ();
		buffs.add ( buff );
	}
	public void removeFromBuff(Buff buff){
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
	private void booleanChangeInt(boolean target,int changer){
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
	protected Fighter ( String name , int cardID , int shopPrice , int manaPrice ) {
		super ( name , cardID , shopPrice , manaPrice );
	}
}
