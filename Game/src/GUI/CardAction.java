package GUI;

public class CardAction {
    private CardActionType type;
    private int targetId;

    public CardAction(CardActionType type, int targetId) {
        this.type = type;
        this.targetId = targetId;
    }
}
