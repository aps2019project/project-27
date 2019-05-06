package Controller;

import Moudle.Account;
import Moudle.Battle;
import Moudle.Shop;

import java.util.Scanner;

public class Controller {
    private final Scanner scanner = new Scanner(System.in);
    private String input;
    private String region;
    private String type;

    public int input() {
        input = scanner.nextLine();
        ControlBox controlBox = new ControlBox("Battle", type);
        if (region.equals("Battle")) {
            int whereWeAre = 0;
            if (isValidGameInfo(input)) {
                type = "game info";
                whereWeAre = 1;
            }
            if (isValidNextCard(input)) {
                type = "next card";
                whereWeAre = 2;
            }
            Battle.input(new ControlBox(region, type));
            if (isValidShowMyMinion(input)) {
                type = "show my minions";
                whereWeAre = 3;
            }
            if (isValidShowOpMinion(input)) {
                type = "show opponent minions";
                whereWeAre = 4;
            }
            if (isValidShowCardInfo(input)) {
                String[] tmp = input.split(" ");
                type = "show card";
                controlBox.setCardID(tmp[3]);
                whereWeAre = 5;
            }
            if (isValidSelectCard(input)) {
                type = "select card";
                String[] tmp = input.toLowerCase().split(" ");
                controlBox.setCardID(tmp[2]);
                whereWeAre = 6;
            }
            if (isValidMove(input)) {
                type = "move";
                String[] tmp = input.split(" ");
                setLocation((ControlBox) controlBox, tmp[2]);
                whereWeAre = 7;
            }
            if (isValidAttack(input)) {
                type = "attack";
                controlBox.setCardID(input.toLowerCase().split(" ")[1]);
                whereWeAre = 8;
            }
            if (useSpecialPower(input)) {
                type = "use special power";
                String location = input.toLowerCase().split(" ")[3];
                setLocation((ControlBox) controlBox, location);
                whereWeAre = 9;
            }
            if (isValidInsert(input)) {
                type = "insert";
                String[] tmp = input.toLowerCase().split(" ");
                setLocation(controlBox, tmp[3]);
                whereWeAre = 10;
            }
            if (isValidShowHand(input)) {
                type = "show hand";
                whereWeAre = 11;
            }
            if (isValidEndTur(input)) {
                type = "end turn";
                whereWeAre = 12;
            }
            //todo item
            controlBox.setType(type);
            Battle.input(controlBox);
            return whereWeAre;
        }
        if (region.equals("MainMenu")) {

        }
        if (region.equals("Shop")) {
            controlBox = new ControlBox("Shop", "search");
            if (isValidSearchShop(input)) {
                String cardName = null;
                controlBox.setCardName(cardName);
            }
            Shop.input(controlBox);
        }
        if (region.equals("Collection")) {
            controlBox = new ControlBox("Collection", "s");
        }
        if (region.equals("Account")) {
            int whereWeAre = 0;
            controlBox = new ControlBox("Account", type);
            if (isValidCreateAccount(input)) {
                type = "create account";
                controlBox.setUserName(input.split(" ")[2]);
                whereWeAre = 1;
            }
            if (isValidLogin(input)) {
                type = "login";
//                String userName = null;
                controlBox.setUserName(input.split(" ")[1]);
                whereWeAre = 2;
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
                type = "logout";
            }
            controlBox.setType(type);
            Account.input(controlBox);
            return whereWeAre;
        }
        return 0;
    }

    private void setLocation(ControlBox controlBox, String location) {
        controlBox.setX(Integer.parseInt(String.valueOf(location.charAt(1))));
        controlBox.setY(Integer.parseInt(String.valueOf(location.charAt(3))));
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
        return input.matches("create account+ +[a-zA-Z0-9_.]+");
    }

    private boolean isValidSearchShop(String input) {
        return input.matches("search+ +[a-zA-Z]");
    }

    private boolean isValidShowCardInfo(String input) {
        return input.toLowerCase().matches("show+ +card+ +info+ +[\\w_]+");
    }

    private boolean isValidGameInfo(String input) {
        return input.toLowerCase().equals("game info");
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
}
