package GameSocket;

public class Main {
    public static void main(String[] args) {
        Room r = new Room(2);
        Client c1 = new Client("127.0.0.1",5000);
        Client c2 = new Client("127.0.0.1",5000);

        try{
            c1.send("Hello");
            c1.send("Over");
            c2.send("Over");
            c1.close();
            c2.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
        finally {
            r.getRoomServer().setRunning(false);
        }
        //r.getRoomServer().setRunning(false);
    }

}