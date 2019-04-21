package Moudle;

import java.util.ArrayList;

public class Spell extends Card {
	private ArrayList<Spell> spells = new ArrayList<Spell> (  );
	private String[] target;
	private String[] effects;
	public static void setSpells ( ArrayList<Spell> spells ) {
		this.spells = spells;
	}
	protected Spell ( String name , int cardID , int shopPrice , int manaPrice ) {
		super ( name , cardID , shopPrice , manaPrice );
	}
}
