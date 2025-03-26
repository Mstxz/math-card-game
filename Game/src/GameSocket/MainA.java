package GameSocket;

import Gameplay.Card;
import Gameplay.Cards.AbsoluteCCat;

public class MainA {
    public static void main(String[] args) {
        NIOClient c = new NIOClient();
//        //NIOClient c1 = new NIOClient(2);
//        //c1.pressedReady("assets/a.deck");
        try {
            c.pressedReady();
            c.start();
        } catch (RuntimeException e) {
            e.printStackTrace();
            System.out.println("Connection Out");
        }
//        //c1.lobby();
//        AbsoluteHp a = (AbsoluteHp) Card.createCard("AbsoluteHp");
//        System.out.println(a);
    }
}