package GUI.Component;

import Gameplay.Player;

public interface GameObserver {
    public abstract void onCardPlayed();
    public abstract void onHandChanged();
    public abstract void onPlayerQuit(Player playerQuit);
    public abstract void onGameEnded(Player winner);
    public abstract void onTurnArrive();
    public abstract void onTurnEnded();
}
