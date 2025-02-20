package GameSocket;
import java.io.*;
import java.net.*;

public class Client extends Thread implements AutoCloseable{

    // Initialize socket and input/output streams
    private Socket s = null;
    private DataInputStream in = null;
    private DataOutputStream out = null;

    // Constructor to put IP address and port
    public Client(String addr, int port)
    {
        // Establish a connection
        try {
            s = new Socket(addr, port);
            //System.out.println("Connected");

            // Takes input from terminal
            in = new DataInputStream(System.in);

            // Sends output to the socket
            out = new DataOutputStream(s.getOutputStream());
        }
        catch (IOException i) {
            System.out.println(i);
            return;
        }
    }
    public void send(String message) throws IOException {
        try {
            out.writeUTF(message);

        } catch (IOException e) {
            throw e;
        }
    }

    @Override
    public void run() {
        while (!this.isInterrupted()){
            try {
                String input = in.readUTF();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        Client c = new Client("localhost", 12345);
    }

    @Override
    public void close() throws Exception {
        in.close();
        out.close();
        s.close();
    }
}
