package Gameplay;

import Gameplay.Cards.Minus;
import Gameplay.Cards.Plus;
import Gameplay.Numbers.Constant;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        Player a = new Player("1");
        Player b = new Player("2");
        b.setHp(new Constant(-100));
        a.setHp(new Constant(-100));
        a.setMana(0);
        b.setMana(0);
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
        for (int i=3;i>0;i--){
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
