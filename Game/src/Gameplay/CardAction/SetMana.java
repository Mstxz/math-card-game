package Gameplay.CardAction;

import Gameplay.Player;

public class SetMana extends CardAction{
    private int newMana;

    public SetMana(Player target){
        this(1,target);
    }

    public SetMana(int newMana, Player target){
        super(CardActionType.SET_MANA,target);
        this.newMana = newMana;
    }

    public int getNewMana() {
        return newMana;
    }

    public void setNewMana(int newMana) {
        this.newMana = newMana;
    }
}
