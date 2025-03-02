package GameSocket;

public class MainA {
    public static void main(String[] args) {
        NIOClient c = new NIOClient(2);
        //NIOClient c1 = new NIOClient(2);
        //c1.pressedReady("assets/a.txt");
        try {
            c.lobby();
        } catch (RuntimeException e) {
            System.out.println("Connetion Out");
        }
        //c1.lobby();
    }
}