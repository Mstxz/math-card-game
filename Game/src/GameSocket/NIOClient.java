package GameSocket;

import Gameplay.Player;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;


public class NIOClient {
    private ByteBuffer buffer = ByteBuffer.allocate(1024);
    private SocketChannel channel;
    private ClientState currentState;
    private String deckPath;
    private Vector<String> events;
    private ArrayList<PlayerResource> players;
    public NIOClient(int roomCapacity){
        this.events = new Vector<String>();
        this.currentState = ClientState.IDLE;
        this.players = new ArrayList<PlayerResource>(roomCapacity);
        try {
            SocketAddress address = new InetSocketAddress("localhost", 5000);
            channel = SocketChannel.open(address);
            channel.configureBlocking(true);
            buffer.clear();
            buffer.put("USER Arktik".getBytes());
            buffer.flip();
            while (buffer.hasRemaining()){
                channel.write(buffer);
            }
            buffer.clear();
            int byteRead = channel.read(buffer);
            if (byteRead > 0){
                buffer.flip();
                String data = new String(buffer.array(),buffer.position(),byteRead);
                System.out.println("Acknowledged");
            }
            buffer.clear();
            System.out.println("Successfully Connected");
            channel.configureBlocking(false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private int readIntoBuffer() throws RuntimeException,IOException{
        buffer.clear();
        try{
            int byteRead = channel.read(buffer);
            if (byteRead == -1) {
                throw new RuntimeException("Connection Loss");
            }
            buffer.flip();
            return byteRead;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return 0;
    }
    public void lobby() throws RuntimeException{
        try {
            while (currentState == ClientState.IDLE || currentState == ClientState.READY) {
                int byteRead = readIntoBuffer();
                if (byteRead > 0){
                    String data = new String(buffer.array(),buffer.position(),byteRead);
                    switch (data){
                        case "SETUP":
                            currentState = ClientState.LOADING;
                            buffer.clear().put("DECK".getBytes()).flip();
                            while (buffer.hasRemaining()){
                                channel.write(buffer);
                            }
                            buffer.clear();
                            BufferedReader fr = new BufferedReader(new FileReader(deckPath));
                            String line;
                            while ((line = fr.readLine()) != null){
                                buffer.put(line.getBytes());
                                buffer.put("\r\n".getBytes());
                            }

                            buffer.flip();
                            while (buffer.hasRemaining()){
                                channel.write(buffer);
                            }
                            buffer.clear();

                            buffer.put("END OF DECK".getBytes()).flip();
                            while (buffer.hasRemaining()){
                                channel.write(buffer);
                            }

                            byteRead = readIntoBuffer();
                            if (byteRead > 0){
                                data = new String(buffer.array(),buffer.position(),byteRead);
                            }

                            players.set(0,new PlayerResource("",2,0));
                            break;
                        default:
                            System.out.println(buffer.asLongBuffer().get());
                    }
                    buffer.clear();
                }
                for (Iterator<String> it = events.iterator(); it.hasNext(); ) {
                    String e = it.next();
                    String[] arg = e.split(" ");
                    switch (arg[0]){
                        case "ready":
                            buffer.clear().put("READY".getBytes()).flip();
                            while (buffer.hasRemaining()){
                                channel.write(buffer);
                            }
                            buffer.clear();
                            System.out.println("Ready");
                            break;

                    }
                    it.remove();
                }

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void game(){
        try {
            while (currentState == ClientState.PLAY || currentState == ClientState.WAIT) {
                buffer.clear();
                int byteRead = channel.read(buffer);
                if (byteRead > 0){
                    buffer.flip();
                    String data = new String(buffer.array(),buffer.position(),byteRead);
                    String[] arg = data.split(" ");
                    if(currentState == ClientState.PLAY){
                        switch (arg[0]){
                            case "PLAY":
                                // other play card
                                break;
                            case "DRAW":
                                // other draw card
                                break;
                        }
                    }
                    else if (currentState == ClientState.WAIT) {

                    }
                    buffer.clear();
                }

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public Vector<String> getEvents() {
        return events;
    }

    public void pressedReady(String deckPath){
        if(currentState != ClientState.READY){
            currentState = ClientState.READY;
            this.deckPath = deckPath;
        }
        else{
            currentState = ClientState.IDLE;
        }
        this.events.add("ready");
    }
}