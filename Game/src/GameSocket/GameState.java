package GameSocket;

import Gameplay.Player;

import java.util.ArrayList;

public class GameState {
    private int currentTurn;
    private ArrayList<PlayerInfo> players;
    private ArrayList<Boolean> playerAlive;
    public GameState(ArrayList<PlayerInfo> playerInfos){
        players = playerInfos;
        playerAlive = new ArrayList<Boolean>();
        for (PlayerInfo p:playerInfos){
            playerAlive.add(true);
        }
        currentTurn = 0;
    }
}
