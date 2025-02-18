package GameSocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class RoomServer extends Thread {
    private int port = 5000;
    private ServerSocket ss = null;
    private PlayerThread[] threads = null;
    private int capacity = 0;
    private boolean running = true;
    // Constructor with port
    public RoomServer(int port,int capacity,PlayerThread[] threads) {
        this.port = port;
        this.threads = threads;
        this.capacity = capacity;
    }
    @Override
    public void run() {
        try {
            ss = new ServerSocket(port);
            System.out.println("Server started");
            for(int i=0;i<capacity;i++){
                Socket s = ss.accept();
                this.threads[i] = new PlayerThread(s);
                this.threads[i].start();
                System.out.println("Player "+ (i + 1) +" join the game.");
            }
            while (running){}
            ss.close();
            System.out.println("Server closed");
        }
        catch (IOException i){
            System.out.println(i);
        }

    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
