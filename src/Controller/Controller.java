package Controller;

import Moudle.Account;
import Moudle.Battle;
import Moudle.Shop;

import java.util.Scanner;

public class Controller {
	private final Scanner scanner = new Scanner ( System.in );
	private String input;
	private String region;
	private String type;

	public void input () {
		input = scanner.nextLine ( );
		ControlBox controlBox = new ControlBox ( "Battle" , type );
		if ( region.equals ( "Battle" ) ) {
			if ( isValidGameInfo ( input ) ) {
				type = "game info";
			}
			Battle.input ( new ControlBox ( region , type ) );
			if ( isValidShowMyMinion ( input ) ) {
				type = "show my minions";
			}
			if ( isValidShowOpMinion ( input ) ) {
				type = "show opponent minions";
			}
			if ( isValidShowCardInfo ( input ) ) {
				String[] tmp = input.split ( " " );
				type = "show card";
				controlBox.setCardID ( tmp[ 3 ] );
			}
			if ( isValidSelectCard ( input ) ) {
				type = "select card";
				String[] tmp = input.toLowerCase ( ).split ( " " );
				controlBox.setCardID ( tmp[ 2 ] );
			}
			if ( isValidMove ( input ) ) {
				type = "move";
				String[] tmp = input.split ( " " );
				setLocation ( ( ControlBox ) controlBox , tmp[2] );
			}
			if ( isValidAttack ( input ) ){
				type = "attack";
				controlBox.setCardID ( input.toLowerCase ().split ( " " )[1] );
			}
			if ( useSpecialPower ( input ) ){
				type = "use special power";
				String location = input.toLowerCase ().split ( " " )[3];
				setLocation ( ( ControlBox ) controlBox , location );
			}
			if ( isValidInsert ( input ) ){
				type = "insert";
				String[] tmp = input.toLowerCase ().split ( " " );
				setLocation ( controlBox,tmp[3] );

			}
			if (isValidShowHand ( input ) ){
				type ="show hand";
			}
			if ( isValidEndTur ( input ) ){
				type = "end turn";
			}
			controlBox.setType ( type );
			Battle.input ( controlBox );
		}
		if ( region.equals ( "MainMenu" ) ) {

		}
		if ( region.equals ( "Shop" ) ) {
			controlBox = new ControlBox ( "Shop" , "search" );
			if ( isValidSearchShop ( input ) ) {
				String cardName = null;
				controlBox.setCardName ( cardName );
			}
			Shop.input ( controlBox );
		}
		if ( region.equals ( "Collection" ) ) {
			controlBox = new ControlBox ( "Collection" , "s" );
		}
		if ( region.equals ( "Account" ) ) {
			controlBox = new ControlBox ( "Account" , "createAccount" );
			if ( isValidCreateAccount ( input ) ) {
				if ( isValidPasswordLogin ( input ) ) {
					String userName = null;
					controlBox.setUserName ( userName );
				}
			}
			Account.input ( controlBox );
			controlBox = new ControlBox ( "Account" , "login" );
			if ( isValidLogin ( input ) ) {
				String userName = null;
				controlBox.setUserName ( userName );
			}
			Account.input ( controlBox );
		}
	}

	private void setLocation ( ControlBox controlBox , String location ) {
		controlBox.setX ( Integer.parseInt ( String.valueOf ( location.charAt ( 1 ) ) ) );
		controlBox.setY ( Integer.parseInt ( String.valueOf ( location.charAt ( 3 ) ) ) );
	}

	private boolean isValidSelectCard ( String input ) {
		return input.toLowerCase ( ).matches ( "select+ +card+ +[\\w_]+" );
	}
	private boolean isValidShowHand(String input){
		return input.toLowerCase ().equals ( "show hand" );
	}
	private boolean isValidShowOpMinion ( String input ) {
		return input.toLowerCase ( ).equals ( "show my opponenet minions" );
	}

	private boolean isValidShowMyMinion ( String input ) {
		return input.toLowerCase ( ).equals ( "show my minions" );
	}

	private boolean isValidPasswordLogin ( String input ) {
		return input.matches ( "[a-zA-Z0-9]" );
	}

	private boolean isValidLogin ( String input ) {
		return input.matches ( "login+ +[a-zA-Z0-9]+" );
	}

	private boolean isValidCreateAccount ( String input ) {
		return input.matches ( "create account+ +[a-zA-Z0-9]+" );
	}

	private boolean isValidSearchShop ( String input ) {
		return input.matches ( "search+ +[a-zA-Z]" );
	}

	private boolean isValidShowCardInfo ( String input ) {
		return input.toLowerCase ( ).matches ( "show+ +card+ +info+ +[\\w_]+" );
	}

	private boolean isValidGameInfo ( String input ) {
		return input.toLowerCase ( ).equals ( "game info" );
	}

	private boolean isValidMove ( String input ) {
		return input.toLowerCase ( ).matches ( "move+ +to+ +[(]\\d,\\d[)]" );
	}
	private boolean isValidAttack(String input){
		return input.toLowerCase ().matches ( "attack+ +[a-zA-Z0-9]+" );
	}
	private boolean useSpecialPower(String input){
		return input.toLowerCase ().matches ( "Use+ +special+ +power+ +[(]\\d,\\d[)]");
	}
	private boolean isValidInsert(String input){
		return input.toLowerCase ().matches ( "insert+ +[a-z]+ +in+ +[(]\\d,\\d[)]" );
	}
	private boolean isValidEndTur(String input){
		return input.equalsIgnoreCase ( "end turn" );
	}
}
