package Controller;

import Moudle.Account;
import Moudle.Battle;
import Moudle.Shop;

import java.util.Scanner;

public class Controller {
    Scanner scanner = new Scanner(System.in);
    String input;
    String region;
    String type;

    public void input() {
        input = scanner.nextLine();
        ControlBox controlBox;
        if (region.equals("Battle")) {
            if (isValidGameInfo(input)) {
                type = "game info";
            }
            Battle.input(new ControlBox(region, type));
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
            controlBox = new ControlBox("Account", "createAccount");
            if (isValidCreateAccount(input)) {
                if (isValidPasswordLogin(input)) {
                    String userName = null;
                    controlBox.setUserName(userName);
                }
            }
            Account.input(controlBox);
            controlBox = new ControlBox("Account", "login");
            if (isValidLogin(input)) {
                String userName = null;
                controlBox.setUserName(userName);
            }
            Account.input(controlBox);
        }
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
        return input.matches("search+ +[a-zA-Z]");
    }
    private boolean isValidGameInfo(String input) {
        return input.toLowerCase().equals("game info");
    }

}
