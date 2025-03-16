package GameSocket;

public class Main {
    public static void main(String[] args) {
        NIOServer s = new NIOServer(2);
        s.start();
    }
}