package View;

import Moudle.*;

public class View {
    public static void showBattle(Battle battle) {
        Cell[][] map = new Cell[5][9];
        Fighter[][] cardOnCell = new Fighter[5][9];
        Item[][] itemOnCell = new Item[5][9];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                if (map[i][j].getCardOnCell() != null) {
                    cardOnCell[i][j] = (Fighter) map[i][j].getCardOnCell();
                } else if (map[i][j].getItemOnCell() != null) {
                    itemOnCell[i][j] = map[i][j].getItemOnCell();
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                if (cardOnCell[i][j] == null && itemOnCell[i][j] == null) {
                    continue;
                } else if (cardOnCell[i][j] != null) {
                    if (cardOnCell[i][j].isHero() == true) {
                        System.out.print(cardOnCell[i][j].getPlayer() + "|" + "Hero" + "|" + cardOnCell[i][j].getAP() + "|" + cardOnCell[i][j].getHP() + "|" + cardOnCell[i][j].getID());
                    } else {
                        System.out.print(cardOnCell[i][j].getPlayer() + "|" + "Minion" + "|" + cardOnCell[i][j].getAP() + "|" + cardOnCell[i][j].getHP() + "|" + cardOnCell[i][j].getID());
                    }
                } else if (itemOnCell[i][j] != null) {
                    if(itemOnCell[i][j].isFlag() == true) {
                        System.out.print(itemOnCell[i][j].getName() + "| flag");
                    } else {
                        System.out.print(itemOnCell[i][j].getName());
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Player player1 = new Player();
        Player player2 = new Player();
        Battle battle = new Battle(player1, player2,1 );
        showBattle(battle);
    }
}
