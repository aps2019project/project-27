import java.util.ArrayList;
import java.lang.Math;

public class Ground {
    private Cell[][] cells = new Cell[5][9];



    public int getdistance(int x1, int y1, int x2, int y2) {
        int distance =(int)Math.sqrt( Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
        return distance;
    }
}
class Cell {
    private Object cardOnCell;
    private Item itemOnCell;
    private ArrayList<String> cellEffect = new ArrayList<>();



    public void moveFromCell() {

    }
    public void moveInCell(Object object) {

    }
    public ArrayList<String> getCellEffect(Cell cell) {
        // the output of this method was void in UML but it sounds like a mistake to me, so I changed it.
        return this.cellEffect;
    }
}
