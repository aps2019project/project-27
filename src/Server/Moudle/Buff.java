package Server.Moudle;

import java.util.ArrayList;

public class Buff {
    private static ArrayList<Buff> createdBuffs = new ArrayList<>();
    private String name;
    private boolean exeptABuff;
    private ArrayList<Fighter> fighters;
    private int cellX, cellY;
    private ArrayList<Cell> cells;
    private boolean isCellBuff;
    //0:hell	1:holy		2:poison
    private int cellEffectType;
    private boolean isPositive;
    private int changeAP;
    private int changeHP;
    private int changeMana;
    private int changeHollynes;
    private boolean noHolynessForOpponent;
    private int poison;
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

    public void setCellXY(int x, int y) {
        cellX = x;
        cellY = y;
    }

    public void setCells(ArrayList<Cell> cells) {
        this.cells = cells;
    }

    public void addToCell(Cell cell) {
        cells.add(cell);
    }

    public ArrayList<Cell> getCells() {
        return cells;
    }

    public static void addToCreateBuffs(Buff buff) {
        createdBuffs.add(buff);
    }

    public static ArrayList<Buff> getCreatedBuffs() {
        return createdBuffs;
    }

    public static Buff getFromCreated(String name) {
        for (Buff buff : createdBuffs) {
            if (buff.name.equals(name))
                return buff;
        }
        return null;
    }

    public Buff(
            String name,
            String CAP,
            String CHP,
            String CMana,
            String CHolly,
            String CMove,
            String CcanAttack,
            String CCounterAttack,
            String poison,
            String ageType,
            String age
    ) {
        this.name = name;
        this.changeAP = Integer.parseInt(CAP);
        this.changeHP = Integer.parseInt(CHP);
        this.changeMana = Integer.parseInt(CMana);
        this.changeHollynes = Integer.parseInt(CHolly);
        this.changeCanMove = Integer.parseInt(CMove);
        this.changeCanAttack = Integer.parseInt(CcanAttack);
        this.changeCanCounterAttack = Integer.parseInt(CCounterAttack);
        this.poison = Integer.parseInt(poison);
        this.ageType = Integer.parseInt(ageType);
        this.age = Integer.parseInt(age);
        createdBuffs.add(this);
        int a = 1;
    }

    public int getChangeMana() {
        return changeMana;
    }

    public int getCellEffectType() {
        return cellEffectType;
    }

    public boolean isABuff() {
        return exeptABuff;
    }

    public int getPoison() {
        return poison;
    }

    public boolean isDisableNegativeBuffs() {
        return disableNegativeBuffs;
    }

    public boolean isDisablePositiveBuffs() {
        return disablePositiveBuffs;
    }

    public boolean noHolynessForOpponent() {
        return noHolynessForOpponent;
    }

    public boolean isPositive() {
        return isPositive;
    }

    public int getPlusDamageToAttacked() {
        return plusDamageToAttacked;
    }

    public boolean isExeptABuff() {
        return exeptABuff;
    }

    public int getAge() {
        return age;
    }

    public boolean getIsCellBuff() {
        return isCellBuff;
    }

    public Buff getCellBuff() {
        return cellBuff;
    }

    public void setFighters(ArrayList<Fighter> fighters) {
        this.fighters = fighters;
    }

    public void decreesAge() {
        age--;
    }

    public ArrayList<Fighter> getFighters() {
        return fighters;
    }

    public int getAgeType() {
        return ageType;
    }

    public int getChangeHollynes() {
        return changeHollynes;
    }

    public int getChangeHP() {
        return changeHP;
    }

    public int getChangeAP() {
        return changeAP;
    }

    public int getChangeCanMove() {
        return changeCanMove;
    }

    public int getChangeCanCounterAttack() {
        return changeCanCounterAttack;
    }

    public int getChangeCanAttack() {
        return changeCanAttack;
    }
}
