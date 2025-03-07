package Gameplay;

import GUI.Page.AvengerAssembleGUI;
import Gameplay.Numbers.Constant;

import java.io.FileNotFoundException;

public class GameForGUI extends Thread {
    private Player self;
    //private Bot enemy = new Bot();
    private Player enemy;
    private int selfNumber;
    private Player[] sequencePlayer = new Player[2];
    private int count = 0;
    private boolean isBotTurn = false;
    private AvengerAssembleGUI gui;
    public GameForGUI(Player self,Player enemy,AvengerAssembleGUI gui){
        int index = ((int)(Math.random()*2));
        sequencePlayer[index] = self;
        selfNumber = index;
        //cardGui = new CardGameGUI(a.getHand(),a,b);
        if (index == 0) {
            sequencePlayer[1] = enemy;
            enemy.setPlayerNumber(1);
        }
        else {
            sequencePlayer[0] = enemy;
            enemy.setPlayerNumber(0);
        }
        this.gui = gui;
    }

    @Override
    public void run(){
        for (int i = 0 ; i<2*20; i++){
            Player inPlay = sequencePlayer[i%2];
            inPlay.draw();
            //System.out.println("Before re-render: "+inPlay.getHand().size());
            System.out.println("LOOP");
            gui.updatePlayerHUD();
            gui.initCard();
            //System.out.println("After re-render: "+inPlay.getHand().size());
            if(Player.checkWin(sequencePlayer[0],sequencePlayer[1])){
                System.out.println("Win");
                return;
            }
            if(inPlay instanceof Bot){
                int targetId = ((int)(Math.random()*2));
                while (targetId == i % 2){
                    targetId = ((int)(Math.random()*2));
                }
                Card c;
                while ((c = inPlay.play(inPlay,sequencePlayer[targetId])) != null){
                        System.out.println(c);
                    continue;
                }
            }
            else{
                gui.setPlayerTurn(true);
                while (gui.isPlayerTurn()){
                    //continue;
                    System.out.println("Waiting for player input:"+gui.isPlayerTurn());
                }
                System.out.println("End Player Turn");
            }
            if (inPlay.getMaxMana()<10){
                inPlay.setMaxMana(inPlay.getMaxMana()+1);
            }
            inPlay.setMana(inPlay.getMaxMana());
            gui.updatePlayerHUD();
            gui.initCard();
        }
        System.out.println("Round out");
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

    public int getSelfNumber() {
        return selfNumber;
    }

    public void setSelfNumber(int selfNumber) {
        this.selfNumber = selfNumber;
    }

    public void setGame(){
        Player a = sequencePlayer[0];
        Player b = sequencePlayer[1];

        try {
            a.setDeck(Deck.LoadDeck("a"));
            b.setDeck(Deck.LoadDeck("a"));
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }

        b.setHp(new Constant(100));
        a.setHp(new Constant(100));

        a.getDeck().shuffle();
        b.getDeck().shuffle();
        for(int i=0;i<4;i++){
            a.draw();
            b.draw();
        }
        b.draw();
    }
}
