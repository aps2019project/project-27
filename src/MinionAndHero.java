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
	public int getNation () {
		return nation;
	}
	public boolean isHero () {
		return isHero;
	}
	public String[] getAbilities(){
		return abilities;
	}
	public static void setMinionAndHeroes ( ArrayList<MinionAndHero> minionAndHeroes ) {
		MinionAndHero.minionAndHeroes = minionAndHeroes;
	}
}