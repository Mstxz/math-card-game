package GameSocket;

public class Main {
    public static void main(String[] args) {
        OnlineGame r = new OnlineGame(4);
        r.start();
//        System.out.println(r.getPrivateIp());
//        Client c1 = new Client(r.getPrivateIp(),5000);
//        try{
//            c1.send("QUIT");
//            //r.cleanUp();
//            c1.close();
//            //c2.close();
//
//        }
//        catch (Exception e){
//            System.out.println(e);
//        }
        //r.getRoomServer().setRunning(false);
    }

}