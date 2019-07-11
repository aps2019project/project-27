package Server.Moudle;

import java.util.ArrayList;

public class Fighter extends MinionAndHero {
    private transient int AP;
    private transient int HP;
    private int x;
    private int y;
    private String ID;
    private transient int specialPowerCoolDown;
    private ArrayList<Fighter> attackedFighter = new ArrayList<>();
    private boolean canCounterAttack;
    private boolean haveLastFlag;
    private boolean canMove;
    private boolean canAttack;
    private int holyDefence;
    private ArrayList<Buff> buffs = new ArrayList<>();
    private Player player;

    public void resetSpecialPowerCoolDown() {
        specialPowerCoolDown = 0;
    }

    @Override
    public int getSpecialPowerCoolDown() {
        return specialPowerCoolDown;
    }

    public boolean isHaveLastFlag() {
        return haveLastFlag;
    }

    public void setHaveLastFlag(boolean haveLastFlag) {
        this.haveLastFlag = haveLastFlag;
    }

    public void preTurnProcces() {
        for (Buff buff : this.getSpecialPowers()) {
            if (this.getSpecialPowerType() == 1) {
                addToBuff(buff);
            }
        }
        specialPowerCoolDown++;
        this.enableBuffBoolEssence();
    }

    public void addToBuff(Buff buff) {
        if (buff.getAgeType() == 3) {
            return;
        }
        HP -= buff.getPoison();
        AP += buff.getChangeAP();
        HP += buff.getChangeHP();
        holyDefence += buff.getChangeHollynes();
        buffs.add(buff);
        if (buff.isDisableNegativeBuffs()) {
            for (Buff buff1 : this.buffs)
                if (!buff1.isPositive()) {
                    removeFromBuff(buff1);
                }
        }
        if (buff.isDisablePositiveBuffs()) {
            for (Buff buff1 : this.buffs)
                if (buff1.isPositive()) {
                    removeFromBuff(buff1);
                }
        }
    }

    public int howManyAttacked(Fighter fighter) {
        int counter = 0;
        for (Fighter fighter1 : attackedFighter) {
            if (fighter == fighter1) {
                counter++;
            }
        }
        return counter;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean CanAttack() {
        return canAttack;
    }

    public void disableCanAttack() {
        canAttack = false;
    }

    public void disableCanMove() {
        this.canMove = false;
    }

    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isCanCounterAttack() {
        return canCounterAttack;
    }

    @Override
    public int getHP() {
        return HP;
    }

    public void decreaseHP(int decrease) {
        HP -= decrease;
    }

    @Override
    public int getAP() {
        return AP;
    }

    public int getHolyDefence() {
        return holyDefence;
    }

    public boolean isCanMove() {
        return canMove;
    }

    public Player getPlayer() {
        return player;
    }

    public void removeFromBuff(Buff buff) {
        AP -= buff.getChangeAP();
        HP -= buff.getChangeHP();
        holyDefence -= buff.getChangeHollynes();
        buffs.remove(buff);
    }

    public void enableBuffBoolEssence() {
        canAttack = true;
        canMove = true;
        canCounterAttack = true;
        for (Buff buff : buffs) {
            booleanChangeInt(new booleanWrapper(canAttack), buff.getChangeCanAttack());
            booleanChangeInt(new booleanWrapper(canMove), buff.getChangeCanMove());
            booleanChangeInt(new booleanWrapper(canCounterAttack), buff.getChangeCanCounterAttack());
        }
    }

    public void addAttackedFighter(Fighter fighter) {
        attackedFighter.add(fighter);
    }

    public String getID() {
        return ID;
    }

    private void booleanChangeInt(booleanWrapper target, int changer) {
        if (changer == 1)
            target.aBoolean = true;
        if (changer == 2)
            target.aBoolean = false;
        if (changer == 3) {
            if (target.aBoolean)
                target.aBoolean = false;
            else target.aBoolean = true;
        }
    }

    protected Fighter(MinionAndHero minionAndHero, ArrayList<MinionAndHero> minionAndHeroes, Player player) {
        super(minionAndHero);
        AP = super.getAP();
        HP = super.getHP();
        int counter = 0;
        for (MinionAndHero minionAndHero1 : minionAndHeroes) {
            if (minionAndHero == minionAndHero1) {
                counter++;
            }
        }
        if (counter == 0)
            counter = 1;
        this.player = player;
        this.ID = player.getUserName() + "_" + minionAndHero.getName() + "_" + counter;
    }
}

class booleanWrapper {
    boolean aBoolean;

    booleanWrapper(boolean aBoolean) {
        this.aBoolean = aBoolean;
    }
}