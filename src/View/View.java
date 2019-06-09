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
                    if (cardOnCell[i][j].isHero()) {
                        System.out.print(cardOnCell[i][j].getPlayer() + "|" + "Hero" + "|" + cardOnCell[i][j].getAP() + "|" + cardOnCell[i][j].getHP() + "|" + cardOnCell[i][j].getID());
                    } else {
                        System.out.print(cardOnCell[i][j].getPlayer() + "|" + "Minion" + "|" + cardOnCell[i][j].getAP() + "|" + cardOnCell[i][j].getHP() + "|" + cardOnCell[i][j].getID());
                    }
                } else if (itemOnCell[i][j] != null) {
                    if (itemOnCell[i][j].isFlag()) {
                        System.out.print(itemOnCell[i][j].getName() + "| flag");
                    } else {
                        System.out.print(itemOnCell[i][j].getName());
                    }
                }
            }
        }
    }

    public static void showSpell(Spell spell) {
        System.out.printf("Name : %s - MP : %d - Desc : \n", spell.getName(), spell.getManaPrice());

    }
    public static void showSpell(Spell spell,int counter) {
        System.out.printf("Name : %s - MP : %d - Desc : \n", spell.getName(), spell.getManaPrice());

    }
    public static void printMinion ( MinionAndHero minionAndHero , int counter ) {
        System.out.printf ( "\t%d : Type:Minion - Name : %s - Class:%s - AP:%d - HP:%d - MP:%d - Special power:%s - Sell Cost:%d\n" ,
                counter + 1 , minionAndHero.getName ( ) , minionAndHero.getAttackType ( ) , minionAndHero.getAP ( ) ,
                minionAndHero.getHP ( ) , minionAndHero.getManaPrice ( ) , minionAndHero.getSpecialPowerType ( ) ,
                minionAndHero.getShopPrice ( ) );
    }
    public static void printMinion ( MinionAndHero minionAndHero  ) {
        System.out.printf ( "\tType:Minion - Name : %s - Class:%s - AP:%d - HP:%d - MP:%d - Special power:%s - Sell Cost:%d\n" ,
                  minionAndHero.getName ( ) , minionAndHero.getAttackType ( ) , minionAndHero.getAP ( ) ,
                minionAndHero.getHP ( ) , minionAndHero.getManaPrice ( ) , minionAndHero.getSpecialPowerType ( ) ,
                minionAndHero.getShopPrice ( ) );
    }
    public static void showFighter ( Fighter fighter) {
        System.out.printf("%s : health : %d - attack : %d - attack type : %d - range : %d - location : x=%d y=%d\n",
                fighter.getID(), fighter.getHP(), fighter.getAP(),fighter.getAttackType (),fighter.getRange (), fighter.getX(), fighter.getY());
    }
}
