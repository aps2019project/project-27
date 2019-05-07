package Moudle;

import java.util.ArrayList;

public class Cell {
    private Object cardOnCell = null;
    private Item itemOnCell = null;
    public ArrayList<Buff> cellEffect = new ArrayList<>();

    public Object getCardOnCell() {
        return cardOnCell;
    }

    public Item getItemOnCell() {
        return itemOnCell;
    }

    public void addCellEffect(Buff buff) {
        cellEffect.add(buff);
    }

    public void removeCellEffect(Buff buff) {
        cellEffect.remove(buff);
    }

    public void moveFromCell() {

    }
    public void setItemOnCell(Item itemOnCell){
        this.itemOnCell = itemOnCell;
    }
    public void removeItem(){
        itemOnCell = null;
    }
    public void moveInCell(Object object) {
    }
}