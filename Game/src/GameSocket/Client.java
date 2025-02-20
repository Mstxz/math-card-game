package GameSocket;
import java.io.*;
import java.net.*;

public class Client extends Thread implements AutoCloseable{

    // Initialize socket and input/output streams
    private Socket s = null;
    private DataInputStream in = null;
    private DataOutputStream out = null;

    private GameStatus status;

    // Constructor to put IP address and port
    public Client(String addr, int port, GameStatus status)
    {
        this.status = status;
        // Establish a connection
        try {
            s = new Socket(addr, port);
            //System.out.println("Connected");

            // Takes input from terminal
            in = new DataInputStream(s.getInputStream());

            // Sends output to the socket
            out = new DataOutputStream(s.getOutputStream());
        }
        catch (IOException i) {
            i.printStackTrace();
            return;
        }
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public void send(String message) throws IOException {
        try {
            out.writeUTF(message);


        } catch (IOException e) {
            throw e;
        }
    }

    public void upload(File f) throws IOException {
        try(FileInputStream fs = new FileInputStream(f)){
            int count;
            byte[] buffer = new byte[8192]; // or 4096, or more
            while ((count = in.read(buffer)) > 0)
            {
                out.write(buffer, 0, count);
            }
        }
    }

    @Override
    public void run() {
        while (true){
            try {
                String input = in.readUTF();
                switch (input){
                    case "YOUR_TURN":
                        status.setTurn(true);
                        status.setUpdateAvailable(true);
                        break;
                    case "CARD":
                }
            } catch (IOException e) {

                throw new RuntimeException(e);
            }
        }
    }


    @Override
    public void close() throws Exception {
        in.close();
        out.close();
        s.close();
    }
}
