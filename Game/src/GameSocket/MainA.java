package GameSocket;

public class MainA {
    public static void main(String[] args) {
        NIOClient c = new NIOClient(2);
        //NIOClient c1 = new NIOClient(2);
        c.pressedReady("assets/20Minus.txt");
        //c1.pressedReady("assets/a.txt");
        c.lobby();
        //c1.lobby();
    }
}