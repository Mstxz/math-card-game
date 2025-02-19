package GameSocket;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class PlayerThread extends Thread{
    private Socket s;
    private DataInputStream in;
    private boolean finished = false;
    PlayerThread(Socket s) throws IOException {
        this.s = s;
        try{
            in = new DataInputStream(s.getInputStream());
        }
        catch (IOException e){
            throw e;
        }
    }
    @Override
    public void run(){
        String m = "";
        try {
            // Reads message from client until "Over" is sent
            while (!m.equals("Over"))
            {
                m = in.readUTF();
                System.out.println(m);
            }
            this.close();
        } catch (IOException e) {
            System.out.println("Player Left");
        }
        finally {
            finished = true;
        }
    }
    public void close() throws IOException {
        s.close();
        in.close();
    }

    public boolean isFinished() {
        return finished;
    }
    public void setFinished(boolean isFinished) {
        finished = isFinished;
    }
}
