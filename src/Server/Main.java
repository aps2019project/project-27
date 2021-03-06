package Server;

import Server.Moudle.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        //load phase
        Load.loadAccounts();
        Load.loadMinionAndHeros();
        Load.loadSpells();
        Load.loadItems();
        Battle.setCollectibleItems(Item.getItems());
        Card.addMAndH(MinionAndHero.getMinionAndHeroes());
        Card.addSpells(Spell.getSpells());
        Load.saveMAndH();
        Load.saveSpells();
        Graphic.main(args);
        new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(8888);
                while (true) {
                    Client client = new Client(serverSocket.accept());
                    ArrayList clients = Client.getClients();
                    new Thread(client).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
