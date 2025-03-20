package Gameplay;

import GUI.Page.AvengerAssembleGUI;
import Gameplay.Numbers.Constant;

import java.io.FileNotFoundException;

public class GameForGUI extends Thread {
    private Player self;
    //private Bot enemy = new Bot();
    private Player enemy;
    private boolean paused = false;
    private int selfNumber;
    private Player[] sequencePlayer = new Player[2];
    private int count = 0;
    private boolean isBotTurn = false;
    private AvengerAssembleGUI gui;
    public GameForGUI(Player self,Player enemy,AvengerAssembleGUI gui){
        int index = ((int)(Math.random()*2));
        sequencePlayer[index] = self;
        selfNumber = index;
        self.setPlayerNumber(index);
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

    public void waitForGUI(){
        paused = true;
    }

    public synchronized void resumeGame(){
        paused = false;
        this.notify();
    }

    public synchronized void checkPause(){

        while(paused){
            try{
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run(){
        for (int i = 0 ; i<2*20; i++){
            Player inPlay = sequencePlayer[i%2];
            inPlay.draw();
            gui.getUserPanel().updatePlayable(gui.getEnemy());
            //System.out.println("Before re-render: "+inPlay.getHand().size());
            gui.updatePlayerHUD();
            gui.initCard();
            //System.out.println("After re-render: "+inPlay.getHand().size());
            if(Player.checkWin(sequencePlayer[0],sequencePlayer[1]) != null){
                gui.result(Player.checkWin(sequencePlayer[0],sequencePlayer[1]));
                return;
            }
            if(inPlay instanceof Bot){
                int targetId = ((int)(Math.random()*2));
                while (targetId == i % 2){
                    targetId = ((int)(Math.random()*2));
                }
                Card c;
                try {
                    Thread.sleep(500);
                    while ((c = inPlay.play(inPlay,sequencePlayer[targetId])) != null){
                        gui.updatePlayerHUD();
                        gui.initCard();
                        Thread.sleep(2000);
                        System.out.println(c.getName());
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            else{
                gui.getUserPanel().updatePlayable(gui.getEnemy());
                gui.updatePlayerHUD();
                gui.initCard();
                gui.setPlayerTurn(true);
                this.waitForGUI();
                this.checkPause();
            }
            if (inPlay.getMaxMana()<10){
                inPlay.setMaxMana(inPlay.getMaxMana()+1);
            }
            inPlay.setMana(inPlay.getMaxMana());
            gui.getUserPanel().updatePlayable(gui.getEnemy());
            gui.updatePlayerHUD();
            gui.initCard();
        }
        gui.result(null);
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
