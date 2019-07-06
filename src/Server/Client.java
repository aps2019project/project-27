package Server;

import ControlBox.ControlBox;
import Server.Moudle.Account;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class Client {
	private static ArrayList<Client> clients = new ArrayList<> (  );
	private Account account;
	private Socket socket;
	private Scanner scanner;
	private Formatter formatter;
	public Client(Socket socket){
		this.socket = socket;
		clients.add ( this );
		try {
			scanner = new Scanner ( socket.getInputStream () );
			formatter = new Formatter ( socket.getOutputStream () );
		} catch (IOException e) {
			e.printStackTrace ( );
		}
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
	public ControlBox recieve(){
		GsonBuilder gsonBuilder = new GsonBuilder ();
		Gson gson = gsonBuilder.create ();
		ControlBox controlBox = gson.fromJson ( scanner.nextLine (),ControlBox.class );
		return controlBox;
	}

	public void setAccount ( Account account ) {
		this.account = account;
	}
	public void send (ControlBox controlBox){
		GsonBuilder gsonBuilder = new GsonBuilder ();
		Gson gson = gsonBuilder.create ();
		String str = gson.toJson ( controlBox );
		formatter.format ( str+"\n" );
		formatter.flush ();
	}
}
