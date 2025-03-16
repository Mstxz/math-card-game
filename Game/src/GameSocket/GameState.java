package GameSocket;

import Gameplay.Player;

public class GameState {
    private int currentTurn;
    private Player[] players;
    public GameState(int capacity){
        players = new Player[capacity];
        currentTurn = 0;
    }
}
