package GameSocket;

import java.net.ServerSocket;
import java.util.Vector;

public class GameServer {
    private int port = 12345;
    private ServerSocket ss = null;
    private Vector<OnlinePlayer> players = null;
    private int playerInLobby = 0;
    private int capacity = 0;
    private Vector<PlayerEvent> ev;
    public GameServer(int capacity, Vector<PlayerEvent> ev) {
        this.players = new Vector<OnlinePlayer>();
        this.players.ensureCapacity(capacity);
        this.capacity = capacity;
        this.ev = ev;

    }
    public void start(){
        new Thread(new LobbyHandler(players,capacity)).start();
        boolean exit = false;
        do{
            exit = true;
            for(OnlinePlayer p:players){
                if(!p.isReady()){
                    exit = false;
                }
            }
        }while(!exit);
    }

}
