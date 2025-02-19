package GameSocket;

public class Main {
    public static void main(String[] args) {
        Room r = new Room(2);
        System.out.println(r.getPublicIp());
        //Client c1 = new Client(r.getPrivateIp(),5000);
        //Client c2 = new Client(r.getPrivateIp(),5000);
        try{
            //c1.send("Hello");
            //r.cleanUp();
            //c1.close();
            //c2.close();

        }
        catch (Exception e){
            System.out.println(e);
        }
        //r.getRoomServer().setRunning(false);
    }

}