package Moudle;

import java.util.ArrayList;

public class MinionAndHero extends Card {
    private static ArrayList<MinionAndHero> minionAndHeroes = new ArrayList<>();
    private boolean isHero;
    private int AP;
    private int HP;
    private int nation;
    private int attackType;
    private String[] abilities;
    private ArrayList<Buff> specialPowers=new ArrayList<>();
    private int specialPowerCoolDown;
    //0:onspawn 2:passive   3:ondeath   4:onAttack  5:onDe  6:combo
    private int specialPowerType;
    private Target specialPowerTarget;

    public Target getSpecialPowerTarget () {
        return specialPowerTarget;
    }

    public int getSpecialPowerType () {
        return specialPowerType;
    }

    public ArrayList<Buff> getSpecialPowers () {
        return specialPowers;
    }
    protected MinionAndHero( String name, int shopPrice, int manaPrice) {
        super(name,shopPrice, manaPrice,1);
    }
    protected MinionAndHero(MinionAndHero minionAndHero){
        super (minionAndHero.getName (),minionAndHero.getShopPrice (),minionAndHero.getManaPrice (),1 );
    }
    public int getAP () {
        return AP;
    }
    public int getHP () {
        return HP;
    }
    public static void setMinionAndHeroes( ArrayList<MinionAndHero> minionAndHeroes) {
        MinionAndHero.minionAndHeroes = minionAndHeroes;
    }
    public int getNation() {
        return nation;
    }
    public boolean isHero() {
        return isHero;
    }
}
