package Moudle;

public class Fighter extends Card {
	private int AP;
	private int HP;
	private int x;
	private int y;
	private Cell currentCell;
	private Player player;
	protected Fighter ( String name , int cardID , int shopPrice , int manaPrice ) {
		super ( name , cardID , shopPrice , manaPrice );

	}
}
