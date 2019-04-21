import java.util.ArrayList;
import java.lang.Math;

public class Ground {
    private Cell[][] cells = new Cell[5][9];
    public int getDistance ( int x1, int y1, int x2, int y2) {
        int distance =(int)Math.sqrt( Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
        return distance;
    }
}

