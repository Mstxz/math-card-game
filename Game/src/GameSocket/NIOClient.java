package GameSocket;

import Gameplay.Player;
import utils.ResourceLoader;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;


public class NIOClient {
    private ByteBuffer buffer = ByteBuffer.allocate(1024);
    private SocketChannel channel;
    private ClientState currentState;
    private String deckPath;
    private Vector<String> events;
    private ArrayList<PlayerInfo> players;
    public NIOClient(int roomCapacity){
        this.events = new Vector<String>();
        this.currentState = ClientState.IDLE;
        this.players = new ArrayList<PlayerInfo>(roomCapacity);
        try {
            SocketAddress address = new InetSocketAddress("localhost", 5000);
            channel = SocketChannel.open(address);
            channel.configureBlocking(true);
            Request req = new Request(ProtocolOperation.USER,"Arktik assets/icon.png 100");
            System.out.println(new String(req.getData(), 0, req.getBytesLength(),StandardCharsets.UTF_8));
            buffer.clear();
            buffer.put(req.encodeBytes());
            buffer.flip();
            while (buffer.hasRemaining()){
                channel.write(buffer);
            }
            buffer.clear();
            int byteRead = channel.read(buffer);
            if (byteRead > 0){
                buffer.flip();
                Request serverRes = Request.decodeBytes(buffer.array());

                //String data = new String(buffer.array(),buffer.position(),byteRead);
                if(serverRes.getOperation() == ProtocolOperation.ACKN){
                    System.out.println("Acknowledged");
                }
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
        } catch (SocketException ex) {
            throw new RuntimeException("Connection Loss");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public void lobby() throws RuntimeException{
        try {
            while (currentState == ClientState.IDLE || currentState == ClientState.READY) {
                int byteRead = readIntoBuffer();
                if (byteRead > 0){
                    Request serverReq = Request.decodeBytes(buffer.array());
//                    String data = new String(buffer.array(),buffer.position(),byteRead);
//                    String[] args = data.split(" ");

                    switch (serverReq.getOperation()){
                        case ProtocolOperation.DECK:
                            currentState = ClientState.LOADING;
                            Request request = new Request(ProtocolOperation.DECK);
                            //ByteBuffer bf = ByteBuffer.allocate(2048);
                            //buffer.clear().put("DECK".getBytes());
                            try (BufferedInputStream f = ResourceLoader.loadFileAsStream(deckPath);
                                 InputStreamReader fr = new InputStreamReader(f);
                                 BufferedReader br = new BufferedReader(fr)
                            ){
                                String line;
                                while ((line = br.readLine()) != null){
                                    request.appendData(line).appendData("\r\n");
                                    //buffer.put("\r\n".getBytes());
                                    //buffer.put(line.getBytes());
                                }
                            }
                            //buffer.put("\r\nEND".getBytes()).flip();
                            buffer.clear().put(request.encodeBytes()).flip();
                            while (buffer.hasRemaining()){
                                channel.write(buffer);
                            }
                            buffer.clear();
//                            buffer.clear();
//                            try (BufferedInputStream f = ResourceLoader.loadFileAsStream(deckPath);
//                                 InputStreamReader fr = new InputStreamReader(f);
//                                 BufferedReader br = new BufferedReader(fr)
//                            ){
//                                String line;
//                                while ((line = br.readLine()) != null){
//                                    buffer.put(line.getBytes());
//                                    buffer.put("\r\n".getBytes());
//                                }
//                                buffer.flip();
//                                while (buffer.hasRemaining()){
//                                    channel.write(buffer);
//                                }
//                            }
//
//                            buffer.clear().put("END OF DECK".getBytes()).flip();
//                            while (buffer.hasRemaining()){
//                                channel.write(buffer);
//                            }

//                            byteRead = readIntoBuffer();
//                            if (byteRead > 0){
//                                data = new String(buffer.array(),buffer.position(),byteRead);
//                            }

                            //players.set(0,new PlayerInfo("",2,0));
                            break;
                        case LOBBY:
                            //System.out.println("BRUH");
//                            while ((byteRead = readIntoBuffer()) != -1){
//                                if (byteRead > 1){
//                                    if (byteRead != "END".getBytes().length){
//                                        data = new String(buffer.array(),buffer.position(),byteRead);
//                                        PlayerInfo playerInfo = PlayerInfo.decodeBytes(buffer.array());
//                                        players.set(playerInfo.getPlayerID(),playerInfo);
//                                        System.out.println(playerInfo);
//                                    }
//                                    else{
//                                        break;
//                                    }
//                                }
//                            }
                            break;
                        case COUNT:
                            System.out.println(serverReq.getDataUTF());
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
                            Request clientRequest = new Request(ProtocolOperation.READY);
                            buffer.clear().put(clientRequest.encodeBytes()).flip();
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