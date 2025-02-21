package Gameplay;

import GUI.GUI;
import Gameplay.Numbers.Constant;

import java.io.FileNotFoundException;

public class Game {
    public static Player[] players = new Player[2];

    public Game(Player a,Player b){
        int index = ((int)(Math.random()*2));
        players[index] = a;
        a.setPlayerNumber(index);
        if (index == 0) {
            players[1] = b;
            b.setPlayerNumber(1);
        }
        else {
            players[0] = b;
            b.setPlayerNumber(0);
        }
    }

    public static Player getPlayer(int index){
        if (index!=0 && index!=1){
            return null;
        }
        return players[index];
    }

    public void Play() {
        Player a = players[0];
        Player b = players[1];
        b.setHp(new Constant(100));
        a.setHp(new Constant(100));
        System.out.println("Welcome to the game!");
        try {
            a.setDeck(Deck.LoadDeck("a"));
            b.setDeck(Deck.LoadDeck("a"));
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        a.getDeck().shuffle();
        b.getDeck().shuffle();
        for(int i=0;i<4;i++){
            a.draw();
            b.draw();
        }
        b.draw();
        for (int i=1;i<=10;i++){
            System.out.println("Round "+i);
            System.out.println();
            a.play(a,b);
            if (Player.checkWinNonPrint(a,b)){
                break;
            }
            b.play(b,a);
            System.out.println();
            if (Player.checkWinNonPrint(a,b)){
                break;
            }
            a.setMana(a.getMaxMana());
            b.setMana(b.getMaxMana());
        }
    }
}
