package GameSocket;

import Gameplay.Card;
import utils.ResourceLoader;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Vector;


public class NIOClient extends Thread{
    private ByteBuffer buffer = ByteBuffer.allocate(1024);
    private SocketChannel channel;
    private ClientState currentState;
    private String deckPath;
    private Vector<Request> events;
    private ArrayList<PlayerInfo> playerInfos;
    private boolean connected;
    private boolean lobbyLoaded;
    private int playerID;
    private ArrayList<LobbyObserver> lobbyObservers;
    public NIOClient(String hostIP){
        lobbyObservers = new ArrayList<>();
        this.events = new Vector<Request>();
        this.currentState = ClientState.IDLE;
        this.connected = false;
        this.lobbyLoaded = false;
        this.playerInfos = new ArrayList<PlayerInfo>(4);
        for(int i = 0 ;i < 4 ;i++){
            playerInfos.add(null);
        }
        try {
            SocketAddress address = new InetSocketAddress(hostIP, 5000);
            channel = SocketChannel.open(address);

            channel.configureBlocking(true);
            Request req = new Request(ProtocolOperation.USER,"Arktik assets/icon.png 100");
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
                    playerID = ByteBuffer.wrap(serverRes.getData()).getInt();
                    if (channel.isOpen()){
                        connected = true;
                    }
                }

            }
            buffer.clear();
            byteRead = channel.read(buffer);
            if (byteRead > 0){
                buffer.flip();
                Request serverRes = Request.decodeBytes(buffer.array());
                if (serverRes.getOperation() == ProtocolOperation.LOBBY){
                    int i = 0;

                    while (i < serverRes.getBytesLength()){
                        byte[] slice = Arrays.copyOfRange(serverRes.getData(),i,serverRes.getBytesLength());
                        PlayerInfo playerInfo = PlayerInfo.decodeBytes(slice);
                        i += playerInfo.encodeBytes().length;
                        playerInfos.set(playerInfo.getPlayerID(),playerInfo);
                    }
                    lobbyLoaded = true;
                    notifyLobbyObserver();
                    System.out.println("Successfully Connected");
                }
            }
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

    @Override
    public void run(){
        try {
            while (currentState == ClientState.IDLE || currentState == ClientState.READY || currentState == ClientState.LOADING) {
                int byteRead = readIntoBuffer();
                if (byteRead > 0){
                    Request serverReq = Request.decodeBytes(buffer.array());

                    switch (serverReq.getOperation()){
                        case ProtocolOperation.DECK:
                            currentState = ClientState.LOADING;
                            Request request = new Request(ProtocolOperation.DECK);
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
                            break;
                        case LOBBY:
                            //System.out.println("BRUH");
                            int i = 0;

                            while (i < serverReq.getBytesLength()){
                                byte[] slice = Arrays.copyOfRange(serverReq.getData(),i,serverReq.getBytesLength());
                                PlayerInfo playerInfo = PlayerInfo.decodeBytes(slice);
                                i += playerInfo.encodeBytes().length;
                                playerInfos.set(playerInfo.getPlayerID(),playerInfo);

                            }
                            System.out.println(playerInfos);
                            notifyLobbyObserver();
                            break;
                        case COUNT:
                            System.out.println(serverReq.getDataUTF());
                            break;
                        default:
                            //System.out.println(buffer.asLongBuffer().get());
                    }
                    buffer.clear();
                }
                for (Iterator<Request> it = events.iterator(); it.hasNext(); ) {
                    Request e = it.next();
                    switch (e.getOperation()){
                        case READY:
                            buffer.clear().put(e.encodeBytes()).flip();
                            while (buffer.hasRemaining()){
                                channel.write(buffer);
                            }
                            buffer.clear();
                            break;

                    }
                    it.remove();
                }

            }
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

    public Vector<Request> getEvents() {
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
        this.events.add(new Request(ProtocolOperation.READY));
    }

    public void playCard(Card card,int targetID){
        if(currentState == ClientState.PLAY){
            Request req = new Request(ProtocolOperation.CARD);
            req.appendData(card.getName());
            req.appendData(playerID);
            req.appendData(targetID);
            this.events.add(req);
        }

    }
    public void addLobbyObserver(LobbyObserver observer){
        lobbyObservers.add(observer);
        observer.onChange(playerInfos);

    }

    private void notifyLobbyObserver(){
        System.out.println("Notify");
        for(LobbyObserver l:lobbyObservers){
            l.onChange(playerInfos);
        }
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public ArrayList<PlayerInfo> getPlayerInfos() {
        return playerInfos;
    }

    public void setPlayerInfos(ArrayList<PlayerInfo> playerInfos) {
        this.playerInfos = playerInfos;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public boolean isLobbyLoaded() {
        return lobbyLoaded;
    }

    public void setLobbyLoaded(boolean lobbyLoaded) {
        this.lobbyLoaded = lobbyLoaded;
    }
}