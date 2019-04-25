package Controller;

import Moudle.Battle;

import java.util.Scanner;

public class ControllBox {
	private final Scanner scanner = new Scanner ( System.in );
	private String input;
	private String type;
	private String region;
	public String getRegion () {
		return region;
	}
	public void setRegion ( String region ) {
		this.region = region;
	}
	public String getType () {
		return type;
	}
	public void input() {
		input = scanner.nextLine ( );
		if ( region.equals ( "Battle" ) ) {
			if ( isValidGameInfo ( input ) ) {
				type = "game info";
			}
			Battle.input ( this );
		}
		if ( region.equals ( "MainMenu" ) ){

		}
	}
	private boolean isValidGameInfo(String input){
		return input.toLowerCase ().equals ( "game info" );
	}
}
