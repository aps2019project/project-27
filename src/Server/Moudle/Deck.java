package Server.Moudle;

import java.util.ArrayList;

public class Deck {
    private static ArrayList<Deck> decks = new ArrayList<>();
    private String name;
    //private ArrayList<Card> cards = new ArrayList<>();
    private ArrayList<MinionAndHero> minionAndHeroes = new ArrayList<>();
    private ArrayList<Spell> spells = new ArrayList<>();
    private Item item;

    public static Deck findDeck(String deckName) {
        for (int i = 0; i < decks.size(); i++) {
            if (decks.get(i).name.equals(deckName)) {
                return decks.get(i);
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Card> getCards() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.addAll(spells);
        cards.addAll(minionAndHeroes);
        return cards;
    }

    public void addToCarts(Card card) {
        if (card.getCardType() == 0)
            spells.add((Spell) card);
        if (card.getCardType() == 1)
            minionAndHeroes.add((MinionAndHero) card);
    }
    public void removeCard(Card card) {
        if (card.getCardType() == 0)
            spells.remove((Spell) card);
        if (card.getCardType() == 1)
            minionAndHeroes.remove((MinionAndHero) card);
    }
    public ArrayList<MinionAndHero> getMinionAndHeroes() {
        return minionAndHeroes;
    }

    public ArrayList<Spell> getSpells() {
        return spells;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Card findCard(String cardName) {
        for (Card card : minionAndHeroes) {
            if (card.getName().equals(cardName)) {
                return card;
            }
        }
        for (Card card : spells) {
            if (card.getName().equals(cardName)) {
                return card;
            }
        }
        return null;
    }



    public void removeItem(Item item) {
        this.item = null;
    }

    public void show() {
        System.out.println("\tHeroes :");
        int counterHero = 0;
        for (MinionAndHero minionAndHero : minionAndHeroes) {
            if (minionAndHero.isHero()) {
                printMinion(minionAndHero, counterHero);
                counterHero++;
            }
        }
        System.out.println("\tItems :");
        if (item != null) {
            System.out.printf("\t\t1 : Name:%s - Desc: - Sell Cost:%d\n", item.getName(), /*description*/
                    item.getPrice());
        }
        System.out.println("\tCards :");
        int counterCard = 0;
        for (MinionAndHero minionAndHero : minionAndHeroes) {
            if (!minionAndHero.isHero()) {
                printMinion(minionAndHero, counterCard);
                counterCard++;
            }
        }
        for (Spell spell : spells) {
            System.out.printf("\t\t%d : Type:Spell - Name:%s - MP:%s - Desc: - Sell Cost:%d\n", counterCard + 1, spell.getName(),
                    spell.getManaPrice(), spell.getShopPrice());
        }
    }


    private static void printMinion(MinionAndHero minionAndHero, int counter) {
        System.out.printf("\t\t%d : Type:Minion - Name : %s - Class:%s - AP:%d - HP:%d - MP:%d - Special power:%s - Sell Cost:%d\n",
                counter + 1, minionAndHero.getName(), minionAndHero.getAttackType(), minionAndHero.getAP(),
                minionAndHero.getHP(), minionAndHero.getManaPrice(), minionAndHero.getSpecialPowerType(),
                minionAndHero.getShopPrice());
    }

    public boolean isValidDeck() {
        return Collection.isValidDeck(this);
    }
}
