package GameSocket;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class PlayerThread extends Thread{
    private Socket s;
    private DataInputStream in;
    private boolean finished = false;
    private boolean waiting = false;
    private String incomingMessage = "";
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
        try {
            // Reads message from client until "Over" is sent
            while (!finished)
            {
                incomingMessage = in.readUTF();
                while (incomingMessage.equals("QUIT")){
                    if(finished){
                        System.out.println("Player Left");
                        break;
                    }
                }

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

    public String getIncomingMessage() {
        return incomingMessage;
    }
}
