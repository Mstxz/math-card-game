package GameSocket;

public class PlayerEvent {
    private int playerIndex;
    private PlayerEventType type;
    private String arg[];
    public PlayerEvent(int playerIndex, PlayerEventType type) {
        this.playerIndex = playerIndex;
        this.type = type;
    }

    public PlayerEvent(int playerIndex, PlayerEventType type, String[] arg) {
        this.playerIndex = playerIndex;
        this.type = type;
        this.arg = arg;
    }

    public int getPlayerIndex() {
        return playerIndex;
    }

    public void setPlayerIndex(int playerIndex) {
        this.playerIndex = playerIndex;
    }

    public PlayerEventType getType() {
        return type;
    }

    public void setType(PlayerEventType type) {
        this.type = type;
    }

    public String[] getArg() {
        return arg;
    }

    public void setArg(String[] arg) {
        this.arg = arg;
    }

    @Override
    public String toString() {
        return "PlayerEvent{" +
                "playerIndex=" + playerIndex +
                ", type=" + type +
                ", arg=" + arg +
                '}';
    }
}
