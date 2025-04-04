package Gameplay;

import GUI.Component.Game;
import GUI.Setting.Controller.SettingController;
import GUI.Setting.UserPreference;
import Gameplay.Bot.*;
import Gameplay.Numbers.Constant;

import java.util.ArrayList;
import java.util.Arrays;

public class GameForGUI extends Game {
    private boolean paused = false;
    private boolean isRunning = true;
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
            if (!isRunning){
                return;
            }
            Player inPlay = turnOrder.get(i%2);
            observer.onTurnCountChange(i + 1);
            ArrayList<Integer> loseList = Player.checkLose(turnOrder);
            if(!loseList.isEmpty() && turnOrder.get(loseList.getFirst()) == inPlay){
                saveAchievement(turnOrder.get((loseList.getFirst()+1) % 2));
                if (turnOrder.get((playerOrder+1)%2) instanceof Bot){
                    UserPreference.getInstance().getWinStat().setPlay(UserPreference.getInstance().getWinStat().getPlay()+1);
                    if (turnOrder.get((loseList.getFirst()+1) % 2) instanceof Bot){
                        UserPreference.getInstance().getWinStat().setLose(UserPreference.getInstance().getWinStat().getLose()+1);
                    }
                    else if (turnOrder.get((loseList.getFirst()+1) % 2) != null) {
                        UserPreference.getInstance().getWinStat().setWin(UserPreference.getInstance().getWinStat().getWin()+1);
                    }
                    SettingController.updatePreference();
                }

                observer.onGameEnded(turnOrder.get((loseList.getFirst()+1) % 2));
                return;
            }

            inPlay.draw();
            observer.onStatChanged();
            observer.onHandChanged();
            if(inPlay instanceof Bot){
                int targetId = playerOrder;
                Card c;
                try {
                    //System.out.println(Arrays.toString(inPlay.getHand().toArray()));
                    Thread.sleep(500);
                    while ((c = inPlay.play(inPlay,turnOrder.get(targetId))) != null){
                        observer.onCardPlayed(c);
                        loseList = Player.checkLoseHP(turnOrder);
                        if(!loseList.isEmpty()){
                            Player winner;
                            if (loseList.size() == 2){
                                winner = inPlay;
                            }
                            else{
                                winner = turnOrder.get((loseList.getFirst()+1) % 2);
                            }
                            Thread thread = getThread(winner);
                            thread.start();
                            observer.onGameEnded(winner);
                            return;
                        }

                        Thread.sleep(750);
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
                if (!isRunning){
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
            if (maxScore == -1 || maxScore < p.getHp().absolute()){
                winner.clear();
                maxScore = p.getHp().absolute();
                winner.add(p);
            }
            else if (maxScore == p.getHp().absolute()){
                winner.clear();
            }
        }
        winner.add(null);
        if (!(winner.getFirst() instanceof Bot)){
        }
        if (turnOrder.get((playerOrder+1)%2) instanceof Bot){
            UserPreference.getInstance().getWinStat().setPlay(UserPreference.getInstance().getWinStat().getPlay()+1);
            if (winner.getFirst() instanceof Bot){
                UserPreference.getInstance().getWinStat().setLose(UserPreference.getInstance().getWinStat().getLose()+1);
            }
            else if (winner.getFirst() != null) {
                UserPreference.getInstance().getWinStat().setWin(UserPreference.getInstance().getWinStat().getWin()+1);
            }
            SettingController.updatePreference();
        }
        saveAchievement(winner.getFirst());
        observer.onGameEnded(winner.getFirst());
    }

    public void saveAchievement(Player winner){
        if (winner == null || winner instanceof Bot){
            return;
        }
        if (turnOrder.get((playerOrder+1)%2) instanceof Pupr){
            UserPreference.getInstance().getAchievementProfile().isWinPupr = true;
        }
        else if (turnOrder.get((playerOrder+1)%2) instanceof Arsr){
            UserPreference.getInstance().getAchievementProfile().isWinArsr = true;
        }
        else if (turnOrder.get((playerOrder+1)%2) instanceof Who){
            UserPreference.getInstance().getAchievementProfile().isWinWho = true;
        }
        else if (turnOrder.get((playerOrder+1)%2) instanceof Mystyr){
            UserPreference.getInstance().getAchievementProfile().isWinMystyr = true;
        }
        else if (turnOrder.get((playerOrder+1)%2) instanceof OmmThuk) {
            UserPreference.getInstance().getAchievementProfile().isWinOmmThuk = true;
        }
        SettingController.updatePreference();
    }

    @Override
    public boolean isGameEnded(){
        return !Player.checkLoseHP(turnOrder).isEmpty();
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
    public void notifyQuit() {
        this.isRunning = false;
        resumeGame();
    }

    @Override
    public void playerPlay(int cardIndex,Player receiver) {
        Card cardPlayed = getPlayer().getHand().get(cardIndex);
        cardPlayed.action(getPlayer(), receiver);
        getPlayer().getDeck().addDispose(getPlayer().getHand().get(cardIndex));
        getPlayer().getHand().remove(cardIndex);
        observer.onCardPlayed(cardPlayed);
        if (isGameEnded()){
            ArrayList<Integer> loseList = Player.checkLoseHP(turnOrder);

            Player winner;
            if (loseList.size() == 2){
                winner = getPlayer();
            }
            else{
                winner = turnOrder.get((loseList.getFirst()+1) % 2);
            }


            Thread thread = getThread(winner);
            thread.start();
            observer.onGameEnded(winner);
        }



    }

    private Thread getThread(Player winner) {
        Runnable save = () -> {
            saveAchievement(winner);
            if (turnOrder.get((playerOrder+1)%2) instanceof Bot){
                UserPreference.getInstance().getWinStat().setPlay(UserPreference.getInstance().getWinStat().getPlay()+1);
                if (winner instanceof Bot){
                    UserPreference.getInstance().getWinStat().setLose(UserPreference.getInstance().getWinStat().getLose()+1);
                }
                else if (winner != null) {
                    UserPreference.getInstance().getWinStat().setWin(UserPreference.getInstance().getWinStat().getWin()+1);
                }
                SettingController.updatePreference();
            }

        };
        return new Thread(save);
    }
}

