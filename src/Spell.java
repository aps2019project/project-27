import java.util.ArrayList;
public class Spell extends Card {
	private static ArrayList<Spell> spells = new ArrayList<Spell> (  );
	private String[] target;
	private String[] effects;
	public static void setSpells ( ArrayList<Spell> spells ) {
		Spell.spells = spells;
	}
}