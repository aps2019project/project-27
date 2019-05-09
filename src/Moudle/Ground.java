package Moudle;

import java.lang.Math;

public class Ground {
    private Cell[][] cells = new Cell[5][9];
    public Ground(){
        for ( int i=0;i<5;i++ ){
            for ( int j=0;j<9;j++ ){
                cells[i][j] = new Cell ();
            }
        }
    }

    public Cell[][] getCells() {
        return cells;
    }

    public static int getDistance (int x1, int y1, int x2, int y2) {
        return Math.abs ( x1-x2 )+Math.abs ( y1-y2 );
    }
    public Cell getCell(int x,int y){
        return cells[x][y];
    }
}

