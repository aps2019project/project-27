package Server;

import ControlBox.ControlBox;
import Server.Moudle.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class Main {
	public static void main ( String[] args ) throws FileNotFoundException {
		//load phase
		Load.loadAccounts ( );
		Load.loadMinionAndHeros ( );
		Load.loadSpells ( );
		Load.loadItems ( );
		Battle.setCollectibleItems ( Item.getItems ( ) );
		Card.addMAndH ( MinionAndHero.getMinionAndHeroes ( ) );
		Card.addSpells ( Spell.getSpells ( ) );
		new Thread ( () -> {
			try {
				ServerSocket serverSocket = new ServerSocket ( 8888 );
				while ( true ) {
					Client client = new Client ( serverSocket.accept ( ) );
					ArrayList clients = Client.getClients ( );
					new Thread ( () -> {
						while ( true ) {
							ControlBox controlBox = client.recieve ( );
							ControlBox answer = null;
							switch ( controlBox.getRegion ( ) ) {
								case "Account":
									answer = Account.input ( controlBox );
									break;
								case "Battle":
									answer = Battle.input ( controlBox );
									break;
							}
							client.send ( answer );
						}
					} ).start ( );
					int a = 1;
				}
			} catch (IOException e) {
				e.printStackTrace ( );
			}
		} ).start ( );

	}
}
