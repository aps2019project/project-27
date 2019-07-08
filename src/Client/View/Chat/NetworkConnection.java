package Client.View.Chat;

import Server.Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public abstract class NetworkConnection {

    private ConnectionThread connectionThread = new ConnectionThread();
    private Consumer<Serializable> onReceiveCallBack;

    public NetworkConnection(Consumer<Serializable> onReceiveCallBack) {
        this.onReceiveCallBack = onReceiveCallBack;
        connectionThread.setDaemon(true);
    }

    public void startConnection() {
        connectionThread.start();
    }

    public void send(Serializable data) throws IOException {
        connectionThread.out.writeObject(data);
    }

    public void closeConnection() throws IOException {
        connectionThread.socket.close();
    }


    protected abstract String getIP();


    protected abstract int getPort();

    private class ConnectionThread extends Thread {
        private Socket socket;
        private ObjectOutputStream out;


        @Override
        public void run() {
            System.out.println(getIP());
            System.out.println(getPort());
            try (Socket socket = new Socket(getIP(), getPort());
                 ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                 ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

                this.socket = socket;
                this.out = out;
                socket.setTcpNoDelay(true);

                while (true) {
                    Serializable data = (Serializable) in.readObject();
                    onReceiveCallBack.accept(data);

                }

            } catch (Exception e) {
                e.getStackTrace();
                onReceiveCallBack.accept("Connection closed");
            }
        }
    }
}
