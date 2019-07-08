package Server.Moudle;

import Client.View.View;
import ControlBox.ControlBox;

import java.util.ArrayList;

public class Collection {
    private static ArrayList<Collection> collections = new ArrayList<>();
    private ArrayList<Spell> spells = new ArrayList<> ();
    private ArrayList<MinionAndHero> minionAndHeroes = new ArrayList<> ();
    private ArrayList<Item> items = new ArrayList<>();
    private ArrayList<Deck> decks;


    public static int input( ControlBox controlBox) {
        String in = controlBox.getType();
        if (in.equals("show")) {
            Account.getMainAccount().getCollection().show();
        }
        if (in.equals("searchCollection")) {
            Account.getMainAccount().getCollection().search(controlBox.getCardName());
        }
        if (in.equals("createDeck")) {
            Account.getMainAccount().getCollection().createDeck(controlBox.getDeckName());
        }
        if (in.equals("deleteDeck")) {
            Account.getMainAccount().getCollection().deleteDeck(controlBox.getDeckName());
        }
        if (in.equals("add")) {
            Account.getMainAccount().getCollection().add(controlBox.getCardName(), controlBox.getDeckName());
        }
        if (in.equals("remove")) {
            Account.getMainAccount().getCollection().remove(controlBox.getCardName(), controlBox.getDeckName());
        }
        if (in.equals("validateDeck")) {
            Account.getMainAccount().getCollection().validateDeck(controlBox.getDeckName());
        }
        if (in.equals("selectDeck")) {
            Account.getMainAccount().getCollection().selectDeck(controlBox.getDeckName());
        }
        if (in.equals("showAllDecks")) {
            Account.getMainAccount().getCollection().showAllDecks();
        }
        if (in.equals("showDeck")) {
            Account.getMainAccount().getCollection().showDeck(controlBox.getDeckName());
        }
        if (in.equals("help")) {
            help();
        }
        return 0;
    }

    public static ArrayList<Collection> getCollections() {
        return collections;
    }

    public static void exit() {

    }



    public static void help() {
        System.out.printf("exit\nshow\nsearch [card name | item name]\nsave\ncreate deck[deck name]\ndelete deck [deck name]\n" +
                "add[card id|card id|hero id]to deck[deck name]\nremove[card id|card id|hero id]from deck[deck name]\n" +
                "validate deck[deck name]\nselect deck[deck name]\nshow all decks\nshow deck[deck name]\nhelp\n");
    }

    public ArrayList<Card> getCards() {
        ArrayList<Card> cards = new ArrayList<> (  );
        cards.addAll ( spells);
        cards.addAll ( minionAndHeroes );
        return cards;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void show() {
        System.out.println("Heroes :");
        int counterHero = 0;
        for (MinionAndHero minionAndHero:minionAndHeroes) {
                if (minionAndHero.isHero()) {
                    View.printMinion (minionAndHero, counterHero);
                    counterHero++;
                }
        }
        System.out.println("Items :");
        int counterItem = 0;
        for (Item item : Account.getMainAccount().getCollection().getItems()) {
            System.out.printf("\t%d : Name:%s - Desc: - Sell Cost:%d\n", counterItem + 1 /*description*/,
                    item.getName(), item.getPrice());
            counterItem++;
        }
        System.out.println("Cards :");
        int counterCard = 0;
        for (MinionAndHero minionAndHero:minionAndHeroes) {
            if ( ! minionAndHero.isHero ( ) ) {
                View.printMinion ( minionAndHero , counterCard );
                counterCard++;
            }
        }
            for ( Spell spell:spells){
                System.out.printf("\t%d : Type:Spell - Name:%s - MP:%s - Desc: - Sell Cost:%d\n", counterCard + 1, spell.getName(),
                        spell.getManaPrice(), spell.getShopPrice());
            }
    }
    public void search(String name) {
        if (Account.getMainAccount().getCollection().findCard(name) == null && Account.getMainAccount().getCollection().findItem(name) == null) {
            System.out.println("There is no such card|item in collection");
        } else if (Account.getMainAccount().getCollection().findCard(name) != null && Account.getMainAccount().getCollection().findItem(name) == null) {
            System.out.println(Account.getMainAccount().getCollection().findCard(name).getName());
        } else if (Account.getMainAccount().getCollection().findCard(name) == null && Account.getMainAccount().getCollection().findItem(name) != null) {
            System.out.println(Account.getMainAccount().getCollection().findItem(name).getName());
        }
    }

    public Card findCard(String cardName) {
        ArrayList<Card> cards = new ArrayList (  );
        cards.addAll ( minionAndHeroes );
        cards.addAll ( spells );
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
        if ( card.getCardType ()==0 )
            spells.add ( ( Spell ) card );
        if ( card.getCardType ()==1 )
            minionAndHeroes.add ( ( MinionAndHero ) card );

    }

    public void removeFromCards(Card card) {
        if ( card.getCardType ()==0 )
            spells.remove ( ( Spell ) card );
        if ( card.getCardType ()==1 )
            minionAndHeroes.remove ( ( MinionAndHero ) card );
    }

    public void addToItems(Item item) {
        items.add(item);
    }

    public void removeFromItems(Item item) {
        items.remove(item);
    }

    public void createDeck(String deckName) {
        if (Account.getMainAccount().findDeck(deckName) != null) {
            System.out.println("There is already a deck with this name!");
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setHeaderText("There is already a deck with this name");
//            alert.showAndWait();
        } else {
            Deck deck = new Deck();
            deck.setName(deckName);
            Account.getMainAccount().getDecks().add(deck);
            System.out.println("Deck's been created!");
//            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//            alert.setHeaderText("Deck's been created");
//            alert.showAndWait();
        }
    }

    public void deleteDeck(String deckName) {
        if (Account.getMainAccount().findDeck(deckName) == null) {
            System.out.println("There is no such deck in your collection!");
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setHeaderText("There is no suck deck in your collection");
//            alert.showAndWait();
        } else {
            Account.getMainAccount().getDecks().remove(Account.getMainAccount().findDeck(deckName));
            System.out.println("Deck's been deleted!");
//            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//            alert.setHeaderText("Deck's been deleted");
//            alert.showAndWait();
        }
    }

    public void add(String name, String deckName) {
        Account account = Account.getMainAccount();
        if (account.getCollection().findCard(name) == null && Account.getMainAccount().getCollection().findItem(name) == null) {
            System.out.println("This card|item doesn't exist in the collection!");
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setHeaderText("This card|item doesn't exist in the collection");
//            alert.showAndWait();
        } else if (account.getCollection().findCard(name) != null && Account.getMainAccount().getCollection().findItem(name) == null) {
           //if (account.findDeck(deckName).findCard(name) != null) {
             //   System.out.println("This card is already in deck!");
            //}
            //else
                {
                if (account.findDeck(deckName).getCards().size() >= 21) {
                    System.out.println("You can't add anymore cards to deck!");
//                    Alert alert = new Alert(Alert.AlertType.ERROR);
//                    alert.setHeaderText("You can't add anymore cards to deck");
//                    alert.showAndWait();
                } else {
                    account.findDeck(deckName).addToCarts(findCard(name));
                    Deck deck = account.findDeck ( deckName );
                    System.out.println("The card's been added to the deck!");
//                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//                    alert.setHeaderText("The card's been added to the deck");
//                    alert.showAndWait();
                }
            }
        } else if (account.getCollection().findCard(name) == null && Account.getMainAccount().getCollection().findItem(name) != null) {
            if (account.findDeck(deckName).getItem() != null) {
                System.out.println("You can't add anymore items to deck!");
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setHeaderText("You can't add anymore items to deck");
//                alert.showAndWait();
            } else {
                account.findDeck(deckName).setItem(findItem(name));
                System.out.println("The item's been added to the deck!");
//                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//                alert.setHeaderText("The item's been added to the deck");
//                alert.showAndWait();
            }
        }
    }

    public void remove(String name, String deckName) {
        Account account = Account.getMainAccount();
        if (account.findDeck(deckName).findCard(name) == null && account.findDeck(deckName).getItem() == null) {
            System.out.println("This card|item doesn't exist in deck!");
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setHeaderText("This card|item doesn't exist in deck");
//            alert.showAndWait();
        } else if (account.findDeck(deckName).findCard(name) != null) {
            account.findDeck(deckName).removeCard(findCard(name));
            System.out.println("The card's been removed from the deck!");
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setHeaderText("This card's been removed from the deck");
//            alert.showAndWait();
        } else if (account.findDeck(deckName).getItem() != null) {
            account.findDeck(deckName).removeItem(findItem(name));
            System.out.println("The item's been removed from the deck!");
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setHeaderText("This item's been removed from the deck");
//            alert.showAndWait();
        }
    }

    public boolean validateDeck(String deckName) {
        Deck deck = Account.getMainAccount().findDeck(deckName);
        return isValidDeck ( deck );
    }

    public static boolean isValidDeck (  Deck deck ) {
        if ( deck == null ) {
            System.out.println ("you havent this deck" );
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setHeaderText("You don't have this deck");
//            alert.showAndWait();
            return false;
        }
        if ( deck.getCards().size() == 21) {
            int counter = 0;
            for ( Card card : deck.getCards()) {
                if (card.getCardType() == 1) {
                    MinionAndHero minionAndHero = (MinionAndHero) card;
                    if (minionAndHero.isHero()) {
                        counter++;
                    }
                }
            }
            if (counter == 1) {
                System.out.println("This deck is valid!");
//                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//                alert.setHeaderText("This deck is valid");
//                alert.showAndWait();
                return true;
            }
        } else {
            System.out.println("This deck is not valid!");
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setHeaderText("This deck is not valid");
//            alert.showAndWait();
            return false;
        }
        return true;
    }

    public void selectDeck(String deckName) {
        if (Account.getMainAccount().findDeck(deckName) == null) {
            System.out.println("There is no such deck!");
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setHeaderText("There is no such deck");
//            alert.showAndWait();
        } else {
            Account.getMainAccount().setMainDeck(Account.getMainAccount().findDeck(deckName));
            System.out.println("The deck's been selected!");
//            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//            alert.setHeaderText("The deck's been selected");
//            alert.showAndWait();
        }
    }

    public void showAllDecks() {
        if (Account.getMainAccount().getMainDeck() == null) {
            int i = 0;
            for (Deck deck : Account.getMainAccount().getDecks()) {
                System.out.printf("%d : %s :\n", i + 1, deck.getName());
                deck.show();
                i++;
            }
        } else {
            System.out.printf("1 : %s :\n", Account.getMainAccount().getMainDeck().getName());
            Account.getMainAccount().getMainDeck().show();
            int i = 1;
            for (Deck deck : Account.getMainAccount().getDecks()) {
                if (!deck.getName().equals(Account.getMainAccount().getMainDeck().getName())) {
                    System.out.printf("%d : %s :\n", i + 1, deck.getName());
                    deck.show();
                    i++;
                }
            }
        }
        if (Account.getMainAccount().getDecks().size() == 0) {
            System.out.println("There are no decks to show!");
        }
    }

    public void showDeck(String deckName) {
        if (Account.getMainAccount().findDeck(deckName) == null) {
            System.out.println("There is no such deck!");
        } else {
            Account.getMainAccount().findDeck(deckName).show();
        }
    }
}
