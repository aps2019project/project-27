package Controller;

import Moudle.Battle;

import java.util.Scanner;

public class Controller {
Scanner scanner =  new Scanner(System.in);
String input;
String region;
String type;
    public void input() {
        input = scanner.nextLine ( );
        if ( region.equals ( "Battle" ) ) {
            if ( isValidGameInfo ( input ) ) {
                type = "game info";
            }
            Battle.input ( new ControllBox(region,type) );
        }
        if ( region.equals ( "MainMenu" ) ){

        }
    }
    private boolean isValidGameInfo(String input){
        return input.toLowerCase ().equals ( "game info" );
    }

}
