import java.util.ArrayList;

public class Spell extends Card {
	private ArrayList<Spell> spells = new ArrayList<Spell> (  );
	private String[] target;
	private String[] effects;
	private int x;

	protected Spell ( String name , int cardID , int shopPrice , int manaPrice ) {
		super ( name , cardID , shopPrice , manaPrice );
	}
}
