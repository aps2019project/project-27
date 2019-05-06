package Moudle;

import java.util.ArrayList;

public class Spell extends Card {
    private ArrayList<Spell> spells = new ArrayList<Spell> (  );
    private ArrayList<Buff> mainBuffs;
    private ArrayList<Buff> buffs;
    private Target target;

    public ArrayList<Buff> getBuffs () {
        return buffs;
    }

    public ArrayList<Buff> getMainBuffs () {
        return mainBuffs;
    }

    public Target getTarget () {
        return target;
    }
    public void setSpells ( ArrayList<Spell> spells ) {
        this.spells = spells;
    }
    protected Spell ( String name , int cardID , int shopPrice , int manaPrice ) {
        super ( name , cardID , shopPrice , manaPrice );
    }
}
