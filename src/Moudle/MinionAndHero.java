package Moudle;

import java.util.ArrayList;

public class MinionAndHero extends Card {
	private  static ArrayList<MinionAndHero> minionAndHeroes = new ArrayList<> (  );
	private boolean isHero;
	private int AP;
	private int HP;
	private String specialPower;
	private int nation;
	private int attackType;
	private String[] abilities;
	private boolean canCounterAttack;
	private boolean canMove;
	private boolean canAttack;
	private int holyDefence;


	protected MinionAndHero ( String name , int cardID , int shopPrice , int manaPrice ) {
		super ( name , cardID , shopPrice , manaPrice );
	}

	public static void setMinionAndHeroes ( ArrayList<MinionAndHero> minionAndHeroes ) {
		MinionAndHero.minionAndHeroes = minionAndHeroes;
	}

	public int getNation () {
		return nation;
	}
	public boolean isHero () {
		return isHero;
	}
	public String[] getAbilities(){
		return abilities;
	}
}
