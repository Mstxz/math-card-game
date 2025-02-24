package GameSocket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Vector;

public class PlayerThread extends Thread{
    private Socket s;
    private DataInputStream in;
    private DataOutputStream out;
    private boolean finished = false;
    private String incomingMessage = "";
    private Vector<PlayerEvent> ev;
    private int index = 0;
    PlayerThread(int i,Socket s,Vector<PlayerEvent> ev) throws IOException {
        this.index = i;
        this.s = s;
        this.ev = ev;
        try{
            in = new DataInputStream(s.getInputStream());
            out = new DataOutputStream(s.getOutputStream());
        }
        catch (IOException e){
            throw e;
        }
    }
    @Override
    public void run(){
        try {
            while (!finished)
            {
                //System.out.println(this.isInterrupted());
                if(this.isInterrupted()){
                    System.out.println("Player Left");
                    this.close();
                    return;
                }
                incomingMessage = in.readUTF();
                System.out.println("Message: "+incomingMessage);
//                while (incomingMessage.equals("QUIT")){
//                    if(finished){
//                        System.out.println("Player Left");
//                        break;
//                    }
//                }
                if (incomingMessage.equals("")){
                    continue;
                }
                String[] m = incomingMessage.split(" ");
                switch (m[0]){
                    case "QUIT":
                        this.ev.add(new PlayerEvent(index,PlayerEventType.QUIT));
                        break;
                    case "END_TURN":
                        this.ev.add(new PlayerEvent(index,PlayerEventType.END_TURN));
                        break;
                    case "DRAW":
                        this.ev.add(new PlayerEvent(index,PlayerEventType.DRAW));
                        break;
                    case "PLAY":
                        this.ev.add(new PlayerEvent(index,PlayerEventType.PLAY,m[1]));
                        break;
                    case "NAME":
                        this.ev.add(new PlayerEvent(index,PlayerEventType.REGISTER_NAME,m[1]));
                        break;
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
    public void send(String message) throws IOException {
        try {
            out.writeUTF(message);

        } catch (IOException e) {
            throw e;
        }
    }

    public void close() throws IOException {
        s.close();
        in.close();
    }

    public boolean getFinished() {
        return finished;
    }

    public void setFinished(boolean isFinished) {
        finished = isFinished;
    }

    public String getIncomingMessage() {
        return incomingMessage;
    }
}
