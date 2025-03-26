package Gameplay.CardAction;

import Gameplay.Player;

public class Draw extends CardAction{
    private int amount;

    public Draw(Player target){
        this(1,target);
    }

    public Draw(int amount, Player target){
        super(CardActionType.DRAW,target);
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
