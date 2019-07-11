package Server.Moudle;

import ControlBox.ControlBox;
import Client.View.View;
import javafx.scene.control.Alert;

import java.util.ArrayList;

public class Shop {
    private static Shop currentShop = new Shop();
    private static ArrayList<Card> cards = new ArrayList<>();
    private static ArrayList<Item> items = new ArrayList<>();

    public static void input(ControlBox controlBox) {
        String in = controlBox.getType();
        cards = controlBox.getCards();
        items = controlBox.getItems();
        int a = 1;
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
            currentShop.buy(controlBox.getCardName(), controlBox);
        }
        if (in.equals("sell")) {
            currentShop.sell(controlBox.getCardName(), controlBox);
        }
        if (in.equals("show")) {
            currentShop.show();
        }
        if (in.equals("help")) {
            currentShop.help();
        }
    }

    public static void setCurrentShop() {

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

    public static void setCards(ArrayList<Card> card) {
        Shop.cards = card;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public static void setItems(ArrayList<Item> item) {
        Shop.items = item;
    }

    public void exit() {

    }

    public void showCollection() {
        System.out.println("Heroes :");
        int counterHero = 0;
        for (Card card : Account.getMainAccount().getCollection().getCards()) {
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
        for (Card card : Account.getMainAccount().getCollection().getCards()) {
            if (card.getCardType() == 1) {
                MinionAndHero minionAndHero = (MinionAndHero) card;
                View.printMinion(minionAndHero, counterCard);
                counterCard++;
            }
            if (card.getCardType() == 0) {
                System.out.printf("\t%d : Type:Spell - Name:%s - MP:%s - Desc: - Sell Cost:%d\n", counterCard + 1, card.getName(),
                        card.getManaPrice(), card.getShopPrice());
            }
        }
    }

    public Card findCard(String name) {
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getName().equalsIgnoreCase(name)) {
                return cards.get(i);
            }
        }
        return null;
    }

    public Item findItem(String name) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().equalsIgnoreCase(name)) {
                return items.get(i);
            }
        }
        return null;
    }

    public void search(String name) {
        for (Card card : cards) {
            for (int i = 1; i < name.length(); i++) {
                if (card.getName().contains(name.subSequence(0, i))) {
                    System.out.println(card.getName());
                }
            }
        }
        System.out.println("This card|item is not in the shop!");
    }

    public void searchCollection(String name) {
        if (Account.getMainAccount().getCollection().findCard(name) != null && Account.getMainAccount().getCollection().findItem(name) == null) {
            System.out.println(Account.getMainAccount().getCollection().findCard(name).getName());
        } else if (Account.getMainAccount().getCollection().findCard(name) == null && Account.getMainAccount().getCollection().findItem(name) != null) {
            System.out.println(Account.getMainAccount().getCollection().findItem(name).getName());
        } else {
            System.out.println("This card|item is not in your collection!");
        }
    }

    public void buy(String name, ControlBox controlBox) {
        if (findCard(name) == null && findItem(name) == null) {
            System.out.println("This card|item is not in the shop!");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("This card|item is not in the shop!");
            alert.showAndWait();
        } else if (findCard(name) != null && findCard(name).getTedad() != 0) {
            if (Account.getMainAccount().getMoney() < findCard(name).getShopPrice()) {
                System.out.println("You don't have enough money!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("You don't have enough money");
                alert.showAndWait();
            } else {
                Account.getMainAccount().getCollection().addToCards(findCard(name));
                Account.getMainAccount().spendMoney(findCard(name).getShopPrice());
                controlBox.spendMoney(findCard(name).getShopPrice());
                findCard(name).removeFromTedad();
                controlBox.setRemaining(findCard(name).getTedad());
                System.out.println("The card's been bought!");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("The card's been bought");
                alert.showAndWait();
            }
        } else if (findItem(name) != null) {
            if (Account.getMainAccount().getMoney() < findItem(name).getPrice()) {
                System.out.println("You don't have enough money!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("You don't have enough money");
                alert.showAndWait();
            } else if (Account.getMainAccount().getCollection().getItems().size() >= 3) {
                System.out.println("You can't buy anymore items!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("You can't buy anymore items");
                alert.showAndWait();
            } else {
                Account.getMainAccount().getCollection().addToItems(findItem(name));
                Account.getMainAccount().spendMoney(findItem(name).getPrice());
                controlBox.spendMoney(findItem(name).getPrice());
                System.out.println("The item's been bought!");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("The item's been bought");
                alert.showAndWait();
            }
        }
    }

    public void sell(String name, ControlBox controlBox) {

        if (Account.getMainAccount().getCollection().findCard(name) == null && Account.getMainAccount().getCollection().findItem(name) == null) {
            System.out.println("You don't have this card|item!");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("You don't have this card|item");
            alert.showAndWait();
        } else if (Account.getMainAccount().getCollection().findCard(name) != null && Account.getMainAccount().getCollection().findCard(name).getTedad() != 0) {
            Account.getMainAccount().getCollection().removeFromCards(findCard(name));
            Account.getMainAccount().addMoney(findCard(name).getShopPrice());
            controlBox.addMoney(findCard(name).getShopPrice());
            System.out.println("The card's been sold!");
            Account.getMainAccount().getCollection().findCard(name).addToTedad();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("The card's been sold");
            alert.showAndWait();
        } else if (Account.getMainAccount().getCollection().findItem(name) != null) {
            Account.getMainAccount().getCollection().removeFromItems(findItem(name));
            Account.getMainAccount().addMoney(findItem(name).getPrice());
            controlBox.addMoney(findItem(name).getPrice());
            System.out.println("The item's been sold!");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("The item's been sold");
            alert.showAndWait();
        }
    }

    public void show() {
        System.out.println("Heroes :");
        int counterHero = 0;
        for (Card card : cards) {
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
        for (Item item : items) {
            System.out.printf("\t%d:Name:%s - Desc: - Sell Cost:%d\n", counterItem + 1, item.getName() /*description*/, item.getPrice());
            counterItem++;
        }
        System.out.println("Cards :");
        int counterCard = 0;
        for (Card card : cards) {
            if (card.getCardType() == 1) {
                MinionAndHero minionAndHero = (MinionAndHero) card;
                if (!minionAndHero.isHero()) {
                    View.printMinion(minionAndHero, counterCard);
                    counterCard++;
                }
            }
            if (card.getCardType() == 0) {
                System.out.printf("\tType:Spell - Name:%s - MP:%s - Desc: - Sell Cost:%d\n", card.getName(), card.getManaPrice(),
                        card.getShopPrice());
            }
        }
    }

    public void help() {
        System.out.printf("exit\nshow collection\nsearch[item name|card name]\nsearch collection[item name|card name]\n" +
                "buy[card name|item name]\nsell[card id|item id]\nshow\n");
    }
}
