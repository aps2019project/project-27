package Moudle;

import Controller.ControlBox;

import java.util.ArrayList;

public class Shop {
    private Account account;
    private ArrayList<MinionAndHero> heroesAndMinions;
    private ArrayList<Card> cards;
    private ArrayList<Item> items;

    public static void input( ControlBox controlBox ){

    }
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public ArrayList<MinionAndHero> getHeroesAndMinions() {
        return heroesAndMinions;
    }

    public void setHeroesAndMinions(ArrayList<MinionAndHero> heroesAndMinions) {
        this.heroesAndMinions = heroesAndMinions;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public void exit() {

    }

    public void showCollection() {
        System.out.println(account.getCollection().getCards());
        System.out.println(account.getCollection().getItems());
    }

    public Card findCard(String name) {
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getName().equals(name)) {
                return cards.get(i);
            }
        }
        return null;
    }

    public Item findItem(String name) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().equals(name)) {
                return items.get(i);
            }
        }
        return null;
    }

    public int search(String name) {
        if (findCard(name) != null && findItem(name) == null) {
         //   return findCard(name).getCardID();
        } else if (findCard(name) == null && findItem(name) != null) {
         //   return findItem(name).getID();
        }
        System.out.println("This card|item is not in the shop!");
        return 0;
    }

    public int searchCollection(String name) {
        if (account.getCollection().findCard(name) != null && account.getCollection().findItem(name) == null) {
     //       return account.getCollection().findCard(name).getCardID();
        } else if (account.getCollection().findCard(name) == null && account.getCollection().findItem(name) != null) {
      //      return account.getCollection().findItem(name).getID();
        }
        System.out.println("This card|item is not in your collection!");
        return 0;
    }

    public void buy(String name) {
        if (findCard(name) == null && findItem(name) == null) {
            System.out.println("This card|item is not in the shop!");
        } else if (findCard(name) != null && findItem(name) == null) {
            if (account.getMoney() < findCard(name).getShopPrice()) {
                System.out.println("You don't have enough money!");
            } else {
                account.getCollection().addToCards(findCard(name));
                account.spendMoney(findCard(name).getShopPrice());
            }
        } else if (findCard(name) == null && findItem(name) != null) {
            if (account.getMoney() < findItem(name).getPrice()) {
                System.out.println("You don't have enough money!");
            } else if (account.getCollection().getItems().size() >= 3) {
                System.out.println("You can't buy anymore items!");
            } else {
                account.getCollection().addToItems(findItem(name));
                account.spendMoney(findItem(name).getPrice());
            }
        }
    }

    public void sell(String name) {
        if (findCard(name) == null && findItem(name) == null) {
            System.out.println("You don't have this card|item!");
        } else if (findCard(name) != null && findItem(name) == null) {
            account.getCollection().removeFromCards(findCard(name));
            account.addMoney(findCard(name).getShopPrice());
        } else if (findCard(name) == null && findItem(name) != null) {
            account.getCollection().removeFromItems(findItem(name));
            account.addMoney(findItem(name).getPrice());
        }
    }

    public void show() {
        System.out.println(cards);
        System.out.println(items);
    }

    public void help() {
        System.out.printf("exit\nshow collection\nsearch[item name|card name]\nsearch collection[item name|card name]\n" +
                "buy[card name|item name]\nsell[card id|item id]\nshow\n");
    }
}
