package Moudle;

import java.util.ArrayList;

public class MinionAndHero extends Card {
    private static ArrayList<MinionAndHero> minionAndHeroes = new ArrayList<>();
    private boolean isHero;
    private int AP;
    private int HP;
    private int range;
    private int attackType;
    private ArrayList<Buff> specialPowers = new ArrayList<>();
    private int specialPowerMana;
    private int specialPowerCoolDown;
    //0:onSpawn 2:passive   3:onDeath   4:onAttack  5:onDe  6:combo 7:use with select
    private int specialPowerType;
    private Target specialPowerTarget;
    public MinionAndHero(
            String name,
            String AP,
            String HP,
            String AType,
            String range,
            String SPMan,
            String SPTyp,
            Buff buff,
            Target targe,
            String image,
            String cost,
            String mana,
            boolean isHero
    ){
        super (name,image,cost,mana,1 );
        this.AP = Integer.parseInt ( AP );
        this.HP = Integer.parseInt ( HP );
        this.attackType = Integer.parseInt ( AType );
        this.range = Integer.parseInt ( range );
        this.isHero = isHero;
        this.specialPowerTarget = targe;
        this.specialPowers.add ( buff );
        this.specialPowerType = Integer.parseInt ( SPTyp );
        if ( isHero ){
            specialPowerCoolDown = Integer.parseInt ( SPMan );
        }
        else {
            specialPowerMana = Integer.parseInt ( SPMan );
        }
        minionAndHeroes.add ( this );
    }
    public static ArrayList<MinionAndHero> getMinionAndHeroes () {
        return minionAndHeroes;
    }

    public Target getSpecialPowerTarget() {
        return specialPowerTarget;
    }

    public int getRange () {
        return range;
    }

    public int getSpecialPowerCoolDown() {
        return specialPowerCoolDown;
    }

    public int getSpecialPowerMana() {
        return specialPowerMana;
    }

    public int getSpecialPowerType() {
        return specialPowerType;
    }

    public ArrayList<Buff> getSpecialPowers() {
        return specialPowers;
    }

    protected MinionAndHero(String name, int shopPrice, int manaPrice,String image) {
        super(name, shopPrice, manaPrice, 1,image);
    }

    protected MinionAndHero(MinionAndHero minionAndHero) {
        super(minionAndHero.getName(), minionAndHero.getShopPrice(), minionAndHero.getManaPrice(), 1,minionAndHero.getImage ());
        AP = minionAndHero.AP;
        HP = minionAndHero.HP;
        isHero = minionAndHero.isHero;
        range = minionAndHero.range;
        attackType = minionAndHero.attackType;
        specialPowerMana = minionAndHero.specialPowerMana;
        specialPowerCoolDown = minionAndHero.specialPowerCoolDown;
        specialPowerType = minionAndHero.specialPowerType;
        specialPowers = minionAndHero.specialPowers;
        specialPowerTarget = minionAndHero.specialPowerTarget;
    }

    public int getAP() {
        return AP;
    }

    public int getHP() {
        return HP;
    }

    public static void setMinionAndHeroes(ArrayList<MinionAndHero> minionAndHeroes) {
        MinionAndHero.minionAndHeroes = minionAndHeroes;
    }

    public boolean isHero() {
        return isHero;
    }

    public int getAttackType() {
        return attackType;
    }
}
