package GameSocket;

import Gameplay.Player;

import java.util.ArrayList;

public class GameState {
    private int countLoaded;
    private int currentTurn;
    private int turnCount;
    private ArrayList<PlayerInfo> players;
    private ArrayList<Boolean> playerAlive;
    public GameState(ArrayList<PlayerInfo> playerInfos){

        players = playerInfos;
        playerAlive = new ArrayList<Boolean>();
        for (PlayerInfo p:playerInfos){
            playerAlive.add(true);
        }
        turnCount = 0;
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

    public void setCurrentTurn(int currentTurn) {
        this.currentTurn = currentTurn;
    }

    public int getCurrentTurn() {
        return currentTurn;
    }

    public ArrayList<PlayerInfo> getPlayers() {
        return players;
    }

    public int getTurnCount() {
        return turnCount;
    }

    public void setTurnCount(int turnCount) {
        this.turnCount = turnCount;
    }
}
