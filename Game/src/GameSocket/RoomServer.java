package GameSocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

public class RoomServer extends Thread {
    private int port = 12345;
    private ServerSocket ss = null;
    private PlayerThread[] threads = null;
    private int capacity = 0;
    private boolean running = true;
    private Vector<PlayerEvent> ev;
    // Constructor with port
    public RoomServer(int port, int capacity, PlayerThread[] threads, Vector<PlayerEvent> ev) {
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
                this.threads[i] = new PlayerThread(s);
                this.threads[i].start();
                System.out.println("Player "+ (i + 1) +" join the game.");
            }
            while (running){
                for(int i=0;i<capacity;i++){
                    if(this.threads[i] == null){
                        continue;
                    }
                    String m = this.threads[i].getIncomingMessage();
                    switch (m){
                        case "QUIT":
                            this.ev.add(new PlayerEvent(i,PlayerEventType.QUIT));
                            break;
                        case "END_TURN":
                            this.ev.add(new PlayerEvent(i,PlayerEventType.END_TURN));
                            break;
                        case "DRAW":
                            this.ev.add(new PlayerEvent(i,PlayerEventType.DRAW));
                            break;
                        default:
                            if (m.equals("")){
                                break;
                            }
                            String[] played = m.split(" ");
                            this.ev.add(new PlayerEvent(i,PlayerEventType.PLAY,Integer.parseInt(played[1])));
                            break;
                    }
                }
            }
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
