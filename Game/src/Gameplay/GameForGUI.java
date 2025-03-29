package Gameplay;

import GUI.Component.Game;
import Gameplay.Bot.Bot;
import Gameplay.Numbers.Constant;

import java.util.ArrayList;
import java.util.Arrays;

public class GameForGUI extends Game {
    private boolean paused = false;
    public GameForGUI(ArrayList<Player> players){
        int index = ((int)(Math.random()*2));
        playerOrder = index;
        this.turnOrder = players;
        if (index == 1){
            players.getFirst().setPlayerNumber(index);
            players.getLast().setPlayerNumber(0);
            this.turnOrder = new ArrayList<>(this.turnOrder.reversed());

        }
        else{
            players.getFirst().setPlayerNumber(index);
            players.getLast().setPlayerNumber(1);
        }
        //System.out.println(Arrays.toString(getPlayer().getDeck().getCards().toArray()));
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
            observer.onTurnCountChange(i + 1);
            Player inPlay = turnOrder.get(i%2);
            inPlay.draw();
            observer.onHandChanged();
            if(inPlay instanceof Bot){
                int targetId = playerOrder;
                Card c;
                try {
                    System.out.println(Arrays.toString(inPlay.getHand().toArray()));
                    Thread.sleep(500);
                    while ((c = inPlay.play(inPlay,turnOrder.get(targetId))) != null){
                        observer.onCardPlayed();
                        ArrayList<Integer> loseList = Player.checkLose(turnOrder);
                        if(!loseList.isEmpty()){
                            observer.onGameEnded(turnOrder.get((loseList.getFirst()+1) % 2));
                            return;
                        }

                        Thread.sleep(750);
                        System.out.println(c.getName());
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            else{
                observer.onHandChanged();
                observer.onTurnArrive();
                this.waitForGUI();
                this.checkPause();
                ArrayList<Integer> loseList = Player.checkLose(turnOrder);
                if(!loseList.isEmpty()){
                    observer.onGameEnded(turnOrder.get((loseList.getFirst()+1) % 2));
                    return;
                }
            }

            if (inPlay.getMaxMana()<10){
                inPlay.setMaxMana(inPlay.getMaxMana()+1);
            }
            inPlay.setMana(inPlay.getMaxMana());
            observer.onTurnEnded();
        }
        ArrayList<Player> winner = new ArrayList<>();
        int maxScore = -1;
        for (Player p:turnOrder){
            if (maxScore == -1 || maxScore > p.getHp().absolute()){
                winner.clear();
                maxScore = p.getHp().absolute();
                winner.add(p);
            }
            else if (maxScore == p.getHp().absolute()){
                winner.clear();
            }
        }
        winner.add(null);
        observer.onGameEnded(winner.getFirst());
    }



    @Override
    public boolean isGameEnded(){
        return !Player.checkLose(turnOrder).isEmpty();
    }

    @Override
    public void notifyGameStart() {
        Player a = turnOrder.getFirst();
        Player b = turnOrder.getLast();

        b.setHp(new Constant(100));
        a.setHp(new Constant(100));

        a.getDeck().shuffle();
        b.getDeck().shuffle();
        for(int i=0;i<4;i++){
            a.draw();
            b.draw();
        }
        b.draw();
        observer.onGameStart(0);
    }

    @Override
    public void notifyEndTurn() {
        resumeGame();
    }

    @Override
    public void playerPlay(int cardIndex,Player receiver) {
        Card cardPlayed = getPlayer().getHand().get(cardIndex);
        cardPlayed.action(getPlayer(), receiver);
        getPlayer().getDeck().getDispose().add(getPlayer().getHand().get(cardIndex));
        getPlayer().getHand().remove(cardIndex);
        observer.onCardPlayed();
    }
}



//public class GameForGUI extends Thread {
//    private Player self;
//    //private Bot enemy = new Bot();
//    private Player enemy;
//    private boolean paused = false;
//    private int selfNumber;
//    private Player[] sequencePlayer = new Player[2];
//    private AvengerAssembleGUI gui;
//    public GameForGUI(Player self,Player enemy,AvengerAssembleGUI gui){
//        int index = ((int)(Math.random()*2));
//        sequencePlayer[index] = self;
//        selfNumber = index;
//        self.setPlayerNumber(index);
//        //cardGui = new CardGameGUI(a.getHand(),a,b);
//        if (index == 0) {
//            sequencePlayer[1] = enemy;
//            enemy.setPlayerNumber(1);
//        }
//        else {
//            sequencePlayer[0] = enemy;
//            enemy.setPlayerNumber(0);
//        }
//        this.gui = gui;
//    }
//
//    public void waitForGUI(){
//        paused = true;
//    }
//
//    public synchronized void resumeGame(){
//        paused = false;
//        this.notify();
//    }
//
//    public synchronized void checkPause(){
//
//        while(paused){
//            try{
//                this.wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    @Override
//    public void run(){
//        for (int i = 0 ; i<2*20; i++){
//            Player inPlay = sequencePlayer[i%2];
//            inPlay.draw();
//            gui.getUserPanel().updatePlayable(gui.getEnemy());
//            //System.out.println("Before re-render: "+inPlay.getHand().size());
//            gui.updatePlayerHUD();
//            gui.initCard();
//            //System.out.println("After re-render: "+inPlay.getHand().size());
//            if(Player.checkWin(sequencePlayer[0],sequencePlayer[1]) != null){
//                gui.result(Player.checkWin(sequencePlayer[0],sequencePlayer[1]));
//                return;
//            }
//            if(inPlay instanceof Bot){
//                int targetId = ((int)(Math.random()*2));
//                while (targetId == i % 2){
//                    targetId = ((int)(Math.random()*2));
//                }
//                Card c;
//                try {
//                    Thread.sleep(500);
//                    while ((c = inPlay.play(inPlay,sequencePlayer[targetId])) != null){
//                        gui.updatePlayerHUD();
//                        gui.initCard();
//                        Thread.sleep(750);
//                        System.out.println(c.getName());
//                    }
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//            else{
//                gui.getUserPanel().updatePlayable(gui.getEnemy());
//                gui.updatePlayerHUD();
//                gui.initCard();
//                gui.setPlayerTurn(true);
//                this.waitForGUI();
//                this.checkPause();
//            }
//            if (inPlay.getMaxMana()<10){
//                inPlay.setMaxMana(inPlay.getMaxMana()+1);
//            }
//            inPlay.setMana(inPlay.getMaxMana());
//            gui.getUserPanel().updatePlayable(gui.getEnemy());
//            gui.updatePlayerHUD();
//            gui.initCard();
//        }
//        gui.result(null);
//    }
//
//    public int getSelfNumber() {
//        return selfNumber;
//    }
//
//    public void setSelfNumber(int selfNumber) {
//        this.selfNumber = selfNumber;
//    }
//
//    public void setGame(){
//        Player a = sequencePlayer[0];
//        Player b = sequencePlayer[1];
//
//        try {
//            a.setDeck(Deck.LoadDeck("a"));
//            b.setDeck(Deck.LoadDeck("a"));
//        }
//        catch (FileNotFoundException e){
//            e.printStackTrace();
//        }
//
//        b.setHp(new Constant(100));
//        a.setHp(new Constant(100));
//
//        a.getDeck().shuffle();
//        b.getDeck().shuffle();
//        for(int i=0;i<4;i++){
//            a.draw();
//            b.draw();
//        }
//        b.draw();
//    }
//}

