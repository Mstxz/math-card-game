package GameSocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class RoomServer extends Thread {
    private int port = 12345;
    private ServerSocket ss = null;
    private OtherPlayer[] threads = null;
    private int playerInLobby = 0;
    private int capacity = 0;
    private boolean running = true;
    private Vector<PlayerEvent> ev;
    // Constructor with port
    public RoomServer(int port, int capacity, OtherPlayer[] threads, Vector<PlayerEvent> ev) {
        this.port = port;
        this.threads = threads;
        this.capacity = capacity;
        this.ev = ev;
    }
    @Override
    public void run() {
        try {
            ss = new ServerSocket(port);
            System.out.println("Server started");
            for(int i=0;i<capacity;i++){
                Socket s = ss.accept();
                PlayerThread pt = new PlayerThread(i,s,ev);
                pt.start();
                this.threads[i] = new OtherPlayer("",pt);

                playerInLobby += 1;
            }

            ss.close();
            //System.out.println("Server closed");
        }
        catch (IOException i){
            System.out.println(i);
        }

    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public int getPlayerInLobby() {
        return playerInLobby;
    }

    public void setPlayerInLobby(int playerInLobby) {
        this.playerInLobby = playerInLobby;
    }
}
