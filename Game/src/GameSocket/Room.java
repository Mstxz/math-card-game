package GameSocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Vector;

public class Room {
    private String publicIp;
    private String privateIp;
    private PlayerThread[] players = null;
    private Vector<PlayerThread> eventQueue;
    private RoomServer roomServer = null;
    Room(){
        this(2);
    }
    Room(int capacity){
        this.players = new PlayerThread[capacity];

        try {
            URL whatIsMyIP = new URL("http://checkip.amazonaws.com");
            BufferedReader in = null;
            try {
                in = new BufferedReader(new InputStreamReader(
                        whatIsMyIP.openStream()));
                String ip = in.readLine();
                this.publicIp = ip;
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            this.privateIp = InetAddress.getLocalHost().getHostAddress();
        }
        catch (UnknownHostException e){
            e.printStackTrace();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.roomServer = new RoomServer(5000,capacity,players);
        this.roomServer.start();
    }

    public RoomServer getRoomServer() {
        return roomServer;
    }
    public void cleanUp(){
        this.roomServer.setRunning(false);
        for(PlayerThread t: players){
            if (t != null){
                t.setFinished(true);
            }
        }
    }
    public void start(){
    }

    public String getPublicIp() {
        return publicIp;
    }

    public String getPrivateIp() {
        return privateIp;
    }
}
