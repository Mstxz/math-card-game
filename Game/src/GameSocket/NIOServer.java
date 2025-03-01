package GameSocket;

import Gameplay.Numbers.Constant;

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
                            if(epochSecond <= gameStarting){
                                buffer.clear();
                                buffer.putLong(gameStarting-epochSecond);
                                buffer.flip();
                                while (buffer.hasRemaining()) {
                                    client.write(buffer);
                                }
                            }
                            else if(!playerState[registeredID.get(client)].isStarted()){
                                buffer.clear();
                                buffer.put("SETUP".getBytes(StandardCharsets.UTF_8));
                                buffer.flip();
                                while (buffer.hasRemaining()) {
                                    client.write(buffer);
                                }
                                playerState[registeredID.get(client)].setStarted(true);
                            }

                            info.updateGameStarted(playerState);
                            if (info.isGameStarted()){
                                gameStarting = -1;
                            }
                        }
                        byte[] bytesOut = playerState[registeredID.get(client)].getDataOutQueue().poll();
                        if (bytesOut != null){
                            buffer.clear();
                            buffer.put(bytesOut);
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
                Socket socket = client.socket();
                System.out.println("DISCONNECTED: " + socket.getInetAddress().getHostAddress());
                registeredID.remove(client);
                client.close();
            }
            if (bytesRead == -1) {
                Socket socket = client.socket();
                System.out.println("DISCONNECTED: " + socket.getInetAddress().getHostAddress());
                registeredID.remove(client);
                client.close();
            }
            else if (bytesRead > 0){
                buffer.flip();
                String data = new String(buffer.array(), buffer.position(), bytesRead,StandardCharsets.UTF_8);
                buffer.clear();
                String[] request = data.split(" ");
                switch (request[0]){
                    case "USER":
                        System.out.println("Player "+request[1]+" has joined the game.");
                        playerState[this.registeredID.get(client)].getPlayerInfo().setName(request[1]);
                        playerState[this.registeredID.get(client)].getPlayerInfo().setProfilePicture(request[2]);
                        playerState[this.registeredID.get(client)].getPlayerInfo().setHp(new Constant(Integer.parseInt(request[3])));
                        System.out.println(playerState[this.registeredID.get(client)].getPlayerInfo());
                        buffer.put("ACK".getBytes(StandardCharsets.UTF_8));
                        buffer.flip();
                        while (buffer.hasRemaining()) {
                            client.write(buffer);
                        }
                        break;
                    case "READY":
                        playerState[registeredID.get(client)].toggleReady();
                        int countReady = 0;
                        boolean started = false;
                        for(int i: registeredID.values()){
                            if(playerState[i].isReady()) countReady++;
                            if(playerState[i].isStarted()) started=true;
                        }
                        if (countReady == registeredID.size()){
                            gameStarting = Instant.now().getEpochSecond() + 10;
                        }
                        else if (!started){
                            gameStarting = -1;
                        }
                        break;
                    case "DECK":
                        File f = File.createTempFile("temp",".dat");
                        f.deleteOnExit();
                        try (
                                FileOutputStream fo = new FileOutputStream(f);
                                DataOutputStream out = new DataOutputStream(fo)
                        ){
                            bytesRead = client.read(buffer);
                            while (true){
                                if (bytesRead == -1){
                                    Socket socket = client.socket();
                                    System.out.println("DISCONNECTED: " + socket.getInetAddress().getHostAddress());
                                    registeredID.remove(client);
                                    client.close();
                                    break;
                                }
                                else if (bytesRead > 0){
                                    buffer.flip();
                                    data = new String(buffer.array(), buffer.position(), bytesRead,StandardCharsets.UTF_8);
                                    if (data.equals("END OF DECK")){
                                        //System.out.println("End");
                                        break;
                                    }
                                    out.writeBytes(data);
                                }
                                bytesRead = client.read(buffer);
                            }
                            playerState[registeredID.get(client)].setDeckPath(f.getAbsolutePath());
//                            try(BufferedReader frr = new BufferedReader(new FileReader(f.getAbsolutePath()));){
//                                String line;
//                                while ((line = frr.readLine()) != null){
//                                    System.out.println(line);
//                                }
//                            } catch (FileNotFoundException e) {
//                                throw new RuntimeException(e);
//                            } catch (IOException e) {
//                                throw new RuntimeException(e);
//                            }
//                            System.out.println(f.getAbsolutePath());
                            info.updateDeckLoaded(playerState);
                            if (info.isDeckLoaded()){
                                pushUpdate("LOBBY");
                                ByteBuffer bf = ByteBuffer.allocate(1024);
                                for(PlayerState to:playerState){
                                    if(to!= null) {
                                        for (PlayerState from : playerState) {
                                            if (from != null) {
                                                bf.put(from.getPlayerInfo().encodeBytes());
                                            }
                                        }
                                        to.getDataOutQueue().add(bf.array());
                                    }

                                }
                                pushUpdate("END");
                            }
                        }
                        break;
                    case "PLAY":
                        pushUpdate("PLAY ",registeredID.get(k));
                }
            }
        } else {
            throw new RuntimeException("Unknown Channel");
        }
    }
    public void pushUpdate(String str){
        for(PlayerState ps:playerState){
            if (ps !=null){
                ps.getDataOutQueue().add(str.getBytes(StandardCharsets.UTF_8));
            }
        }
    }
    public void pushUpdate(byte[] bytes){
        for(PlayerState ps:playerState){
            if (ps !=null){
                ps.getDataOutQueue().add(bytes);
            }

        }
    }
    public void pushUpdate(String str,int exclude){
        for(PlayerState ps:playerState){
            if (ps !=null && ps.getPlayerInfo().getPlayerID() != exclude){
                ps.getDataOutQueue().add(str.getBytes(StandardCharsets.UTF_8));
            }
        }
    }
}
