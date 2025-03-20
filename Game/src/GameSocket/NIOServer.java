package GameSocket;

import Gameplay.Numbers.Constant;
import Gameplay.Player;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {
    private HashMap<SocketChannel,Integer> registeredID;
    private PlayerState[] playerState;
    private ByteBuffer buffer;
    private long gameStarting;
    private ServerInfo info;
    public NIOServer() {
        registeredID = new HashMap<SocketChannel,Integer>();
        playerState = new PlayerState[4];
        buffer = ByteBuffer.allocate(1024);
        gameStarting = -1;
        info = new ServerInfo();
    }

    public void unsetCountdown(){
        gameStarting = -1;
        for (int i=0;i<4;i++){
            if (registeredID.containsValue(i)){
                playerState[i].setCountDown(10);
            }
        }
    }

    public void start() {
        try (ServerSocketChannel server = ServerSocketChannel.open()) {
            server.bind(new InetSocketAddress(5000));
            server.configureBlocking(false);
            Selector selector = Selector.open();
            server.register(selector, SelectionKey.OP_ACCEPT);
            while (true) {
                try {
                    selector.select();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Set<SelectionKey> keys = selector.selectedKeys();
                for (Iterator<SelectionKey> it = keys.iterator(); it.hasNext(); ) {
                    SelectionKey k = it.next();
                    if (k.isAcceptable()) {
                        handleAcception(selector,k);
                    }
                    else if (k.isWritable() && k.isReadable()) {
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
                                    serverReq.setData(String.valueOf(gameStarting-epochSecond));
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

                            info.updateGameStarted(playerState);
                            if (info.isGameStarted()){
                                gameStarting = -1;
                            }
                        }
                        Request bytesOut = playerState[registeredID.get(client)].getDataOutQueue().poll();
                        if (bytesOut != null){
                            //System.out.println(bytesOut.length);

                            buffer.clear();
                            buffer.put(bytesOut.encodeBytes());
                            buffer.flip();
                            while (buffer.hasRemaining()) {
                                client.write(buffer);
                            }
                        }
                    }
                    buffer.clear();
                    it.remove();
                }
            }
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
                if (!registeredID.values().contains(i)){
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
                        String userData = new String(clientReq.getData(), 0, clientReq.getBytesLength(),StandardCharsets.UTF_8);
                        System.out.println(userData);
                        request = userData.split(" ");
                        System.out.println("Player "+request[0]+" has joined the game.");
                        playerState[this.registeredID.get(client)].getPlayerInfo().setName(request[0]);
                        playerState[this.registeredID.get(client)].getPlayerInfo().setProfilePicture(request[1]);
                        playerState[this.registeredID.get(client)].getPlayerInfo().setHp(new Constant(Integer.parseInt(request[2])));
                        System.out.println(playerState[this.registeredID.get(client)].getPlayerInfo());
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
                        if (countReady == registeredID.size()){
                            gameStarting = Instant.now().getEpochSecond() + 10;
                        }
                        else if (!started){
                            gameStarting = -1;
                        }
                        break;
                    case DECK:
                        File f = File.createTempFile("temp",".dat");
                        f.deleteOnExit();
                        try (
                                FileOutputStream fo = new FileOutputStream(f);
                                DataOutputStream out = new DataOutputStream(fo)
                        ){
                            data = new String(clientReq.getData(),0,clientReq.getBytesLength(),StandardCharsets.UTF_8);
                            out.writeBytes(data);
                            playerState[registeredID.get(client)].setDeckPath(f.getAbsolutePath());
                            info.updateDeckLoaded(playerState);
                            if (info.isDeckLoaded()){
                                lobbyUpdate();
                            }
                        }
                        break;
                    case CARD:
                        Request req = new Request(ProtocolOperation.CARD);
                        pushUpdate(req,registeredID.get(client));
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

    private void lobbyUpdate(){

//        pushUpdate("LOBBY");
//        ByteBuffer bf = ByteBuffer.allocate(1024);
        for(PlayerState to:playerState){
            if(to!= null) {
                Request serverReq = new Request(ProtocolOperation.LOBBY);
                for (PlayerState from : playerState) {
                    if (from != null) {
                        serverReq.appendData(from.getPlayerInfo().encodeBytes());
                        System.out.println(serverReq);
                        //bf.put(from.getPlayerInfo().encodeBytes());
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
}
