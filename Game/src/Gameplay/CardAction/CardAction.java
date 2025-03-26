package Gameplay.CardAction;


import Gameplay.Player;

public abstract class CardAction {
    protected final CardActionType type;
    protected final Player target;
    public CardAction(CardActionType type, Player target) {
        this.type = type;
        this.target = target;
    }

    public CardActionType getType() {
        return type;
    }
}
