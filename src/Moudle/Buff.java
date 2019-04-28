package Moudle;

public class Buff {
	private String name;
	private boolean isPositive;
	private int changeAP;
	private int changeHP;
	private int changeHollynes;
	//boolean change int:	0:nothing	1:true	2:false	3:not
	private int changeCanMove;
	private int changeCanAttack;
	private int changeCanCounterAttack;
	private boolean disableEnemyPositiveBuffs;
	private boolean disablePlayerNegativeBuffs;
	//age type:0:1 turn	2:long age
	private int ageType;
	private int age;
	private Buff cellBuff;

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
