package Server.Moudle;

import java.util.ArrayList;

public class Item {
    private static ArrayList<Item> items = new ArrayList<>();
    int a = 1;
    private String image;
    private static Item lastFlag = new Item();

    static {
        lastFlag.isLastFlag = true;
        lastFlag.isFlag = false;
        lastFlag.name = "last flag";
        lastFlag.image = "flag.gif";
    }

    private static Item normalFlag = new Item();

    static {
        normalFlag.isLastFlag = false;
        normalFlag.isFlag = true;
        normalFlag.name = "normal flag";
        normalFlag.image = "flag.gif";

    }

    private String name;
    private int price;
    private boolean isFlag;
    private boolean isLastFlag;
    private Target target;
    private ArrayList<Buff> buffs;
    private boolean isCollectible;
    private String description;

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public static ArrayList<Item> getItems() {
        return items;
    }

    public boolean isLastFlag() {
        return isLastFlag;
    }

    public static Item getLastFlag() {
        return lastFlag;
    }

    public static Item getNormalFlag() {
        return normalFlag;
    }

    public Target getTarget() {
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

    public void showItem() {
    }

    public static void setItems(ArrayList<Item> items) {
        Item.items = items;
    }

    public String getName() {
        return name;
    }
}
