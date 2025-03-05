package Gameplay;

import GUI.Page.AvengerAssembleGUI;
import Gameplay.Numbers.Constant;

import java.io.FileNotFoundException;

public class GameForGUI {
    private Player self;
    private Bot enemy = new Bot();
    private int selfNumber;
    private Player[] sequencePlayer = new Player[2];
    private int count = 0;
    private boolean isBotTurn = false;
    private AvengerAssembleGUI gui;
    public GameForGUI(Player self){
        int index = ((int)(Math.random()*2));
        sequencePlayer[index] = self;
        selfNumber = index;
        //cardGui = new CardGameGUI(a.getHand(),a,b);
        if (index == 0) {
            sequencePlayer[1] = enemy;
        }
        else {
            sequencePlayer[0] = enemy;
        }
    }

    public void play(){
        Player a = sequencePlayer[0];
        Player b = sequencePlayer[1];
        b.setHp(new Constant(100));
        a.setHp(new Constant(100));

        a.setMaxMana(10);
        b.setMaxMana(10);
        a.setMana(10);
        b.setMana(10);

        System.out.println("Welcome to the game!");
        try {
            a.setDeck(Deck.LoadDeck("20Minus"));
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
        while (!Player.checkWin(self,enemy)){
            if (!gui.isPlayerTurn()){
                enemy.play(enemy,self);
                gui.setPlayerTurn(true);
                this.count += 1;
            }
        }
    }

    public boolean isBotTurn(){
        return isBotTurn;
    }

    public void setCount(int count){
        this.count = count;
    }

    public int getCount(){
        return this.count;
    }
}
