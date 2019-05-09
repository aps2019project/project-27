package Controller;

import Moudle.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
    private static String input;
    private static String region;
    private static String type;
    private final Scanner scanner = new Scanner(System.in);

    private Controller(String region) {
        this.region = region;
    }

    public static void setRegion(String region) {
        Controller.region = region;
    }

    public static void main(String[] args) throws FileNotFoundException {
        //load phase
        //Load.loadAccounts();
        Load.loadMinionAndHeros();
        Load.loadSpells ();
        Card.addMAndH(MinionAndHero.getMinionAndHeroes());
        Card.addSpells ( Spell.getSpells () );
        Controller controller = new Controller("Account");
        int out = 0;
        ArrayList<MinionAndHero> minionAndHeroes = MinionAndHero.getMinionAndHeroes();
        ArrayList<Account> accounts = Account.getAccounts();
        ArrayList<Card> cards = Card.getCards();
        while (out != -1) {
            int a = 1;
            out = controller.input();
        }
    }

    public int input() {
        input = scanner.nextLine();
        ControlBox controlBox = new ControlBox("Battle", type);
        type = "empty";
        if (region.equals("Battle")) {
            if (isValidSelectUser(input)) {
                type = "select user";
                controlBox.setUserName(input.split(" ")[2]);
            }
            if (isValidGameInfo(input)) {
                type = "game info";
            }
            if (isValidNewGame(input)) {
                type = "new mp";
                controlBox.setBattleType(Integer.parseInt(input.split(" ")[3]));
                controlBox.setNumberOfFlags(Integer.parseInt(input.split(" ")[4]));
            }
            if (isValidShowItems(input)) {
                type = "show items";
            }
            if (isValidShowItemInfo(input)) {
                type = "show item";
                controlBox.setCardName(input.split(" ")[2]);
            }
            if (isValidUseItem(input)) {
                type = "use item";
                controlBox.setCardName(input.split(" ")[1]);
                setLocation(controlBox, input.split(" ")[2]);
            }
            if (isValidNextCard(input)) {
                type = "next card";
            }
            Battle.input(new ControlBox(region, type));
            if (isValidShowMyMinion(input)) {
                type = "show my minions";
            }
            if (isValidShowOpMinion(input)) {
                type = "show opponent minions";
            }
            if (isValidShowCardInfo(input)) {
                String[] tmp = input.split(" ");
                type = "show card";
                controlBox.setCardID(tmp[3]);
            }
            if (isValidSelectCard(input)) {
                type = "select card";
                String[] tmp = input.toLowerCase().split(" ");
                controlBox.setCardID(tmp[2]);
            }
            if (isValidMove(input)) {
                type = "move";
                String[] tmp = input.split(" ");
                setLocation((ControlBox) controlBox, tmp[2]);
            }
            if (isValidAttack(input)) {
                type = "attack";
                controlBox.setCardID(input.toLowerCase().split(" ")[1]);
            }
            if (useSpecialPower(input)) {
                type = "use special power";
                String location = input.toLowerCase().split(" ")[3];
                setLocation((ControlBox) controlBox, location);
            }
            if (isValidInsert(input)) {
                type = "insert";
                String[] tmp = input.toLowerCase().split(" ");
                setLocation(controlBox, tmp[3]);
            }
            if (isValidShowHand(input)) {
                type = "show hand";
            }
            if (isValidEndTur(input)) {
                type = "end turn";
            }
            //todo item
            controlBox.setType(type);
            Battle.input(controlBox);
            return 0;
        }
        if (region.equals("MainMenu")) {
            if (input.equalsIgnoreCase("Enter Account")){
                region = "Account";
                System.out.println("You entered account!");
            }
            if (input.equalsIgnoreCase("Enter shop")) {
                region = "Shop";
                System.out.println("You entered shop!");
            }
            if (input.equalsIgnoreCase("Enter collection")) {
                region = "Collection";
                System.out.println("You entered collection!");
            }
            if (input.equalsIgnoreCase("Enter battle")) {
                region = "Battle";
                System.out.println("You entered battle!");
            }
            if (input.equalsIgnoreCase("Enter exit")) {
                return -1;
            }
            if (input.equalsIgnoreCase("Enter help")) {
                System.out.println("1.Collection");
                System.out.println("2.Shop");
                System.out.println("3.Battle");
                System.out.println("4.Exit");
                System.out.println("5.Help");
            }
        }
        if (region.equals("Shop")) {
            controlBox = new ControlBox("Shop", type);
            if (input.equalsIgnoreCase("exit")) {
                region = "MainMenu";
                System.out.println("1.Collection");
                System.out.println("2.Shop");
                System.out.println("3.Battle");
                System.out.println("4.Exit");
                System.out.println("5.Account");
                System.out.println("6.Help");
            }
            if (input.equalsIgnoreCase("show collection")) {
                type = "showCollection";
            }
            if (isValidSearchShop(input)) {
                type = "search";
                controlBox.setCardName(input.split(" ")[1]);
            }
            if (isValidSearchShopCollection(input)) {
                type = "searchCollection";
                controlBox.setCardName(input.split(" ")[2]);
            }
            if (isValidBuy(input)) {
                type = "buy";
                controlBox.setCardName(input.split(" ")[1]);
            }
            if (isValidSell(input)) {
                type = "sell";
                controlBox.setCardName(input.split(" ")[1]);
            }
            if (input.equalsIgnoreCase("show")) {
                type = "show";
            }
            if (input.equalsIgnoreCase("help")) {
                type = "help";
            }
            controlBox.setType(type);
            Shop.input(controlBox);
        }
        if (region.equals("Collection")) {
            controlBox = new ControlBox("Collection", type);
            if (input.equals("exit")) {
                region = "MainMenu";
                System.out.println("1.Collection");
                System.out.println("2.Shop");
                System.out.println("3.Battle");
                System.out.println("4.Exit");
                System.out.println("5.Account");
                System.out.println("6.Help");
            }
            if (input.equals("show")) {
                type = "show";
            }
            if (isValidSearchCollection(input)) {
                type = "searchCollection";
                controlBox.setCardName(input.split(" ")[1]);
            }
            if (input.equals("save")) {
                type = "save";
            }
            if (isValidCreateDeck(input)) {
                type = "createDeck";
                controlBox.setDeckName(input.split(" ")[2]);
            }
            if (isValidDeleteDeck(input)) {
                type = "deleteDeck";
                controlBox.setDeckName(input.split(" ")[2]);
            }
            if (isValidAdd(input)) {
                type = "add";
                controlBox.setCardName(input.split(" ")[1]);
                controlBox.setDeckName(input.split(" ")[4]);
            }
            if (isValidRemove(input)) {
                type = "remove";
                controlBox.setCardName(input.split(" ")[1]);
                controlBox.setDeckName(input.split(" ")[4]);
            }
            if (isValidValidateDeck(input)) {
                type = "validateDeck";
                controlBox.setDeckName(input.split(" ")[2]);
            }
            if (isValidSelectDeck(input)) {
                type = "selectDeck";
                controlBox.setDeckName(input.split(" ")[2]);
            }
            if (input.equals("show all decks")) {
                type = "showAllDecks";
            }
            if (isValidShowDeck(input)) {
                type = "showDeck";
                controlBox.setDeckName(input.split(" ")[2]);
            }
            if (input.equals("help")) {
                type = "help";
            }
            controlBox.setType(type);
            Collection.input(controlBox);
        }
        if (region.equals("Account")) {
            controlBox = new ControlBox("Account", type);
            if (isValidCreateAccount(input)) {
                type = "create account";
                controlBox.setUserName(input.split(" ")[2]);
            }
            if (isValidLogin(input)) {
                type = "login";
                controlBox.setUserName(input.split(" ")[1]);
            }
            if (input.equals("show leaderboard")) {
                type = "show leaderboard";
            }
            if (input.equals("save")) {
                type = "save";
            }
            if (input.equals("logout")) {
                type = "logout";
            }
            if (input.equals("help")) {
                type = "help";
            }
            controlBox.setType(type);
            int o = Account.input(controlBox);
            if (o == 2) {
                region = "MainMenu";
            }
            return o;
        }
        return 0;
    }


    private void setLocation(ControlBox controlBox, String location) {
        controlBox.setX(Integer.parseInt(String.valueOf(location.charAt(1))));
        controlBox.setY(Integer.parseInt(String.valueOf(location.charAt(3))));
    }

    private boolean isValidSearchShopCollection(String input) {
        return input.toLowerCase().matches("search+ +collection+ +[a-z0-9_.]+");
    }

    private boolean isValidBuy(String input) {
        return input.toLowerCase().matches("buy+ +[a-z0-9_.]+");
    }

    private boolean isValidSell(String input) {
        return input.toLowerCase().matches("sell+ +[a-z0-9_.]+");
    }

    private boolean isValidSearchCollection(String input) {
        return input.toLowerCase().matches("search+ +[a-z0-9]+");
    }

    private boolean isValidCreateDeck(String input) {
        return input.toLowerCase().matches("create+ +deck+ +[a-z0-9]+");
    }

    private boolean isValidDeleteDeck(String input) {
        return input.toLowerCase().matches("delete+ +deck+ +[a-z0-9]+");
    }

    private boolean isValidAdd(String input) {
        return input.toLowerCase().matches("add+ +[a-z0-9_.]+ +to+ +deck+ +[a-z0-9_.]+");
    }

    private boolean isValidRemove(String input) {
        return input.toLowerCase().matches("remove+ +[a-z0-9_.]+ +from+ +deck+ +[a-z0-9_.]+");
    }

    private boolean isValidValidateDeck(String input) {
        return input.toLowerCase().matches("validate+ +deck+ +[a-z0-9_.]+");
    }

    private boolean isValidSelectDeck(String input) {
        return input.toLowerCase().matches("select+ +deck+ +[a-z0-9_.]+");
    }

    private boolean isValidShowDeck(String input) {
        return input.toLowerCase().matches("show+ +deck+ +[a-z0-9_.]+");
    }

    private boolean isValidSelectCard(String input) {
        return input.toLowerCase().matches("select+ +card+ +[\\w_]+");
    }

    private boolean isValidShowHand(String input) {
        return input.toLowerCase().equals("show hand");
    }

    private boolean isValidShowOpMinion(String input) {
        return input.toLowerCase().equals("show my opponenet minions");
    }

    private boolean isValidShowMyMinion(String input) {
        return input.toLowerCase().equals("show my minions");
    }

    private boolean isValidPasswordLogin(String input) {
        return input.matches("[a-zA-Z0-9]");
    }

    private boolean isValidLogin(String input) {
        return input.matches("login+ +[a-zA-Z0-9]+");
    }

    private boolean isValidCreateAccount(String input) {
        return input.matches("create account+ +[a-zA-Z0-9]+");
    }

    private boolean isValidSearchShop(String input) {
        return input.toLowerCase().matches("search+ +[a-z0-9]+");
    }

    private boolean isValidShowCardInfo(String input) {
        return input.toLowerCase().matches("show+ +card+ +info+ +[\\w_]+");
    }

    private boolean isValidGameInfo(String input) {
        return input.equalsIgnoreCase("game info");
    }

    private boolean isValidMove(String input) {
        return input.toLowerCase().matches("move+ +to+ +[(]\\d,\\d[)]");
    }

    private boolean isValidAttack(String input) {
        return input.toLowerCase().matches("attack+ +[a-zA-Z0-9]+");
    }

    private boolean useSpecialPower(String input) {
        return input.toLowerCase().matches("Use+ +special+ +power+ +[(]\\d,\\d[)]");
    }

    private boolean isValidInsert(String input) {
        return input.toLowerCase().matches("insert+ +[a-z]+ +in+ +[(]\\d,\\d[)]");
    }

    private boolean isValidEndTur(String input) {
        return input.equalsIgnoreCase("end turn");
    }

    private boolean isValidNextCard(String input) {
        return input.equalsIgnoreCase("show next card");
    }

    private boolean isValidSelectUser(String input) {
        return input.toLowerCase().matches("select+ +user+ +[a-z0-9._]");
    }

    private boolean isValidNewGame(String input) {
        return input.matches("start+ +multiplayer+ +game+ +[0|1|2]+ *+\\d*+");
    }

    private boolean isValidShowItems(String input) {
        return input.toLowerCase().matches("show+ +items");
    }

    private boolean isValidShowItemInfo(String input) {
        return input.toLowerCase().matches("show+ +info+ +[a-z0-9]");
    }

    private boolean isValidUseItem(String input) {
        return input.toLowerCase().matches("use+ +item+ +[0-9a-z]+ +[(]\\d,\\d[)]");
    }
}
