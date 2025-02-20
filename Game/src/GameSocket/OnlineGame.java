package GameSocket;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.Vector;

public class OnlineGame{
    private int capacity;
    private String privateIp = "";
    private PlayerThread[] otherPlayers = null;
    private Vector<PlayerEvent> incomingEvent;
    private RoomServer roomServer = null;
    OnlineGame(){
        this(2);
    }
    OnlineGame(int capacity){
        try{
            this.privateIp = InetAddress.getLocalHost().getHostAddress();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        this.otherPlayers = new PlayerThread[capacity];
        this.capacity = capacity;
        this.incomingEvent = new Vector<PlayerEvent>();
        this.roomServer = new RoomServer(5000,capacity,otherPlayers,incomingEvent);
        this.roomServer.start();
    }

    public RoomServer getRoomServer() {
        return roomServer;
    }
    public void cleanUp(){
        for(PlayerThread t: otherPlayers){
            if (t != null){
                t.setFinished(true);
            }
        }
        this.roomServer.setRunning(false);
    }
    public void start(){
        Client self = new Client(this.getPrivateIp(),5000);
        Client self2 = new Client(this.getPrivateIp(),5000);
        Client self3 = new Client(this.getPrivateIp(),5000);
        Client self4 = new Client(this.getPrivateIp(),5000);
        try {
            self.send("QUIT");
            self2.send("QUIT");
        } catch (Exception e) {
            e.printStackTrace();
        }
        while(true){
            if (!incomingEvent.isEmpty()){
                PlayerEvent e = incomingEvent.getFirst();
                if (e.getType() == PlayerEventType.QUIT && this.otherPlayers[e.getPlayerIndex()] != null){
                    System.out.println("Quiting");
                    this.otherPlayers[e.getPlayerIndex()].setFinished(true);
                    try {
                        this.otherPlayers[e.getPlayerIndex()].join();
                        this.otherPlayers[e.getPlayerIndex()] = null;
                    }
                    catch (InterruptedException ie){
                        ie.printStackTrace();
                    }
                    boolean ended = true;
                    for(int i = 0;i<capacity;i++){
                       if(this.otherPlayers[i] != null){
                           ended = false;
                       }
                    }
                    if(ended){
                        break;
                    }
                }
                incomingEvent.removeFirst();
            }
        }
//        try {
//            self.close();
//            System.out.println("1");
//            self2.close();
//            System.out.println("2");
//            self3.close();
//            System.out.println("3");
//            self4.close();
//            System.out.println("4");
//            cleanUp();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
    }

    public String getPrivateIp() {
        return privateIp;
    }
}
