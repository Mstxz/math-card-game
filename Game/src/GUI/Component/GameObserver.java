package GUI.Component;

import Gameplay.Card;
import Gameplay.CardAction.CardAction;
import Gameplay.Player;

import java.util.ArrayList;

public interface GameObserver {
    public abstract void onGameStart(int startTurn);
    public abstract void onCardPlayed();
    public abstract void onHandChanged();
    public abstract void onStatChanged();
    public abstract void onPlayerQuit(Player playerQuit);
    public abstract void onGameEnded(Player winner);
    public abstract void onTurnArrive();
    public abstract void onTurnEnded();
    public abstract void onTurnCountChange(int count);
}
