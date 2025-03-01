package GameSocket;

public class MainA {
    public static void main(String[] args) {
        NIOClient c = new NIOClient(2);
        c.pressedReady("assets/deck.txt");
        c.lobby();
    }
}