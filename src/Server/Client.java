package Server;

import ControlBox.ControlBox;
import Server.Moudle.Account;
import Server.Moudle.Battle;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class Client implements Runnable {
	private static ArrayList<Client> clients = new ArrayList<> ( );
	private Account account;
	private Battle battle;
	private Socket socket;
	private Scanner scanner;
	private Formatter formatter;

	public Client ( Socket socket ) {
		this.socket = socket;
		clients.add ( this );
		try {
			scanner = new Scanner ( socket.getInputStream ( ) );
			formatter = new Formatter ( socket.getOutputStream ( ) );
		} catch (IOException e) {
			e.printStackTrace ( );
		}
	}

	public Battle getBattle () {
		return battle;
	}

	public void setBattle ( Battle battle ) {
		this.battle = battle;
	}

	public Socket getSocket () {
		return socket;
	}

	public Account getAccount () {
		return account;
	}

	public static ArrayList<Client> getClients () {
		return clients;
	}

	public ControlBox recieve () {
		GsonBuilder gsonBuilder = new GsonBuilder ( );
		Gson gson = gsonBuilder.create ( );
		String get = "";
		while ( true ) {
			if ( scanner.hasNextLine ( ) ) {
				get = scanner.nextLine ();
				break;
			}
		}
		ControlBox controlBox = gson.fromJson ( get , ControlBox.class );
		return controlBox;
	}

	public void setAccount ( Account account ) {
		this.account = account;
	}

	public void send ( ControlBox controlBox ) {
		GsonBuilder gsonBuilder = new GsonBuilder ( );
		Gson gson = gsonBuilder.create ( );
		String str = gson.toJson ( controlBox );
		formatter.format ( str + "\n" );
		formatter.flush ( );
	}

	@Override
	public void run () {
		while ( true ) {
			ControlBox controlBox = this.recieve ( );
			ControlBox answer = new ControlBox ( );
			switch ( controlBox.getRegion ( ) ) {
				case "Account":
					answer = Account.input ( controlBox , this );
					break;
				case "Battle":
					answer = Battle.input ( controlBox , this );
					break;
				case "Client":
					String type = controlBox.getType ( );
					if ( type.equals ( "getMainAccount" ) )
						answer.setAccount ( account );
					else if ( type.equals ( "getCurrentBattle" ) )
						answer.setBattle ( battle );
					break;
			}
			this.send ( answer );
		}
	}
}
