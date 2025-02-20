package GameSocket;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Vector;

public class OnlineGame extends Thread{
    private int capacity = 0;
    private String privateIp = "";
    private OtherPlayer[] otherPlayers = null;
    private Vector<PlayerEvent> incomingEvent;
    private RoomServer roomServer = null;
    private int turn = 0;
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
    public boolean waitForLobby(){
        boolean[] ready = new boolean[capacity];
        boolean allReady = false;
        int registered = 0;
        while(registered != capacity || !allReady){
            if (!incomingEvent.isEmpty()){
                PlayerEvent e = incomingEvent.getFirst();
                if(this.otherPlayers[e.getPlayerIndex()] == null){
                    continue;
                }
                switch (e.getType()){
                    case QUIT:
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
                            return false;
                        }
                    case REGISTER_NAME:
                        this.otherPlayers[e.getPlayerIndex()].setName(e.getArg()[0]);
                        System.out.println("Player "+ e.getArg()[0] +" join the game.");
                        registered += 1;
                    case READY:
                        ready[e.getPlayerIndex()] = !ready[e.getPlayerIndex()];
                }
                incomingEvent.removeFirst();

            }
            allReady = true;
            for(boolean b:ready){
                if (!b) allReady = false;
            }
        }

        return true;
    }
    @Override
    public void run(){
        if (!this.waitForLobby()){
            return;
        }

        int turn = 0;
        try {
            this.otherPlayers[turn].getPt().send("YOUR_TURN");
            System.out.println(this.otherPlayers[turn].name);
            System.out.println("Send");
        }
        catch (IOException ie){
            ie.printStackTrace();
        }
        System.out.println(this.incomingEvent);
        while(this.handleEvent()){

        }
        this.cleanUp();
    }
    public boolean handleEvent(){
        if (!incomingEvent.isEmpty()){
            PlayerEvent e = incomingEvent.getFirst();
            System.out.println(e);
            if(this.otherPlayers[e.getPlayerIndex()] == null){
                return true;
            }

            switch (e.getType()){
                case QUIT:
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
                        return false;
                    }
                    break;
                case END_TURN:
                    turn = (turn + 1) % capacity;
                    try {
                        this.otherPlayers[turn].getPt().send("YOUR_TURN");
                    }
                    catch (IOException ie){
                        ie.printStackTrace();
                    }
                case DRAW:

            }
            incomingEvent.removeFirst();
        }
        return true;
    }
    public String getPrivateIp() {
        return privateIp;
    }
}
