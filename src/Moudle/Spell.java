package Moudle;

import java.util.ArrayList;

public class Spell extends Card {
	private static ArrayList<Spell> spells = new ArrayList<Spell> (  );
	private ArrayList<Buff> buffs;
	private Target target;
	//todo
	public ArrayList<Buff> getBuffs () {
		return buffs;
	}
	public Target getTarget () {
		return target;
	}
	public static void setSpells ( ArrayList<Spell> spells ) {
		Spell.spells = spells;
	}
	protected Spell ( String name , int cardID , int shopPrice , int manaPrice ) {
		super ( name , cardID , shopPrice , manaPrice );
	}
}
//todo cell effect