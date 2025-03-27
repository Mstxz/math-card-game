package GameSocket;

import Gameplay.Player;

import java.util.ArrayList;

public class GameState {
    private int countLoaded;
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
        countLoaded = 0;
    }

    public PlayerInfo getCurrentPlayer(){
        return players.get(currentTurn);
    }
    public void incrementTurn(){
        currentTurn = (currentTurn + 1) % players.size();
    }
    public void incrementInGameCount(){
        countLoaded += 1;
    }

    public boolean isAllInGame(){
        return countLoaded == players.size();
    }
}
