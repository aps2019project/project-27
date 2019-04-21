import java.util.ArrayList;

public class Cell {
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