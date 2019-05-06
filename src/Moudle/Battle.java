package Moudle;

import java.util.ArrayList;

public class Battle {
    private static Battle currentBattle;
    private Fighter selectedFighter;
    private ArrayList<MinionAndHero> minionAndHeroes;
    private Ground ground;
    private int currentTurn;
    private Player player1;
    private Player player2;
    private Player playerInTurn;
    private ArrayList<Fighter> fighters;
    private Card selectedCard;
    private Item selectedItem;
    private int battleType;
    private ArrayList<Item> flags;
    private int numberOfFlags;
    private Item mainFlag;
    private Fighter heroP1;
    private Fighter heroP2;
    private ArrayList<Buff> buffs;

    public static Battle getCurrentBattle() {
        return currentBattle;
    }

    public static void setCurrentBattle(Battle currentBattle) {
        Battle.currentBattle = currentBattle;
    }

    public Fighter getSelectedFighter() {
        return selectedFighter;
    }

    public void setSelectedFighter(Fighter selectedFighter) {
        this.selectedFighter = selectedFighter;
    }

    public ArrayList<MinionAndHero> getMinionAndHeroes() {
        return minionAndHeroes;
    }

    public void setMinionAndHeroes(ArrayList<MinionAndHero> minionAndHeroes) {
        this.minionAndHeroes = minionAndHeroes;
    }

    public Ground getGround() {
        return ground;
    }

    public void setGround(Ground ground) {
        this.ground = ground;
    }

    public int getCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(int currentTurn) {
        this.currentTurn = currentTurn;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public Player getPlayerInTurn() {
        return playerInTurn;
    }

    public void setPlayerInTurn(Player playerInTurn) {
        this.playerInTurn = playerInTurn;
    }

    public ArrayList<Fighter> getFighters() {
        return fighters;
    }

    public void setFighters(ArrayList<Fighter> fighters) {
        this.fighters = fighters;
    }

    public Card getSelectedCard() {
        return selectedCard;
    }

    public void setSelectedCard(Card selectedCard) {
        this.selectedCard = selectedCard;
    }

    public Item getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(Item selectedItem) {
        this.selectedItem = selectedItem;
    }

    public int getBattleType() {
        return battleType;
    }

    public void setBattleType(int battleType) {
        this.battleType = battleType;
    }

    public ArrayList<Item> getFlags() {
        return flags;
    }

    public void setFlags(ArrayList<Item> flags) {
        this.flags = flags;
    }

    public int getNumberOfFlags() {
        return numberOfFlags;
    }

    public void setNumberOfFlags(int numberOfFlags) {
        this.numberOfFlags = numberOfFlags;
    }

    public Item getMainFlag() {
        return mainFlag;
    }

    public void setMainFlag(Item mainFlag) {
        this.mainFlag = mainFlag;
    }

    public Fighter getHeroP1() {
        return heroP1;
    }

    public void setHeroP1(Fighter heroP1) {
        this.heroP1 = heroP1;
    }

    public Fighter getHeroP2() {
        return heroP2;
    }

    public void setHeroP2(Fighter heroP2) {
        this.heroP2 = heroP2;
    }

    public ArrayList<Buff> getBuffs() {
        return buffs;
    }

    public void setBuffs(ArrayList<Buff> buffs) {
        this.buffs = buffs;
    }


}
