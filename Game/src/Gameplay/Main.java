package Gameplay;

import Gameplay.Cards.Minus;
import Gameplay.Cards.Plus;
import Gameplay.Numbers.Constant;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        Player a = new Player("1");
        Player b = new Player("2");
        b.setHp(new Constant(100));
        a.setHp(new Constant(100));
        try {
            a.setDeck(Deck.LoadDeck("a"));
            b.setDeck(Deck.LoadDeck("a"));
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        a.getDeck().shuffle();
        b.getDeck().shuffle();
        for(int i=0;i<5;i++){
            a.draw();
            b.draw();
        }
        for (int i=1;i<3;i++){
            System.out.println("Round "+i);
            System.out.println();
            a.play(a,b);
            b.play(b,a);
            System.out.println();
            a.setMana(a.getMaxMana());
            b.setMana(b.getMaxMana());
        }
    }

}
