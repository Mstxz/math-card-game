package GameSocket;

public class PlayerState {
    private boolean ready;
    private int playerID;
    private boolean started;
    private String deckPath;
    public PlayerState(int id){
        setPlayerID(id);
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

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
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
}
