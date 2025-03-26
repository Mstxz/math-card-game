package Gameplay.CardAction;

import Gameplay.Player;

public class Discard extends CardAction{
    private int amount;

    public Discard(Player target){
        this(1,target);
    }

    public Discard(int amount, Player target){
        super(CardActionType.DISCARD,target);
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
