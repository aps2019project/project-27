package Moudle;

import java.util.ArrayList;

public class Spell extends Card {
	private static ArrayList<Spell> spells = new ArrayList<> (  );
	private ArrayList<Buff> buffs;
	private Target target;

	public static ArrayList<Spell> getSpells () {
		return spells;
	}
	public ArrayList<Buff> getBuffs () {
		return buffs;
	}
	public Target getTarget () {
		return target;
	}
	public static void setSpells ( ArrayList<Spell> spells ) {
		Spell.spells = spells;
	}
	public Spell(
			String name,
			String image,
			String cost,
			String mana,
			Buff buff,
			Target target){
		super ( name,image,cost,mana,1 );
		this.target = target;
		this.buffs.add ( buff );
		spells.add ( this );
	}
}