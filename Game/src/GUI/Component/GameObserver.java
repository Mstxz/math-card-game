package GUI.Component;

import Gameplay.Card;

import Gameplay.Player;

public interface GameObserver {
    public abstract void onGameStart(int startTurn);
    public abstract void onCardPlayed(Card cardPlayed);
    public abstract void onHandChanged();
    public abstract void onStatChanged();
    public abstract void onGameEnded(Player winner);
    public abstract void onTurnArrive();
    public abstract void onTurnEnded();
    public abstract void onTurnCountChange(int count);
}
