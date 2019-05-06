package Moudle;

import java.util.Scanner;

import Moudle.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int location = 0; //0: account, 1:main menu, 2:collection, 3:shop, 4:battle, -1:exit the game
        Account account = new Account();
        Collection collection = new Collection();
        while (location != -1) {
            while (location == 0) {
                String input = scanner.nextLine();
                input.toLowerCase();
                if (input.contains("create account")) {
                    Account.createAccount(input.split(" ")[2]);
                }
                if (input.contains("login")) {
                    if (account.login(input.split(" ")[1]) == true) {
                        location = 1;
                    }
                }
                if (input.contains("show")) {
                    Account.showLeaderBoard();
                }
                if (input.contains("save")) {
                    Account.save();
                }
                if (input.contains("logout")) {
                    account.logout();
                }
                if (input.contains("help")) {
                    account.help();
                }
            }
            while (location == 1) {
                System.out.println("1.Collection");
                System.out.println("2.Shop");
                System.out.println("3.Battle");
                System.out.println("4.Exit");
                System.out.println("5.Help");
                String input = scanner.nextLine();
                input.toLowerCase();
                if (input.contains("enter")) {
                    if (input.contains("collection")) {
                        location = 2;
                    }
                    if (input.contains("shop")) {
                        location = 3;
                    }
                    if (input.contains("battle")) {
                        location = 4;
                    }
                    if (input.contains("exit")) {
                        location = -1;
                    }
                    if (input.contains("help")) {
                        System.out.println("1.Collection");
                        System.out.println("2.Shop");
                        System.out.println("3.Battle");
                        System.out.println("4.Exit");
                        System.out.println("5.Help");
                    }
                }
            }
            while (location == 2) {
                String input = scanner.nextLine();
                if (input.contains("exit")) {
                    location = 1;
                } else if (input.contains("show")) {
                    Collection.show();
                } else if (input.contains("search")) {
                    Collection.search(input.split(" ")[1]);
                } else if (input.contains("save")) {
                    Collection.save();
                } else if (input.contains("create")) {
                    collection.createDeck(input.split(" ")[2]);
                } else if (input.contains("delete")) {
                    collection.deleteDeck(input.split(" ")[2]);
                } else if (input.contains("add")) {
                    collection.add(input.split(" ")[1], input.split(" ")[4]);
                } else if (input.contains("remove")) {
                    collection.remove(input.split(" ")[1], input.split(" ")[4]);
                } else if (input.contains("validate")) {
                    collection.validateDeck(input.split(" ")[2]);
                } else if (input.contains("select")) {
                    collection.selectDeck(input.split(" ")[2]);
                } else if (input.contains("show all")) {
                    collection.showAllDecks();
                } else if (input.contains("show deck")) {
                    collection.showDeck(input.split(" ")[2]);
                } else if (input.contains("help")) {
                    Collection.help();
                }
            }
            while (location == 3) {

            }
            while (location == 4) {

            }
        }
    }
}
