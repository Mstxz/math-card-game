package GUI.Component;

import Gameplay.Card;
import Gameplay.Player;

import java.util.ArrayList;

public abstract class Game extends Thread{
    protected ArrayList<Player> turnOrder;
    protected int playerOrder;
    protected GameObserver observer;


    public Game(){
        this.turnOrder = new ArrayList<>();
        this.playerOrder = 0;
    }
    public abstract void notifyEndTurn();
    public abstract void notifyGameStart();

    public void setObserver(GameObserver observer) {
        this.observer = observer;
    }

    public ArrayList<Player> getTurnOrder() {
        return turnOrder;
    }

    public void setTurnOrder(ArrayList<Player> turnOrder) {
        this.turnOrder = turnOrder;
    }

    public int getPlayerOrder() {
        return playerOrder;
    }

    public void setPlayerOrder(int playerOrder) {
        this.playerOrder = playerOrder;
    }

    public abstract boolean isGameEnded();

    public Player getPlayer(){
        return turnOrder.get(playerOrder);
    }

    public abstract void playerPlay(Card c,Player receiver);
}
