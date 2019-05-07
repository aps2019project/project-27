package Moudle;

import Controller.ControlBox;

import java.util.ArrayList;

public class Shop {
    private static Shop currentShop = new Shop();
    private Account account = Account.getMainAccount();
    private ArrayList<Card> cards = Card.getCards();
    private ArrayList<Item> items = new ArrayList<Item>();

    public static void input(ControlBox controlBox) {
        String in = controlBox.getType();
        if (in.equals("showCollection")) {
            currentShop.showCollection();
        }
        if (in.equals("search")) {
            currentShop.search(controlBox.getCardName());
        }
        if (in.equals("searchCollection")) {
            currentShop.searchCollection(controlBox.getCardName());
        }
        if (in.equals("buy")) {
            currentShop.buy(controlBox.getCardName());
        }
        if (in.equals("sell")) {
            currentShop.sell(controlBox.getCardName());
        }
        if (in.equals("show")) {
            currentShop.show();
        }
        if (in.equals("help")) {
            currentShop.help();
        }
    }

    public static void setCurrentShop(Account account) {

    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

//    public ArrayList<MinionAndHero> getHeroesAndMinions() {
//        return heroesAndMinions;
//    }
//
//    public void setHeroesAndMinions(ArrayList<MinionAndHero> heroesAndMinions) {
//        this.heroesAndMinions = heroesAndMinions;
//    }

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
        System.out.println("Heroes :");
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
        System.out.println("Items :");
        int counterItem = 0;
        for (Item item : items) {
            System.out.printf("\t%d:Name:%d - Desc: - Sell Cost:%d\n", counterItem + 1 /*description*/, items.get(counterItem).getPrice());
            counterItem++;
        }
        System.out.println("Cards :");
        int counterCard = 0;
        for (Card card : cards) {
            if (card.getCardType() == 1) {
                MinionAndHero minionAndHero = (MinionAndHero) card;
                printMinion(minionAndHero, counterCard);
                counterCard++;
            }
            if (card.getCardType() == 0) {
                System.out.printf("\tType:Spell - Name:%s - MP:%s - Desc: - Sell Cost:%d\n", card.getName(), card.getManaPrice(),
                        card.getShopPrice());
            }
        }
        if (counterCard == 0 && counterHero == 0 && counterItem == 0) {
            System.out.println("There is nothing to show!");
        }
    }

    private void printMinion(MinionAndHero minionAndHero, int counter) {
        System.out.printf("\t%d : Type:Minion - Name : %s - Class:%s - AP:%d - HP:%d - MP:%d - Special power:%s - Sell Cost:%d\n",
                counter + 1, minionAndHero.getName(), minionAndHero.getAttackType(), minionAndHero.getAP(),
                minionAndHero.getHP(), minionAndHero.getManaPrice(), minionAndHero.getSpecialPowerType(),
                minionAndHero.getShopPrice());
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

    public String search(String name) {
        if (findCard(name) != null && findItem(name) == null) {
            return findCard(name).getName();
        } else if (findCard(name) == null && findItem(name) != null) {
            return findItem(name).getName();
        } else {
            System.out.println("This card|item is not in the shop!");
        }
        return null;
    }

    public String searchCollection(String name) {
        if (account.getCollection().findCard(name) != null && account.getCollection().findItem(name) == null) {
            return account.getCollection().findCard(name).getName();
        } else if (account.getCollection().findCard(name) == null && account.getCollection().findItem(name) != null) {
            return account.getCollection().findItem(name).getName();
        }
        System.out.println("This card|item is not in your collection!");
        return null;
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
                System.out.println("The card's been bought!");
            }
        } else if (findCard(name) == null && findItem(name) != null) {
            if (account.getMoney() < findItem(name).getPrice()) {
                System.out.println("You don't have enough money!");
            } else if (account.getCollection().getItems().size() >= 3) {
                System.out.println("You can't buy anymore items!");
            } else {
                account.getCollection().addToItems(findItem(name));
                account.spendMoney(findItem(name).getPrice());
                System.out.println("The item's been bought!");
            }
        }
    }

    public void sell(String name) {
        if (findCard(name) == null && findItem(name) == null) {
            System.out.println("You don't have this card|item!");
        } else if (findCard(name) != null && findItem(name) == null) {
            account.getCollection().removeFromCards(findCard(name));
            account.addMoney(findCard(name).getShopPrice());
            System.out.println("The card's been sold!");
        } else if (findCard(name) == null && findItem(name) != null) {
            account.getCollection().removeFromItems(findItem(name));
            account.addMoney(findItem(name).getPrice());
            System.out.println("The item's been sold!");
        }
    }

    public void show() {
        System.out.println("Heroes :");
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
        System.out.println("Items :");
        int counterItem = 0;
        for (Item item : items) {
            System.out.printf("\t%d:Name:%d - Desc: - Sell Cost:%d\n", counterItem + 1 /*description*/, items.get(counterItem).getPrice());
            counterItem++;
        }
        System.out.println("Cards :");
        int counterCard = 0;
        for (Card card : cards) {
            if (card.getCardType() == 1) {
                MinionAndHero minionAndHero = (MinionAndHero) card;
                printMinion(minionAndHero, counterCard);
                counterCard++;
            }
            if (card.getCardType() == 0) {
                System.out.printf("\tType:Spell - Name:%s - MP:%s - Desc: - Sell Cost:%d\n", card.getName(), card.getManaPrice(),
                        card.getShopPrice());
            }
        }
        if (counterCard == 0 && counterHero == 0 && counterItem == 0) {
            System.out.println("There is nothing to show!");
        }
    }

    public void help() {
        System.out.printf("exit\nshow collection\nsearch[item name|card name]\nsearch collection[item name|card name]\n" +
                "buy[card name|item name]\nsell[card id|item id]\nshow\n");
    }
}
