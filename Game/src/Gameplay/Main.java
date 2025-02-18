package Gameplay;

import Gameplay.Cards.Minus;
import Gameplay.Cards.Plus;

public class Main {
    public static void main(String[] args) {
        Player a = new Player("1");
        Player b = new Player("2");
        for (int i=1;i<=9;i++) {
            a.getDeck().addCard(new Plus(i));
            b.getDeck().addCard(new Minus(i));
        }
        a.getDeck().shuffle();
        b.getDeck().shuffle();
        for(int i=0;i<5;i++){
            a.draw();
            b.draw();
        }
        for (int i=5;i>0;i--){
            System.out.println("Round "+i);
            System.out.println(a.getName()+"'s hp : "+a.getHp());
            System.out.println(a.getName()+"'s mana : "+a.getMana());
            System.out.println(b.getName()+"'s hp : "+b.getHp());
            System.out.println(a.getName()+"'s mana : "+b.getMana());
            System.out.println();
            a.showCard();
            a.draw();
            a.play(a,b);
            System.out.println();
            System.out.println(a.getName()+"'s hp : "+a.getHp());
            System.out.println(a.getName()+"'s mana : "+a.getMana());
            System.out.println(b.getName()+"'s hp : "+b.getHp());
            System.out.println(a.getName()+"'s mana : "+b.getMana());
            System.out.println();
            b.showCard();
            b.draw();
            b.play(b,a);
            System.out.println();
        }
    }

}
