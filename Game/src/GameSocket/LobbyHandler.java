package GameSocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class LobbyHandler implements Runnable{
    private Vector<OnlinePlayer> players = null;
    private int capacity = 0;
    private ServerSocket ss = null;
    public LobbyHandler(Vector<OnlinePlayer> players, int capacity) {
        this.players = players;
        this.capacity = capacity;

    }

    @Override
    public void run() {
        try {
            ss = new ServerSocket(12345);
            System.out.println("Server started");
            for(int i=0;i<capacity;i++){
                Socket s = ss.accept();
                OnlinePlayer p = new OnlinePlayer(s);
                p.start();
                this.players.set(i,p);
            }

            ss.close();
            //System.out.println("Server closed");
        }
        catch (IOException i){
            System.out.println(i);
        }
    }
}
