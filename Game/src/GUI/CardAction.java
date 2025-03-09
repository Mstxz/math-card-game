package GUI;

public class CardAction {
    private CardActionType type;
    private int targetId;
    private int amount;

    public CardAction(CardActionType type, int targetId) {
        this.type = type;
        this.targetId = targetId;
    }
    public CardAction(CardActionType type, int targetId,int amount) {
        this.type = type;
        this.targetId = targetId;
        this.amount = amount;
    }
}
