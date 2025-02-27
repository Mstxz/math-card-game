package GameSocket;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Vector;


public class NIOClient {
    private ByteBuffer buffer = ByteBuffer.allocate(1024);
    private SocketChannel channel;
    private ClientState currentState;
    private String deckPath;
    Vector<String> events;
    public NIOClient(){
        this.events = new Vector<String>();
        this.currentState = ClientState.IDLE;


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
    public void lobby() {
        try {
            while (currentState == ClientState.IDLE || currentState == ClientState.READY) {
                buffer.clear();
                int byteRead = channel.read(buffer);
                if (byteRead > 0){
                    buffer.flip();
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
                            buffer.clear();
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