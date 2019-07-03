package Server;

import Moudle.Account;

import java.net.Socket;

public class Client {
	private Account account;
	private Socket socket;
	public Client(Socket socket){
		this.socket = socket;
	}

	public Socket getSocket () {
		return socket;
	}

	public Account getAccount () {
		return account;
	}
}
