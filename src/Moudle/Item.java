package Moudle;

import java.util.ArrayList;

public class Item {
    private static ArrayList<Item> items;
    private String name;
    private int price;
    private boolean isFlag;
    //todo isCollectible

    public static Item findItem(String name) {
        for (Item item : items) {
            if (item.name.equals(name))
                return item;
        }
        return null;
    }

    public boolean isFlag(){
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

    public static void setItems(ArrayList<Item> items) {
        Item.items = items;
    }

    public String getName() {
        return name;
    }
}
