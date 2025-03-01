package GameSocket;

import java.util.ArrayDeque;
import java.util.Queue;

public class PlayerState {
    private boolean ready;
    private PlayerInfo playerInfo;
    private boolean started;
    private String deckPath;
    private ArrayDeque<byte[]> dataOutQueue;

    public PlayerState(int id){
        dataOutQueue = new ArrayDeque<byte[]>();
        setPlayerInfo(new PlayerInfo());
        playerInfo.setPlayerID(id);
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public void toggleReady() {
        ready = !ready;
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public String getDeckPath() {
        return deckPath;
    }

    public void setDeckPath(String deckPath) {
        this.deckPath = deckPath;
    }

    public PlayerInfo getPlayerInfo() {
        return playerInfo;
    }

    public void setPlayerInfo(PlayerInfo playerInfo) {
        this.playerInfo = playerInfo;
    }

    public ArrayDeque<byte[]> getDataOutQueue() {
        return dataOutQueue;
    }
}
