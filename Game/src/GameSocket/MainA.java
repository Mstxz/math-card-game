package GameSocket;

import Gameplay.Card;
import Gameplay.Cards.AbsoluteHp;

public class MainA {
    public static void main(String[] args) {
        NIOClient c = new NIOClient(2);
//        //NIOClient c1 = new NIOClient(2);
//        //c1.pressedReady("assets/a.txt");
        try {
            c.pressedReady("assets/a.txt");
            c.lobby();
        } catch (RuntimeException e) {
            System.out.println("Connection Out");
        }
//        //c1.lobby();
//        AbsoluteHp a = (AbsoluteHp) Card.createCard("AbsoluteHp");
//        System.out.println(a);
    }
}