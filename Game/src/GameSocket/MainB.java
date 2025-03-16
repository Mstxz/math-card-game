package GameSocket;

public class MainB {
    public static void main(String[] args) {
        NIOClient c = new NIOClient(2);
//        //NIOClient c1 = new NIOClient(2);
//        //c1.pressedReady("assets/a.deck");
        try {
            c.pressedReady("assets/a.txt");
            c.lobby();
        } catch (RuntimeException e) {
            e.printStackTrace();
            System.out.println("Connection Out");
        }
//        //c1.lobby();
//        AbsoluteHp a = (AbsoluteHp) Card.createCard("AbsoluteHp");
//        System.out.println(a);
    }
}