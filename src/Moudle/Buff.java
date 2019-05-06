package Moudle;

import java.util.ArrayList;

public class Buff {
    private String name;
    private ArrayList<Fighter> fighters;
    private int cellX, cellY;
    private boolean isCellBuff;
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
    //age type:0:unlimited	2:long age
    private int ageType;
    private int age;
    private Buff cellBuff;

    public void setCellXY(int x, int y) {
        cellX = x;
        cellY = y;
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
