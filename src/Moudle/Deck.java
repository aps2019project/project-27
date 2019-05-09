package Moudle;

import java.util.ArrayList;

public class Deck {
    private static ArrayList<Deck> decks = new ArrayList<>();
    private String name;
    private ArrayList<Card> cards = new ArrayList<>();
    //    private ArrayList<MinionAndHero> minionAndHeroes;
//    private ArrayList<Spell> spells;
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
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Card findCard(String cardName) {
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getName().equals(cardName)) {
                return cards.get(i);
            }
        }
        return null;
    }

    public void removeCard(Card card) {
        cards.remove(card);
    }

    public void removeItem(Item item) {
        this.item = null;
    }

    public void show() {
        System.out.println("\tHeroes :");
        int counterHero = 0;
        for (Card card : cards) {
            if (card.getCardType() == 1) {
                MinionAndHero minionAndHero = (MinionAndHero) card;
                if (minionAndHero.isHero()) {
                    printMinion(minionAndHero, counterHero);
                    counterHero++;
                }
            }
        }
        System.out.println("\tItems :");
        if (item != null) {
            System.out.printf("\t\t1 : Name:%s - Desc: - Sell Cost:%d\n", item.getName(), /*description*/
                    item.getPrice());
        }
        System.out.println("\tCards :");
        int counterCard = 0;
        for (Card card : cards) {
            if (card.getCardType() == 1) {
                MinionAndHero minionAndHero = (MinionAndHero) card;
                if (!minionAndHero.isHero()) {
                    printMinion(minionAndHero, counterCard);
                    counterCard++;
                }
            }
            if (card.getCardType() == 0) {
                System.out.printf("\t\t%d : Type:Spell - Name:%s - MP:%s - Desc: - Sell Cost:%d\n", counterCard + 1, card.getName(),
                        card.getManaPrice(), card.getShopPrice());
            }
        }
    }


    private static void printMinion(MinionAndHero minionAndHero, int counter) {
        System.out.printf("\t\t%d : Type:Minion - Name : %s - Class:%s - AP:%d - HP:%d - MP:%d - Special power:%s - Sell Cost:%d\n",
                counter + 1, minionAndHero.getName(), minionAndHero.getAttackType(), minionAndHero.getAP(),
                minionAndHero.getHP(), minionAndHero.getManaPrice(), minionAndHero.getSpecialPowerType(),
                minionAndHero.getShopPrice());
    }

    public boolean isValidDeck(Deck deck) {
//        todo
        return true;
    }
}
