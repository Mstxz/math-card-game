package GameSocket;

import Gameplay.Card;

import java.util.ArrayList;

public class GameStatus {
    private boolean isTurn;
    private boolean updateAvailable;
    private ArrayList<Card> disposal;
    private ArrayList<Card> hand;
    private int cardsRemain;
    public boolean isTurn() {
        return isTurn;
    }

    public void setTurn(boolean turn) {
        isTurn = turn;
    }

    public boolean isUpdateAvailable() {
        return updateAvailable;
    }

    public void setUpdateAvailable(boolean updateAvailable) {
        this.updateAvailable = updateAvailable;
    }

    @Override
    public String toString() {
        return "ControllerStatus{" +
                "isTurn=" + isTurn +
                ", updateAvailable=" + updateAvailable +
                '}';
    }
}
