package GameSocket;
import java.io.*;
import java.net.*;

public class Client implements AutoCloseable{

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
    public static void main(String[] args) {
        Client c = new Client("127.0.0.1", 5000);
    }

    @Override
    public void close() throws Exception {
        in.close();
        out.close();
        s.close();
    }
}
