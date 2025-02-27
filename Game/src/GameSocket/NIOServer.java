package GameSocket;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.time.Instant;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {
    private HashMap<SocketChannel,Integer> registeredID;
    private PlayerState[] playerState;
    private ByteBuffer buffer;
    private long gameStarting;

    public NIOServer() {
        registeredID = new HashMap<SocketChannel,Integer>();
        playerState = new PlayerState[4];
        buffer = ByteBuffer.allocate(1024);
        gameStarting = -1;
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
                        if (k.channel() instanceof ServerSocketChannel serverChannel) {
                            SocketChannel clientChannel = serverChannel.accept();
                            clientChannel.configureBlocking(false);
                            clientChannel.register(selector, SelectionKey.OP_WRITE | SelectionKey.OP_READ);
                            System.out.println("CONNECTED: " + clientChannel.socket().getInetAddress().getHostAddress());
                            for (int i=0;i<4;i++){
                                if (!registeredID.values().contains(i)){
                                    registeredID.put(clientChannel,i);
                                    playerState[i] =new PlayerState(i);
                                    break;
                                }
                            }

                        } else {
                            throw new RuntimeException("Unknown Channel");
                        }
                    }

                    else if (k.isWritable() && k.isReadable()) {
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
                                String data = new String(buffer.array(), buffer.position(), bytesRead);
                                buffer.clear();
                                String[] request = data.split(" ");
                                switch (request[0]){
                                    case "USER":
                                        System.out.println("Player "+request[1]+" has joined the game.");
                                        buffer.put("ACK".getBytes());
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
                                        DataOutputStream out = new DataOutputStream(new FileOutputStream("temp/"+registeredID.get(client)+".dat"));
                                        playerState[registeredID.get(client)].setDeckPath("temp/"+registeredID.get(client)+".dat");
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
                                                data = new String(buffer.array(), buffer.position(), bytesRead);
                                                if (data.equals("END OF DECK")){
                                                    //System.out.println("End");
                                                    break;
                                                }
                                                out.writeBytes(data);
                                            }
                                            bytesRead = client.read(buffer);
                                        }
                                        break;
                                }
                            }
                        } else {
                            throw new RuntimeException("Unknown Channel");
                        }
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
                                buffer.put("SETUP".getBytes());
                                buffer.flip();
                                while (buffer.hasRemaining()) {
                                    client.write(buffer);
                                }
                                playerState[registeredID.get(client)].setStarted(true);
                            }
                            int countStarted = 0;
                            for(int i: registeredID.values()){
                                if(playerState[i].isStarted()) countStarted++;
                            }
                            if (countStarted == registeredID.size()){
                                gameStarting = -1;
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
}
