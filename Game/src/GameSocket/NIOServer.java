package GameSocket;

import Gameplay.Card;
import Gameplay.CardAction.CardAction;
import Gameplay.CardAction.Draw;
import Gameplay.CardAction.SetHp;
import Gameplay.Deck;
import Gameplay.Numbers.Constant;
import Gameplay.Player;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class NIOServer extends Thread {
    private static NIOServer instance = null;
    private HashMap<SocketChannel,Integer> registeredID;
    private PlayerState[] playerState;
    private ByteBuffer buffer;
    private long gameStarting;
    private ServerInfo serverInfo;
    private GameState gameState;


    public static NIOServer getInstance() {
        if (instance == null){
            instance = new NIOServer();
        }
        return instance;
    }

    public NIOServer() {
        registeredID = new HashMap<SocketChannel,Integer>();
        playerState = new PlayerState[4];
        buffer = ByteBuffer.allocate(1024);
        gameStarting = -1;
        serverInfo = new ServerInfo();

    }

    public void unsetCountdown(){
        gameStarting = -1;
        for (int i=0;i<4;i++){
            if (registeredID.containsValue(i)){
                playerState[i].setCountDown(11);
            }
        }
    }

    public void run() {
        try{
            ServerSocketChannel server = ServerSocketChannel.open();
            server.setOption(StandardSocketOptions.SO_REUSEADDR,true);
            server.bind(new InetSocketAddress(5000));
            serverInfo.setListening(true);
            server.configureBlocking(false);
            Selector selector = Selector.open();
            server.register(selector, SelectionKey.OP_ACCEPT);
            while (serverInfo.isRunning()) {
                if (serverInfo.isPendingClose() && registeredID.isEmpty()){
                    stopServer();
                    continue;
                }
                try {
                    selector.select();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Set<SelectionKey> keys = selector.selectedKeys();
                for (Iterator<SelectionKey> it = keys.iterator(); it.hasNext(); ) {
                    SelectionKey k = it.next();
                    if (!serverInfo.isPendingClose() && k.isAcceptable()) {
                        handleAcception(selector,k);
                    }
                    else if (!serverInfo.isPendingClose() && k.isWritable() && k.isReadable()) {
                        handleRequest(k);
                    }
                    else if(!k.isAcceptable() && k.isWritable()){
                        SocketChannel client = (SocketChannel) k.channel();
                        if(gameStarting != -1){
                            long epochSecond = Instant.now().getEpochSecond();
                            if(epochSecond < gameStarting){
                                if (playerState[registeredID.get(client)].getCountDown() > gameStarting-epochSecond){
                                    playerState[registeredID.get(client)].setCountDown(playerState[registeredID.get(client)].getCountDown() - 1);
                                    Request serverReq = new Request(ProtocolOperation.COUNT);
                                    serverReq.setData((int) (gameStarting-epochSecond));
                                    buffer.clear();
                                    buffer.put(serverReq.encodeBytes());
                                    buffer.flip();
                                    while (buffer.hasRemaining()) {
                                        client.write(buffer);
                                    }
                                    buffer.clear();
                                }
                            }
                            else if(!playerState[registeredID.get(client)].isStarted()){
                                Request serverReq = new Request(ProtocolOperation.DECK);
                                buffer.clear();
                                buffer.put(serverReq.encodeBytes());
                                buffer.flip();
                                while (buffer.hasRemaining()) {
                                    client.write(buffer);
                                }
                                buffer.clear();
                                playerState[registeredID.get(client)].setStarted(true);
                            }

                            serverInfo.updateGameStarted(playerState);

                            if (serverInfo.isGameStarted()){

                                gameStarting = -1;
                            }
                        }

                        Request bytesOut = playerState[registeredID.get(client)].getDataOutQueue().poll();
                        if (bytesOut != null){
                            buffer.clear();
                            buffer.put(bytesOut.encodeBytes());
                            buffer.flip();
                            while (buffer.hasRemaining()) {
                                client.write(buffer);
                            }
                        }
                        if (serverInfo.isPendingClose() && playerState[registeredID.get(client)].getDataOutQueue().isEmpty()){
                            if (client.isConnected()){
                                client.close();
                                registeredID.remove(client);
                            }
                        }
                    }
                    buffer.clear();
                    it.remove();
                }

            }
            selector.close();
            server.close();
            System.out.println("Server Closed");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void handleAcception(Selector selector,SelectionKey k) throws RuntimeException,IOException{
        if (k.channel() instanceof ServerSocketChannel serverChannel) {
            SocketChannel clientChannel = serverChannel.accept();
            clientChannel.configureBlocking(false);
            clientChannel.register(selector, SelectionKey.OP_WRITE | SelectionKey.OP_READ);
            System.out.println("CONNECTED: " + clientChannel.socket().getInetAddress().getHostAddress());
            for (int i=0;i<4;i++){
                if (!registeredID.containsValue(i)){
                    registeredID.put(clientChannel,i);
                    playerState[i] = new PlayerState(i);
                    break;
                }
            }
            unsetCountdown();


        } else {
            throw new RuntimeException("Unknown Channel");
        }
    }
    public void handleRequest(SelectionKey k) throws RuntimeException,IOException{
        if (k.channel() instanceof SocketChannel client) {
            int bytesRead = 0;
            try {
                bytesRead = client.read(buffer);
            } catch (IOException e) {
                handleDisconnect(client);
            }
            if (bytesRead == -1) {
                handleDisconnect(client);
            }
            else if (bytesRead > 0){
                buffer.flip();
                Request clientReq = Request.decodeBytes(buffer.array());
                System.out.println(clientReq);
                String data = new String(buffer.array(), buffer.position(), bytesRead,StandardCharsets.UTF_8);
                //buffer.clear();
                String[] request = data.split(" ");
                switch (clientReq.getOperation()){
                    case ProtocolOperation.USER:

                        try(RequestReader r = new RequestReader(clientReq)){
                            String name = r.readString();
                            String profileURL = r.readString();
                            int startHp = r.readInt();
                            System.out.println("Player "+name+" has joined the game.");
                            playerState[this.registeredID.get(client)].getPlayerInfo().setName(name);
                            playerState[this.registeredID.get(client)].getPlayerInfo().setProfilePicture(profileURL);
                            playerState[this.registeredID.get(client)].getPlayerInfo().setHp(new Constant(startHp));
                            System.out.println(playerState[this.registeredID.get(client)].getPlayerInfo());
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                        Request serverRes = new Request(ProtocolOperation.ACKN);
                        serverRes.appendData(this.registeredID.get(client));
                        buffer.clear().put(serverRes.encodeBytes());
                        buffer.flip();
                        while (buffer.hasRemaining()) {
                            client.write(buffer);
                        }
                        lobbyUpdate();
                        break;
                    case READY:
                        playerState[registeredID.get(client)].toggleReady();
                        int countReady = 0;
                        boolean started = false;
                        for(int i: registeredID.values()){
                            if(playerState[i].isReady()) countReady++;
                            if(playerState[i].isStarted()) started=true;
                        }
                        System.out.println("Ready: " + countReady);
                        if (countReady > 1 && countReady == registeredID.size()){
                            gameStarting = Instant.now().getEpochSecond() + 10;
                        }
                        else if (!started){
                            unsetCountdown();
                        }
                        lobbyUpdate();
                        break;
                    case DECK:
                        File f = File.createTempFile("temp-"+registeredID.get(client),".deck");
                        f.deleteOnExit();
                        try (
                                FileOutputStream fo = new FileOutputStream(f);
                                DataOutputStream out = new DataOutputStream(fo);
                                RequestReader r = new RequestReader(clientReq);
                        ){
                            while (!r.reachTheEnd()){
                                data = r.readString();
                                out.writeBytes(data);
                            }

                            playerState[registeredID.get(client)].setDeckPath(f.getAbsolutePath());
                            serverInfo.updateDeckLoaded(playerState);
                            System.out.println(f.getAbsolutePath());
                            if (serverInfo.isDeckLoaded()){
                                initGame();
                            }
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }

                        break;
                    case CARD:
                        try (RequestReader r = new RequestReader(clientReq)){
                            int cardIndex = r.readInt();
                            Card cardPlayed = Card.decode(r.readByteData());
                            int ownerID = r.readInt();
                            int receiverID = r.readInt();
                            //ArrayList<CardAction> cardActions = cardPlayed.getCardAction(gameState.getPlayers().get(ownerID),gameState.getPlayers().get(receiverID));
                            cardPlayed.action(gameState.getPlayers().get(ownerID),gameState.getPlayers().get(receiverID));
                            gameState.getPlayers().get(ownerID).getDeck().getDispose().add(cardPlayed);
                            gameState.getPlayers().get(ownerID).getHand().remove(cardIndex);
                            ArrayList<Player> playerArrayList = new ArrayList<>();
                            for (PlayerInfo playerInfo:gameState.getPlayers()){
                                playerArrayList.add((Player) playerInfo);
                            }
                            handStateUpdate();
                            ArrayList<Integer> loseList = Player.checkLose(playerArrayList);
                            if(!loseList.isEmpty()){

                                Request gameEnded = new Request(ProtocolOperation.END_GAME);
                                gameEnded.appendData((loseList.getFirst() + 1) % 2);
                                pushUpdate(gameEnded);
                                serverInfo.setPendingClose(true);
                                return;
                            }
//                            Request notifyCardPlayed = new Request(ProtocolOperation.CARD);
//                            notifyCardPlayed.appendData(cardPlayed.encode());
//                            notifyCardPlayed.appendData(ownerID);
//                            notifyCardPlayed.appendData(receiverID);
//                            pushUpdate(notifyCardPlayed);

//                            for (CardAction cardAction : cardActions){
//                                switch (cardAction.getType()){
//                                    case DRAW -> {
//                                        Draw draw = (Draw) cardAction;
//                                        drawCards(draw.getTargetID(),draw.getAmount());
//                                    }
//                                    case SET_HP -> {
//                                        SetHp setHp = (SetHp) cardAction;
//
//                                    }
//                                }
//                            }
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                        break;

                    case END_TURN:
                        if (gameState.getCurrentTurn() == registeredID.get(client)){
                            if (gameState.getCurrentPlayer().getMaxMana() != 10){
                                gameState.getCurrentPlayer().setMaxMana(gameState.getCurrentPlayer().getMaxMana() + 1);
                            }
                            gameState.getCurrentPlayer().setMana(gameState.getCurrentPlayer().getMaxMana());
                            handStateUpdate();
                            gameState.incrementTurn();
                            Request startTurn = new Request(ProtocolOperation.START_TURN);
                            startTurn.appendData(gameState.getCurrentTurn());
                            drawCards(gameState.getCurrentTurn(),1);
                            handStateUpdate();
                            pushUpdate(startTurn);
                        }
                        break;
                }
            }
        } else {
            throw new RuntimeException("Unknown Channel");
        }
    }

    private void handleDisconnect(SocketChannel sc){
        try{
            System.out.println("DISCONNECTED: " + sc.socket().getInetAddress().getHostAddress());
            registeredID.remove(sc);
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initGame(){

        ArrayList<PlayerInfo> playerInfos = new ArrayList<>();
        for (PlayerState ps : playerState) {
            if (ps != null) {
                playerInfos.add(ps.getPlayerInfo());
                //bf.put(from.getPlayerInfo().encodeBytes());
            }
        }
        gameState = new GameState(playerInfos);
        int randomStarter = ((int)(Math.random()*registeredID.size()));
        gameState.setCurrentTurn(randomStarter);
        Request start = new Request(ProtocolOperation.START_GAME);
        start.appendData(randomStarter);
        pushUpdate(start);
        for (PlayerInfo p : gameState.getPlayers()){
            try {
                p.setDeck(Deck.LoadDeckFromPath(playerState[p.getPlayerID()].getDeckPath()));
                p.setCardsInDeck(p.getDeck().getCards().size());
                p.getDeck().shuffle();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        drawCards(randomStarter,4);
        for (int i = 1; i < gameState.getPlayers().size(); i++) {
            drawCards((randomStarter + i) % gameState.getPlayers().size(),5);
        }
        handStateUpdate();
        Request startTurn = new Request(ProtocolOperation.START_TURN);
        startTurn.appendData(randomStarter);
        pushUpdate(startTurn);
    }

    private void drawCards(int playerOrder,int numberOfCards){
        System.out.println("Player " + playerOrder + " draw " + numberOfCards + " cards.");
        ArrayList<Card> cardsDraw = new ArrayList<>();
        for (int i = 0; i < numberOfCards; i++) {
            cardsDraw.add(gameState.getPlayers().get(playerOrder).draw());
            gameState.getPlayers().get(playerOrder).setCardsInHand(gameState.getPlayers().get(playerOrder).getHand().size());
        }
        Request request = new Request(ProtocolOperation.DRAW);
        request.appendData(playerOrder);
        for (Card card:cardsDraw){
            request.appendData(card.encode());
        }
        pushUpdate(request);
    }

    private void setHP(SetHp setHp){
        Request request = new Request(ProtocolOperation.SET_HP);
        request.appendData(setHp.getTargetID());
        request.appendData(setHp.getNewHp().encodedBytes());
        pushUpdate(request);
    }

    private void lobbyUpdate(){
        for(PlayerState to:playerState){
            if(to!= null) {
                Request serverReq = new Request(ProtocolOperation.LOBBY);
                for (PlayerState from : playerState) {
                    if (from != null) {
                        serverReq.appendData(from.getPlayerInfo().encodeBytes());
                        System.out.println(serverReq);
                        //bf.put(from.getPlayerInfo().encodeBytes());
                    }
                    else{
                        serverReq.appendData(new byte[]{});
                    }
                }
                to.getDataOutQueue().add(serverReq);
            }
        }
    }

    private void handStateUpdate(){
        for(PlayerState to:playerState){
            if(to!= null) {
                Request serverReq = new Request(ProtocolOperation.HAND_UPDATE);
                for (PlayerState from : playerState) {
                    if (from != null) {
                        if (from == to){
                            serverReq.appendData(from.getPlayerInfo().encodeBytesIncludeHand());
                        }
                        else{
                            serverReq.appendData(from.getPlayerInfo().encodeBytes());
                        }
                    }
                    else{
                        serverReq.appendData(new byte[]{});
                    }
                }
                to.getDataOutQueue().add(serverReq);
            }
        }
    }

    public void pushUpdate(Request req){
        for(PlayerState ps:playerState){
            if (ps !=null){
                ps.getDataOutQueue().add(req);
                System.out.println(ps.getDataOutQueue());
            }
        }
    }
    public void pushUpdate(Request req,int exclude){
        for(PlayerState ps:playerState){
            if (ps !=null && ps.getPlayerInfo().getPlayerID() != exclude){
                ps.getDataOutQueue().add(req);
            }
        }
    }

    public void sendUpdate(Request req,int receiver){
        for(PlayerState ps:playerState){
            if (ps !=null && ps.getPlayerInfo().getPlayerID() == receiver){
                ps.getDataOutQueue().add(req);
            }
        }
    }

    public void stopServer(){
        serverInfo.setRunning(false);
        serverInfo.setListening(false);
        NIOServer.instance = null;
    }

    public boolean isBound(){
        return serverInfo.isListening();
    }
}
