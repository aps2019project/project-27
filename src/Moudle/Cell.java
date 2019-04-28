package Moudle;

import java.util.ArrayList;

public class Cell {
	private Object cardOnCell=null;
	private Item itemOnCell;
	public ArrayList<Buff> cellEffect = new ArrayList<>();

	public Object getCardOnCell () {
		return cardOnCell;
	}

	public void moveFromCell() {

	}
	public void moveInCell(Object object) {
	}
}