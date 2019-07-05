package Server.Moudle;

import Client.View.View;

import java.util.ArrayList;

public class Player {
    private String UserName;
    private ArrayList<Fighter> fighters = new ArrayList<>();
    private Hand hand;
    private int mana;
    private Account account;
    private ArrayList<Item> collectedItems = new ArrayList<> (  );
    private ArrayList<Card> graveYard = new ArrayList<> (  );
    private Item mainItem;
    private boolean haveLastFlag;
    private ArrayList<Buff> buffs = new ArrayList<> (  );
    private int flagInHand = 0;

    public void increaseFlagInHand() {
        flagInHand++;
    }

    public int getFlagInHand() {
        return flagInHand;
    }

    public ArrayList<Item> getCollectedItems() {
        return collectedItems;
    }

    public boolean isHaveLastFlag() {
        return haveLastFlag;
    }

    public void setHaveLastFlag(boolean haveLastFlag) {
        this.haveLastFlag = haveLastFlag;
    }

    public int getMana() {
        return mana;
    }

    public void preTurnProcess() {
        if (hand.getCards().size() < 5) {
            hand.addCard();
            hand.setNextCard();
        }
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public Player(Account account) {

        this.UserName = account.getUserName ();
        this.account = account;
        hand = new Hand(account.getMainDeck());
    }

    public Account getAccount() {
        return account;
    }

    public String getUserName() {
        return UserName;
    }

    public void addToBuffs(Buff buff) {
        mana += buff.getChangeMana();
        buffs.add(buff);
    }

    public void removeFromBuff(Buff buff) {
        buffs.remove(buff);
    }

    public Item getMainItem() {
        return mainItem;
    }

    public Hand getHand() {
        return hand;
    }

    public void addToGraveYard(Fighter fighter) {
        fighters.remove ( fighter );
        graveYard.add(fighter);
    }

    public void decreaseMana(int mony) {
        mana -= mony;
    }

    public void addItem(Item item) {
        collectedItems.add(item);
    }

    public ArrayList<Fighter> getFighters() {
        return fighters;
    }

    public void addFighter(Fighter fighter) {
        fighters.add(fighter);
    }

    public boolean equals(Object object) {
        if (object.getClass() == Player.class) {
            Account account = this.getAccount();
            if (account.getUserName().equals(this.getUserName()))
                return true;
            return false;
        } else return false;
    }

    public void insert() {

    }

    public void showCollectables() {
        for (Item item : collectedItems) {
            item.showItem();
        }
    }

    public void showFighters() {
        for (Fighter fighter : fighters) {
            View.showFighter (fighter);
        }
    }

    public ArrayList<Card> getGraveYard () {
        return graveYard;
    }

    public void showGraveYard() {
        System.out.println("Heroes :");
        int counterHero = 0;
        for (Card card : graveYard) {
            if (card.getCardType() == 1) {
                MinionAndHero minionAndHero = (MinionAndHero) card;
                if (minionAndHero.isHero()) {
                    View.printMinion(minionAndHero, counterHero);
                    counterHero++;
                }
            }
        }
        System.out.println("Items :");
        int counterItem = 0;
        for (Item item : Account.getMainAccount().getCollection().getItems()) {
            System.out.printf("\t%d:Name:%s - Desc: - Sell Cost:%d\n", counterItem + 1 /*description*/, item.getName(), item.getPrice());
            counterItem++;
        }
        System.out.println("Cards :");
        int counterCard = 0;
        for (Card card : graveYard) {
            if (card.getCardType() == 1) {
                MinionAndHero minionAndHero = (MinionAndHero) card;
                if (!minionAndHero.isHero()) {
                    View.printMinion(minionAndHero, counterCard);
                    counterCard++;
                }
            }
            if (card.getCardType() == 0) {
                System.out.printf("\t%d : Type:Spell - Name:%s - MP:%s - Desc: - Sell Cost:%d\n", counterCard + 1, card.getName(),
                        card.getManaPrice(), card.getShopPrice());
            }
        }
    }
    public void showHand() {
        hand.show();
    }
}