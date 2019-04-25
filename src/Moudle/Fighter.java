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
	}
	protected Fighter ( String name , int cardID , int shopPrice , int manaPrice ) {
		super ( name , cardID , shopPrice , manaPrice );

	}
}
