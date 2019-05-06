package Moudle;

import java.util.ArrayList;

public class Collection {
    private static ArrayList<Collection> collections;
    private Account account;
    private static ArrayList<Card> cards;
    private static ArrayList<MinionAndHero> minionAndHeroes;
    private static ArrayList<Spell> spells;
    private static ArrayList<Item> items;
    private ArrayList<Deck> decks;

    public ArrayList<Card> getCards() {
        return cards;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public static void exit() {

    }

    public static void show() {
        System.out.println("Heroes :");
        for (int i = 0; i < minionAndHeroes.size(); i++) {
            if (minionAndHeroes.get(i).isHero() == true) {
                System.out.printf("\t%d:Name:%s - AP:%d - HP:%d - Class:%s - Special power:%s - Sell Cost:%d\n",
                        i + 1, minionAndHeroes.get(i).getAP(), minionAndHeroes.get(i).getHP(),
                        minionAndHeroes.get(i).getAttackType(), minionAndHeroes.get(i).getSpecialPowerTypeMinion(), minionAndHeroes.get(i).getShopPrice());
            }
        }
        System.out.println("Items :");
        for (int i = 0; i < items.size(); i++) {
            System.out.printf("\t%d:Name:%d - Desc: - Sell Cost:%d\n", i + 1 /*description*/, items.get(i).getPrice());
        }
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

    public static void search(String name) {
        if (Card.findCard(name) == null && Item.findItem(name) == null) {
            System.out.println("There is no such card|item in collection");
        } else if (Card.findCard(name) != null && Item.findItem(name) == null) {
          //  System.out.printf("Your card id is %d\n", Card.findCard(name).getCardID());
        } else if (Card.findCard(name) == null && Item.findItem(name) != null) {
        //    System.out.printf("Your item id is %d\n", Item.findItem(name).getID());
        }
    }

    public static void save() {

    }

    public Card findCard(String cardName) {
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getName().equals(cardName)) {
                return cards.get(i);
            }
        }
        return null;
    }

    public Item findItem(String itemName) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().equals(itemName)) {
                return items.get(i);
            }
        }
        return null;
    }

    public Deck findDeck(String deckName) {
        for (int i = 0; i < decks.size(); i++) {
            if (decks.get(i).getName().equals(deckName)) {
                return decks.get(i);
            }
        }
        return null;
    }

    public void addToCards(Card card) {
        cards.add(card);
    }

    public void removeFromCards(Card card) {
        cards.remove(card);
    }

    public void addToItems(Item item) {
        items.add(item);
    }

    public void removeFromItems(Item item) {
        items.remove(item);
    }

    public void createDeck(String deckName) {
        if (findDeck(deckName) != null) {
            System.out.println("There is already a deck with this name!");
        } else {
            Deck deck = new Deck();
            deck.setName(deckName);
            decks.add(deck);
        }
    }

    public void deleteDeck(String deckName) {
        if (findDeck(deckName) == null) {
            System.out.println("There is no such deck in your collection!");
        } else {
            decks.remove(findDeck(deckName));
        }
    }

    public void add(String name, String deckName) {
        if (findCard(name) == null && findItem(name) == null) {
            System.out.println("This card|item doesn't exist in the collection!");
        } else if (findCard(name) != null && findItem(name) == null) {
            if (findDeck(deckName).findCard(name) != null) {
                System.out.println("This card is already in deck!");
            } else {
                if (findDeck(deckName).getCards().size() >= 21) {
                    System.out.println("You can't add anymore cards to deck!");
                } else {
                    findDeck(deckName).getCards().add(findCard(name));
                }
            }
        } else if (findCard(name) == null && findItem(name) != null) {
            if (findDeck(deckName).getItem() != null) {
                System.out.println("You can't add anymore items to deck!");
            } else {
                findDeck(deckName).setItem(findItem(name));
            }
        }
    }

    public void remove(String name, String deckName) {
        if (findDeck(deckName).findCard(name) == null && findDeck(deckName).getItem() == null) {
            System.out.println("This card|item doesn't exist in deck!");
        } else if (findDeck(deckName).findCard(name) != null && findDeck(deckName).getItem() == null) {
            findDeck(deckName).removeCard(findCard(name));
        } else if (findDeck(deckName).findCard(name) == null && findDeck(deckName).getItem() != null) {
            findDeck(deckName).removeItem(findItem(name));
        }
    }

    public void validateDeck(String deckName) {

    }

    public void selectDeck(String deckName) {
        if (findDeck(deckName) == null) {
            System.out.println("There is no such deck!");
        } else {
            account.setMainDeck(findDeck(deckName));
        }
    }

    public void showAllDecks() {
        if (account.getMainDeck() != null) {
            System.out.println("1:" + account.getMainDeck().getName() + ":");
            account.getMainDeck().show();
            int counter = 2;
            for (int i = 0; i < decks.size(); i++) {
                if (decks.get(i) != account.getMainDeck()) {
                    System.out.println(counter + ":" + decks.get(i).getName() + ":");
                    decks.get(i).show();
                    counter++;
                }
            }
        } else {
            int counter = 1;
            for (int i = 0; i < decks.size(); i++) {
                System.out.println(counter + " " + decks.get(i).getName() + ":");
                decks.get(i).show();
                counter++;
            }
        }
    }

    public void showDeck(String deckName) {
        if (findDeck(deckName) == null) {
            System.out.println("There is no such deck!");
        } else {
            findDeck(deckName).show();
        }
    }

    public static void help() {
        System.out.printf("exit\nshow\nsearch [card name | item name]\nsave\ncreate deck[deck name]\ndelete deck [deck name]\n" +
                "add[card id|card id|hero id]to deck[deck name]\nremove[card id|card id|hero id]from deck[deck name]\n" +
                "validate deck[deck name]\nselect deck[deck name]\nshow all decks\nshow deck[deck name]\nhelp\n");
    }
}
