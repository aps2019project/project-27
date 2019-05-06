package Moudle;

import java.util.ArrayList;

public class Buff {
	private String name;
	private boolean exeptABuff;
	private ArrayList<Fighter> fighters;
	private int cellX,cellY;
	private boolean isCellBuff;
	private boolean isPositive;
	private int changeAP;
	private int changeHP;
	private int changeMana;
	private int changeHollynes;
	private boolean noHolynessForOpponent;
	private int changeOpponentHolyness;
	//boolean change int:	0:nothing	1:true	2:false	3:not
	private int changeCanMove;
	private int changeCanAttack;
	private int changeCanCounterAttack;
	private boolean disablePositiveBuffs;
	private boolean disableNegativeBuffs;
	private int plusDamageToAttacked;
	//age type:0:unlimited	2:long age 3:execute when age is zero
	private int ageType;
	private int age;
	private Buff cellBuff;
	public void setCellXY(int x,int y){
		cellX=x;
		cellY=y;
	}

	public int getChangeMana () {
		return changeMana;
	}

	public boolean isDisableNegativeBuffs () {
		return disableNegativeBuffs;
	}

	public boolean isDisablePositiveBuffs () {
		return disablePositiveBuffs;
	}

	public boolean noHolynessForOpponent(){
		return noHolynessForOpponent;
	}
	public boolean isPositive () {
		return isPositive;
	}

	public int getPlusDamageToAttacked () {
		return plusDamageToAttacked;
	}

	public boolean isExeptABuff () {
		return exeptABuff;
	}

	public int getAge () {
		return age;
	}
	public boolean getIsCellBuff(){
		return isCellBuff;
	}
	public Buff getCellBuff () {
		return cellBuff;
	}

	public void setFighters ( ArrayList<Fighter> fighters ) {
		this.fighters = fighters;
	}
	public void decreesAge(){
		age--;
	}
	public ArrayList<Fighter> getFighters () {
		return fighters;
	}

	public int getAgeType () {
		return ageType;
	}

	public int getChangeHollynes () {
		return changeHollynes;
	}
	public int getChangeHP () {
		return changeHP;
	}
	public int getChangeAP () {
		return changeAP;
	}
	public int getChangeCanMove () {
		return changeCanMove;
	}
	public int getChangeCanCounterAttack () {
		return changeCanCounterAttack;
	}
	public int getChangeCanAttack () {
		return changeCanAttack;
	}
}
