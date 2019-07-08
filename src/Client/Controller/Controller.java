package Client.Controller;


import Client.View.Graphic;
import ControlBox.ControlBox;
import com.gilecode.yagson.YaGson;
import com.gilecode.yagson.YaGsonBuilder;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.Formatter;
import java.util.Scanner;

public class Controller {
	private static YaGson yaGson;
    private static String input;
    private static String region = "";
    private static String type;
    private static final Scanner scanner = new Scanner(System.in);
    private static Socket socket;
    private static Scanner serveerInput;
    private static Formatter serverOutput;
    private Controller(String region) {
        Controller.region = region;
    }

    public static void setRegion(String region) {
        Controller.region = region;
    }
	static {
    	YaGsonBuilder yaGsonBuilder = new YaGsonBuilder ();
    	yaGson = yaGsonBuilder.create ();
	}
    public static void main(String[] args) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        File musicPath = new File("C:\\Users\\ASUS\\Music\\Heroic Demise (New).wav");
        AudioInputStream audioInput = null;
        audioInput = AudioSystem.getAudioInputStream(musicPath);
        Clip clip = AudioSystem.getClip();
        clip.open(audioInput);
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        //connect to server
        String IP = "localhost";
        //IP = scanner.nextLine();
        socket = new Socket ( IP,8888 );
        serveerInput = new Scanner ( socket.getInputStream () );
        serverOutput = new Formatter ( socket.getOutputStream () );
        Graphic.main ( new String[2] );
        region = "Account";
        int out = 0;
        while (out != -1) {
          //  out = Controller.input();
        }
    }

  /*  public static int input() {
        input = scanner.nextLine();
        ControlBox controlBox = new ControlBox("Battle", type);
        type = "empty";
        if (region.equals("Battle")) {
            if (isValidSelectUser(input)) {
                type = "select user";
                controlBox.setUserName(input.split(" ")[2]);
            }
            if ( input.equalsIgnoreCase ( "help" )){
                type = "help";
            }
            if ( input.equalsIgnoreCase ( "show graveyard" ) ){
                type = "graveyard";
            }
            if ( input.equalsIgnoreCase ( "exit" ) ){
                type = "exit";
                region = "MainMenu";
                printInMenu ();
                controlBox.setRegion ( "MainMenu" );
            }
            if (isValidGameInfo(input)) {
                type = "game info";
            }
            if (isValidNewGame(input)) {
                type = "new mp";
                if ( "flags".equals ( input.toLowerCase ( ).split ( " " )[ 3 ] ) ) {
                    controlBox.setBattleType ( 1 );
                } else if ( "hero".equals ( input.toLowerCase ( ).split ( " " )[ 3 ] ) ) {
                    controlBox.setBattleType ( 0 );
                } else if ( "oneflag".equals ( input.toLowerCase ( ).split ( " " )[ 3 ] ) ) {
                    controlBox.setBattleType ( 2 );
                }
                else type = "";
                if (input.split(" ")[3].equals ( "flags" ))
                controlBox.setNumberOfFlags(Integer.parseInt(input.split(" ")[4]));
            }
            if (isValidShowItems(input)) {
                type = "show items";
            }
            if (isValidShowItemInfo(input)) {
                type = "show item";
                controlBox.setCardName(input.split(" ")[2]);
            }
            if (isValidUseItem(input)) {
                type = "use item";
                controlBox.setCardName(input.split(" ")[2]);
                setLocation(controlBox, input.split(" ")[3]);
            }
            if (isValidNextCard(input)) {
                type = "next card";
            }
            if (isValidShowMyMinion(input)) {
                type = "show my minions";
            }
            if (isValidShowOpMinion(input)) {
                type = "show opponent minions";
            }
            if (isValidShowCardInfo(input)) {
                String[] tmp = input.split(" ");
                type = "show card";
                controlBox.setCardID(tmp[3]);
            }
            if (isValidSelectCard(input)) {
                type = "select card";
                String[] tmp = input.split(" ");
                controlBox.setCardID(tmp[2]);
            }
            if (isValidMove(input)) {
                type = "move";
                String[] tmp = input.split(" ");
                setLocation( controlBox , tmp[2]);
            }
            if (isValidAttack(input)) {
                type = "attack";
                controlBox.setCardID(input.split(" ")[1]);
            }
            if (useSpecialPower(input)) {
                type = "use special power";
                String location = input.toLowerCase().split(" ")[3];
                setLocation( controlBox , location);
            }
            if (isValidInsert(input)) {
                type = "insert";
                String[] tmp = input.split(" ");
                controlBox.setCardName ( tmp[1] );
                setLocation(controlBox, tmp[3]);
            }
            if (isValidShowHand(input)) {
                type = "show hand";
            }
            if (isValidEndTur(input)) {
                type = "end turn";
            }
            controlBox.setType(type);
            Battle.input(controlBox , this );
            return 0;
        }
        if (region.equals("MainMenu")) {
            if (input.equals("logout")) {
                type = "logout";
                region = "Account";
            }
            if (input.equalsIgnoreCase("Enter Account")) {
                region = "Account";
                System.out.println("You entered account!");
            }
            if (input.equalsIgnoreCase("Enter shop")) {
                region = "Shop";
                System.out.println("You entered shop!");
            }
            if (input.equalsIgnoreCase("Enter collection")) {
                region = "Collection";
                System.out.println("You entered collection!");
            }
            if (input.equalsIgnoreCase("Enter battle")) {
                region = "Battle";
                System.out.println("You entered battle!");
            }
            if (input.equalsIgnoreCase("Enter exit")) {
                return -1;
            }
            if (input.equalsIgnoreCase("Enter help")) {
                System.out.println("1.Collection");
                System.out.println("2.Shop");
                System.out.println("3.Battle");
                System.out.println("4.Exit");
                System.out.println("5.Help");
            }
        }
        if (region.equals("Shop")) {
            controlBox = new ControlBox("Shop", type);
            if (input.equalsIgnoreCase("exit")) {
                region = "MainMenu";
                printInMenu ( );
            }
            if (input.equalsIgnoreCase("show collection")) {
                type = "showCollection";
            }
            if (isValidSearchShop(input)) {
                type = "search";
                controlBox.setCardName(input.split(" ")[1]);
            }
            if (isValidSearchShopCollection(input)) {
                type = "searchCollection";
                controlBox.setCardName(input.split(" ")[2]);
            }
            if (isValidBuy(input)) {
                type = "buy";
                controlBox.setCardName(input.split(" ")[1]);
            }
            if (isValidSell(input)) {
                type = "sell";
                controlBox.setCardName(input.split(" ")[1]);
            }
            if (input.equalsIgnoreCase("show")) {
                type = "show";
            }
            if (input.equalsIgnoreCase("help")) {
                type = "help";
            }
            controlBox.setType(type);
            Shop.input(controlBox);
        }
        if (region.equals("Collection")) {
            controlBox = new ControlBox("Collection", type);
            if (input.equals("exit")) {
                region = "MainMenu";
                printInMenu ( );
            }
            if (input.equals("show")) {
                type = "show";
            }
            if (isValidSearchCollection(input)) {
                type = "searchCollection";
                controlBox.setCardName(input.split(" ")[1]);
            }
            if (input.equals("save")) {
                type = "save";
            }
            if (isValidCreateDeck(input)) {
                type = "createDeck";
                controlBox.setDeckName(input.split(" ")[2]);
            }
            if (isValidDeleteDeck(input)) {
                type = "deleteDeck";
                controlBox.setDeckName(input.split(" ")[2]);
            }
            if (isValidAdd(input)) {
                type = "add";
                controlBox.setCardName(input.split(" ")[1]);
                controlBox.setDeckName(input.split(" ")[4]);
            }
            if (isValidRemove(input)) {
                type = "remove";
                controlBox.setCardName(input.split(" ")[1]);
                controlBox.setDeckName(input.split(" ")[4]);
            }
            if (isValidValidateDeck(input)) {
                type = "validateDeck";
                controlBox.setDeckName(input.split(" ")[2]);
            }
            if (isValidSelectDeck(input)) {
                type = "selectDeck";
                controlBox.setDeckName(input.split(" ")[2]);
            }
            if (input.equals("show all decks")) {
                type = "showAllDecks";
            }
            if (isValidShowDeck(input)) {
                type = "showDeck";
                controlBox.setDeckName(input.split(" ")[2]);
            }
            if (input.equals("help")) {
                type = "help";
            }
            controlBox.setType(type);
            Collection.input(controlBox);
        }
        if (region.equals("Account")) {
            controlBox = new ControlBox("Account", type);
            if (isValidCreateAccount(input)) {
                type = "create account";
                controlBox.setUserName(input.split(" ")[2]);
            }
            if (isValidLogin(input)) {
                type = "login";
                controlBox.setUserName(input.split(" ")[1]);
            }
            if (input.equals("show leaderboard")) {
                type = "show leaderboard";
            }
            if (input.equals("save")) {
                type = "save";
            }
            if (input.equals("logout")) {
                System.out.println("logout2");
                type = "logout";
            }
            if (input.equals("help")) {
                type = "help";
            }
            controlBox.setType(type);
            boolean o = Account.input(controlBox).isSucces ();
            if (o) {
                region = "MainMenu";
            }
            return -1;
        }
        return 0;
    }*/
    public static void sendToServer( ControlBox controlBox){
		YaGson yaGson = yaGson ();
        String oblect = yaGson.toJson ( controlBox );
        serverOutput.format ( oblect+"\n" );
        serverOutput.flush ();
    }
	public static ControlBox giveFromGraphic(ControlBox controlBox){
        if ( controlBox == null ) {
            controlBox = new ControlBox (  );
            controlBox.setRegion ( "xxx" );
        }
        sendToServer ( controlBox );
        ControlBox answer = getFromServer (  );
        if ( answer.getType ( )!=null ){
            if ( answer.getType ().equals ( "matchMaking" ) )
            if ( answer.isSucces () ){
                Graphic.setRegion ( "Battle" );
                return null ;
            }
        }
        return answer;
	}
	public static YaGson yaGson(){
    	return yaGson;
	}
    public static ControlBox getFromServer (  ) {
        String get = "";
        while ( true ) {
            if ( serveerInput.hasNextLine ( ) ) {
                get = serveerInput.nextLine ( );
                break;
            }
        }
        return yaGson.fromJson ( get,ControlBox.class );
    }
    public static ControlBox getInWait(){
        if ( serveerInput.hasNextLine () ){
            String get = serveerInput.nextLine ( );
            return yaGson.fromJson ( get,ControlBox.class );
        }
        return null;
    }
    public static void printInMenu () {
        System.out.println ( "1.Collection" );
        System.out.println ( "2.Shop" );
        System.out.println ( "3.Battle" );
        System.out.println ( "4.Exit" );
        System.out.println ( "5.Account" );
        System.out.println ( "6.Help" );
    }
}
