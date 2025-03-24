package GameSocket;

public class ServerInfo {
    private boolean running = true;
    private boolean listening = false;
    private boolean gameStarted = false;
    private boolean deckLoaded = false;

    public void updateDeckLoaded(PlayerState[] ps) {
        int loaded = 0;
        int totalPlayer = 0;
        for(PlayerState p:ps){
            if(p != null){
                if (p.getDeckPath() != null){
                    loaded ++;
                }
                totalPlayer ++;
            }
        }
        if (totalPlayer == loaded){
            deckLoaded = true;
        }
    }

    public void updateGameStarted(PlayerState[] ps) {
        int loaded = 0;
        int totalPlayer = 0;
        for(PlayerState p:ps){
            if(p != null) {
                if (p.isStarted()) {
                    loaded++;
                }
                totalPlayer++;
            }
        }
        if (totalPlayer == loaded){
            gameStarted = true;
        }
    }

    public boolean isDeckLoaded() {
        return deckLoaded;
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean isListening() {
        return listening;
    }

    public void setListening(boolean listening) {
        this.listening = listening;
    }
}
