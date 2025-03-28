package GameSocket;

import GUI.Component.Game;
import GUI.Setting.UserPreference;
import Gameplay.Card;
import Gameplay.Player;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;


public class NIOClient extends Game {
    private final ByteBuffer buffer = ByteBuffer.allocate(1024);
    private SocketChannel channel;
    private ClientState currentState;
    private String deckPath;
    private final ArrayList<Request> events;
    private boolean lobbyLoaded;
    private boolean gameStarted;
    private final ArrayList<LobbyObserver> lobbyObservers;
    private int currentTurn;
    public NIOClient(){
        super();
        lobbyObservers = new ArrayList<>();
        this.events = new ArrayList<>();
        this.currentState = ClientState.IDLE;
        this.lobbyLoaded = false;
        this.gameStarted = false;
        this.currentTurn = 0;
    }
    public void connect(String hostIP){
        try {
            SocketAddress address = new InetSocketAddress(hostIP, 5000);
            channel = SocketChannel.open(address);
            System.out.println("Client Start");
            channel.configureBlocking(true);
            Request req = new Request(ProtocolOperation.USER);
            req.appendData(UserPreference.getInstance().getProfile().getName());
            req.appendData(UserPreference.getInstance().getProfile().getProfilePicture().getProfileURL());
            req.appendData(100);
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
                    playerOrder = ByteBuffer.wrap(serverRes.getData()).getInt();
                    System.out.println("Player Index:" + playerOrder);
                }

            }
            buffer.clear();
            byteRead = channel.read(buffer);
            if (byteRead > 0){
                buffer.flip();
                Request serverRes = Request.decodeBytes(buffer.array());
                if (serverRes.getOperation() == ProtocolOperation.LOBBY){
                    try (RequestReader r = new RequestReader(serverRes)){
                        while (!r.reachTheEnd()){
                            PlayerInfo playerInfo = PlayerInfo.decodeBytes(r.readByteData());
                            turnOrder.add(playerInfo);
                        }

                    } catch (Exception e) {
                        throw new RuntimeException(e);
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
            while (channel.isOpen() && (currentState == ClientState.IDLE || currentState == ClientState.READY || currentState == ClientState.LOADING)) {
                int byteRead = readIntoBuffer();
                if (byteRead > 0){
                    Request serverReq = Request.decodeBytes(buffer.array());

                    switch (serverReq.getOperation()){
                        case ProtocolOperation.DECK:
                            currentState = ClientState.LOADING;
                            Request request = new Request(ProtocolOperation.DECK);
                            try (BufferedInputStream f = new BufferedInputStream(new FileInputStream(deckPath));
                                 InputStreamReader fr = new InputStreamReader(f);
                                 BufferedReader br = new BufferedReader(fr)
                            ){
                                String line;
                                while ((line = br.readLine()) != null){
                                    request.appendData(line+"\r\n");
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
                            try (RequestReader r = new RequestReader(serverReq)){
                                turnOrder.clear();
                                while (!r.reachTheEnd()){
                                    PlayerInfo playerInfo = PlayerInfo.decodeBytes(r.readByteData());
                                    turnOrder.add(playerInfo);
                                }
                                System.out.println(turnOrder);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                            notifyLobbyObserver();
                            break;
                        case COUNT:
                            try (RequestReader r = new RequestReader(serverReq)){
                                int count = r.readInt();
                                System.out.println(count);


                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }

                            break;
                        case START_GAME:

                            try (RequestReader reader = new RequestReader(serverReq)){
                                currentTurn = reader.readInt();
                                System.out.println("Current Turn: " + currentTurn);
                            }
                            catch (Exception ex){
                                ex.printStackTrace();
                            }
                            currentState = ClientState.WAIT;
                            gameStarted = true;
                            notifyLobbyObserver();
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
            System.out.println("Switch");
            while (channel.isOpen() && (currentState == ClientState.PLAY || currentState == ClientState.WAIT)) {
                buffer.clear();
                int byteRead = readIntoBuffer();
                if (byteRead > 0) {
                    ArrayList<Request> waitingRequest = getAllRequest(buffer.array(),byteRead);
                    //Request serverReq = Request.decodeBytes(buffer.array());
                    //System.out.println(serverReq.getOperation().name());
                    for (Request req : waitingRequest){
                        System.out.println(req.getOperation());
                        switch (req.getOperation()) {
                            case DRAW:
                                // other play card
                                try (RequestReader r = new RequestReader(req)) {
                                    int receiver = r.readInt();
                                    while (!r.reachTheEnd()) {
                                        Card cardReceived = Card.decode(r.readByteData());
                                        turnOrder.get(receiver).getHand().add(cardReceived);
                                        turnOrder.get(receiver).getDeck().getCards().removeLast();
                                    }
                                    observer.onHandChanged();
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                                break;
                            case CARD:
                                // other draw card
                                break;
                            case START_TURN:
                                try (RequestReader r = new RequestReader(req)) {
                                    currentTurn = r.readInt();
                                    System.out.println(currentTurn + " " + playerOrder);
                                    if (currentTurn == playerOrder){
                                        currentState = ClientState.PLAY;
                                        observer.onTurnArrive();
                                    }
                                    else{
                                        currentState = ClientState.WAIT;
                                    }
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                                break;
                            case HAND_UPDATE:
                                try (RequestReader r = new RequestReader(req)) {
                                    while (!r.reachTheEnd()) {

                                        PlayerInfo playerInfo = PlayerInfo.decodeBytes(r.readByteData());
                                        if (playerInfo != null){
                                            turnOrder.get(playerInfo.getPlayerID()).setMaxMana(playerInfo.getMaxMana());
                                            turnOrder.get(playerInfo.getPlayerID()).setMana(playerInfo.getMana());
                                            turnOrder.get(playerInfo.getPlayerID()).setHp(playerInfo.getHp());
                                            turnOrder.get(playerInfo.getPlayerID()).getHand().clear();
                                            turnOrder.get(playerInfo.getPlayerID()).getHand().addAll(playerInfo.getHand());
                                        }
                                    }
                                    observer.onHandChanged();
                                    observer.onStatChanged();
                                    System.out.println("Hand Update " + turnOrder);
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                                break;
                            case END_GAME:
                                try (RequestReader r = new RequestReader(req)) {
                                    int winnerID = r.readInt();
                                    observer.onGameEnded(turnOrder.get(winnerID));
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                                break;
                        }
                    }
                }
                for (Iterator<Request> it = events.iterator(); it.hasNext(); ) {
                    Request e = it.next();
                    buffer.clear().put(e.encodeBytes()).flip();
                    while (buffer.hasRemaining()) {
                        channel.write(buffer);
                    }
                    buffer.clear();
                    it.remove();
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private ArrayList<Request> getAllRequest(byte[] requestsByte,int byteRead){
        ArrayList<Request> requestArrayList = new ArrayList<>();
        int currentIndex = 0;
        do{
            Request req = Request.decodeBytes(Arrays.copyOfRange(requestsByte,currentIndex,byteRead));
            requestArrayList.add(req);
            currentIndex += 8 + req.getBytesLength();
        } while (currentIndex != byteRead);
        return requestArrayList;
    }

    public ArrayList<Request> getEvents() {
        return events;
    }

    public void pressedReady(){
        if (this.deckPath == null || this.deckPath.equals("")){
            return;
        }
        if(currentState != ClientState.READY){
            currentState = ClientState.READY;
        }
        else{
            currentState = ClientState.IDLE;
        }
        this.events.add(new Request(ProtocolOperation.READY));
    }

    public void setDeckPath(String deckPath){
        this.deckPath = deckPath;
        System.out.println(deckPath);
    }

    public void addLobbyObserver(LobbyObserver observer){
        lobbyObservers.add(observer);
        observer.onLobbyChange(turnOrder);

    }

    private void notifyLobbyObserver(){
        System.out.println("Notify");
        for(LobbyObserver l:lobbyObservers){
            l.onLobbyChange(turnOrder);
        }
    }

    public int getPlayerOrder() {
        return playerOrder;
    }

    public void setPlayerOrder(int playerOrder) {
        this.playerOrder = playerOrder;
    }

    public boolean isLobbyLoaded() {
        return lobbyLoaded;
    }

    public void setLobbyLoaded(boolean lobbyLoaded) {
        this.lobbyLoaded = lobbyLoaded;
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    public void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
    }

    public void closeClient(){
        try {
            channel.close();
            System.out.println("Client Closed");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void notifyGameStart() {
        observer.onGameStart(currentTurn);
    }

    @Override
    public void notifyEndTurn() {
        if(currentState == ClientState.PLAY){
            Request req = new Request(ProtocolOperation.END_TURN);
            this.events.add(req);
            currentState = ClientState.WAIT;
        }
        observer.onTurnEnded();
    }

    @Override
    public boolean isGameEnded() {
        return false;
    }

    @Override
    public void playerPlay(int cardIndex, Player receiver) {
        if(currentState == ClientState.PLAY){
            Request req = new Request(ProtocolOperation.CARD);
            req.appendData(cardIndex);
            req.appendData(turnOrder.get(playerOrder).getHand().get(cardIndex).encode());
            req.appendData(playerOrder);
            req.appendData(receiver.getPlayerNumber());
            this.events.add(req);
        }
    }
}