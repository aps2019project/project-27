package Moudle;
import Controller.ControllBox;

import java.util.ArrayList;
public class Battle {
	private static Battle currentBattle;
	private Ground ground;
	private int currentTurn;
	private Player player1;
	private Player player2;
	private Player playerInTurn;
	private Card selectedCard;
	private Item selectedItem;
	private int battleType;
	private ArrayList<Item> flags;
	private int numberOfFlags;
	private Item mainFlag;
	private Fighter heroP1;
	private Fighter heroP2;
	public static void input(ControllBox controllBox){
		if ( controllBox.getType ().equals ( "game info" ) ){
			currentBattle.showInfo ();
			return;
		}
	}
	public void checkWinner(){}
	public void setMana ( ){}
	public Battle ( Player player1, Player player2, int battleType){

	}
	public void nextTurn(){
		currentTurn++;
		setMana ();
		setPlayerInTurn ();
	}
	public void showInfo(){

	}
	private void setPlayerInTurn (){
		if ( playerInTurn.equals ( player1 ) )
			playerInTurn = player2;
		playerInTurn = player1;
	}
}
