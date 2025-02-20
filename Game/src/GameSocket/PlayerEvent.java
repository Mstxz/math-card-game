package GameSocket;

public class PlayerEvent {
    private int playerIndex;
    private PlayerEventType type;
    private int cardIndex;
    public PlayerEvent(int playerIndex, PlayerEventType type) {
        this.playerIndex = playerIndex;
        this.type = type;
    }

    public PlayerEvent(int playerIndex, PlayerEventType type, int cardIndex) {
        this.playerIndex = playerIndex;
        this.type = type;
        this.cardIndex = cardIndex;
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

    public int getCardIndex() {
        return cardIndex;
    }

    public void setCardIndex(int cardIndex) {
        this.cardIndex = cardIndex;
    }

    @Override
    public String toString() {
        return "PlayerEvent{" +
                "playerIndex=" + playerIndex +
                ", type=" + type +
                ", cardIndex=" + cardIndex +
                '}';
    }
}
