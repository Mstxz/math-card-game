package GameSocket;

import java.net.ServerSocket;
import java.util.Vector;

public class Room {
    private int capacity = 0;
    private int numberOfPlayer = 0;
    private PlayerThread[] players = null;
    private Vector<PlayerThread> eventQueue;
    private RoomServer roomServer = null;
    Room(){
        this(2);
    }
    Room(int capacity){
        this.players = new PlayerThread[capacity];
        this.numberOfPlayer = 1;
        this.capacity = capacity;
        this.roomServer = new RoomServer(5000,capacity,players);
        this.roomServer.start();
    }

    public RoomServer getRoomServer() {
        return roomServer;
    }
}
