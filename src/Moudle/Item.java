package Moudle;

import java.util.ArrayList;

public class Item {
    private static ArrayList<Item> items;
    private static Item lastFlag;
    private static Item normalFlag;
    private String name;
    private int price;
    private boolean isFlag;
    private boolean isLastFlag;
    private Target target;
    private ArrayList<Buff> buffs;
    private boolean isCollectible;

    public boolean isLastFlag () {
        return isLastFlag;
    }
    public static Item getLastFlag () {
        return lastFlag;
    }

    public static Item getNormalFlag () {
        return normalFlag;
    }

    public Target getTarget () {
        return target;
    }
    public boolean isCollectible() {
        return isCollectible;
    }
    public ArrayList<Buff> getBuffs() {
        return buffs;
    }
    public static Item findItem(String name) {
        for (Item item : items) {
            if (item.name.equals(name))
                return item;
        }
        return null;
    }

    public boolean isFlag() {
        return isFlag;
    }

    public int getPrice() {
        return price;
    }

    public static Item findItem(String name, ArrayList<Item> items) {
        for (Item item : items) {
            if (item.name.equals(name))
                return item;
        }
        return null;
    }
    public void showItem(){
        //todo
    }
    public static void setItems(ArrayList<Item> items) {
        Item.items = items;
    }

    public String getName() {
        return name;
    }
}
