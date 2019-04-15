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
	public MinionAndHero(String name,int cardID,int shopPrice,int manaPrice,boolean isHero,int AP,int HP,String specialPower,int nation,int attackType,String[] abilities){
		super (name, cardID, shopPrice, manaPrice );
		this.isHero = isHero;
		this.AP = AP;
		this.HP = HP;
		this.specialPower = specialPower;
		this.nation = nation;
		this.attackType = attackType;
		this.abilities = abilities;
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
	public static void setMinionAndHeroes ( ArrayList<MinionAndHero> minionAndHeroes ) {
		MinionAndHero.minionAndHeroes = minionAndHeroes;
	}
}