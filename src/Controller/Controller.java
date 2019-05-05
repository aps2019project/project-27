package Controller;

import Moudle.Account;
import Moudle.Battle;
import Moudle.Shop;

import java.util.Scanner;

public class Controller {
	Scanner scanner = new Scanner ( System.in );
	String input;
	String region;
	String type;

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
				controlBox.setX ( Integer.parseInt ( String.valueOf ( tmp[2].charAt ( 1 ) ) ) );
				controlBox.setY ( Integer.parseInt ( String.valueOf ( tmp[2].charAt ( 3 ) ) ) );
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

	private boolean isValidSelectCard ( String input ) {
		return input.toLowerCase ( ).matches ( "select+ +card+ +[\\w_]+" );
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
}
