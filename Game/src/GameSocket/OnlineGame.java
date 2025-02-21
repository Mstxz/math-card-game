package GameSocket;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Vector;

public class OnlineGame{
    private int capacity;
    private String privateIp = "";
    private OtherPlayer[] otherPlayers = null;
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
        this.otherPlayers = new OtherPlayer[capacity];
        this.capacity = capacity;
        this.incomingEvent = new Vector<PlayerEvent>();
        this.roomServer = new RoomServer(12345,capacity,otherPlayers,incomingEvent);
        this.roomServer.start();
    }

    public RoomServer getRoomServer() {
        return roomServer;
    }
    public void cleanUp(){
        for(OtherPlayer t: otherPlayers){
            if (t != null){
                t.getPt().setFinished(true);
            }
        }
        this.roomServer.setRunning(false);
    }
    public Client waitForLobby(){
        Client self = new Client(this.getPrivateIp(),12345);
        Client self2 = new Client(this.getPrivateIp(),12345);
        Client self3 = new Client(this.getPrivateIp(),12345);
        Client self4 = new Client(this.getPrivateIp(),12345);
        try {
            self.send("NAME Arktik");
            self2.send("NAME Pupe");
            self3.send("NAME Gun");
            self4.send("NAME Pleng");
        } catch (Exception e) {
            e.printStackTrace();
        }
        int registered = 0;
        while(registered != capacity){
            if (!incomingEvent.isEmpty()){
                PlayerEvent e = incomingEvent.getFirst();
                if(this.otherPlayers[e.getPlayerIndex()] == null){
                    continue;
                }
                if (e.getType() == PlayerEventType.QUIT){
                    System.out.println(this.incomingEvent);
                    this.otherPlayers[e.getPlayerIndex()].getPt().interrupt();
                    System.out.println(this.otherPlayers[e.getPlayerIndex()].getPt().isInterrupted());
                    System.out.println("Terminated");
                    this.otherPlayers[e.getPlayerIndex()] = null;
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
                if (e.getType() == PlayerEventType.REGISTER_NAME){
                    this.otherPlayers[e.getPlayerIndex()].setName(e.getArg());
                    System.out.println("Player "+ e.getArg() +" join the game.");
                    registered += 1;
                }
                incomingEvent.removeFirst();
            }

        }

        return self;
    }
    public void start(){
        Client self = waitForLobby();
        int turn = 0;
        try {
            this.otherPlayers[turn].getPt().send("YOUR_TURN");
        }
        catch (IOException ie){
            ie.printStackTrace();
        }

        while(true){
            if (!incomingEvent.isEmpty()){
                PlayerEvent e = incomingEvent.getFirst();
                if(this.otherPlayers[e.getPlayerIndex()] == null){
                    continue;
                }
                if (e.getType() == PlayerEventType.QUIT){
                    this.otherPlayers[e.getPlayerIndex()].getPt().setFinished(true);
                    try {
                        this.otherPlayers[e.getPlayerIndex()].getPt().join();
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
                if (e.getType() == PlayerEventType.END_TURN && this.otherPlayers[turn] == this.otherPlayers[e.getPlayerIndex()]){
                    turn = (turn + 1) % capacity;
                    try {
                        this.otherPlayers[turn].getPt().send("YOUR_TURN");
                    }
                    catch (IOException ie){
                        ie.printStackTrace();
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
