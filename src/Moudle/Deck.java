package Moudle;

import java.util.ArrayList;

public class Deck {
    private static ArrayList<Deck> decks;
    private String name;
    private ArrayList<Card> cards;
    private ArrayList<MinionAndHero> minionAndHeroes;
    private ArrayList<Spell> spells;
    private Item item;

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

    public static Deck findDeck(String deckName) {
        for (int i = 0; i < decks.size(); i++) {
            if (decks.get(i).name.equals(deckName)) {
                return decks.get(i);
            }
        }
        return null;
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
        System.out.println("Heroes :");
        for (int i = 0; i < minionAndHeroes.size(); i++) {
            if (minionAndHeroes.get(i).isHero() == true) {
                System.out.printf("\t%d:Name:%s - AP:%d - HP:%d - Class:%s - Special power:%s - Sell Cost:%d\n",
                        i + 1, minionAndHeroes.get(i).getAP(), minionAndHeroes.get(i).getHP(),
                        minionAndHeroes.get(i).getAttackType(), minionAndHeroes.get(i).getSpecialPowerTypeMinion(), minionAndHeroes.get(i).getShopPrice());
            }
        }
        System.out.println("Items :");
        System.out.printf("\t%d:Name:%d - Desc: - Sell Cost:%d\n", 1 /*description*/, item.getPrice());

        System.out.println("Cards :");
        int counter = 0;
        for (int i = 0; i < minionAndHeroes.size(); i++) {
            if (minionAndHeroes.get(i).isHero() == false) {
                System.out.printf("\tType:Minion - Class:%s - AP:%d - HP:%d - MP:%d - Special power:%s - Sell Cost:%d\n",
                        minionAndHeroes.get(i).getAttackType(), minionAndHeroes.get(i).getAP(),
                        minionAndHeroes.get(i).getHP(), minionAndHeroes.get(i).getManaPrice(), minionAndHeroes.get(i).getSpecialPowerTypeMinion(),
                        minionAndHeroes.get(i).getShopPrice());
                counter++;
            }
        }
        for (int i = 0; i < spells.size(); i++) {
            System.out.printf("\tType:Spell - Name:%s - MP:%s - Desc: - Sell Cost:%d\n", spells.get(i).getName(), spells.get(i).getManaPrice(),
                    spells.get(i).getShopPrice());
        }
    }
}
