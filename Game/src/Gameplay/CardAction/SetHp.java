package Gameplay.CardAction;

import Gameplay.Number;
import Gameplay.Numbers.Constant;
import Gameplay.Player;

public class SetHp extends CardAction{
    private Number newHp;

    public SetHp(Player target){
        this(new Constant(1),target);
    }

    public SetHp(Number newHp, Player target){
        super(CardActionType.SET_HP,target);
        this.newHp = newHp;
    }

    public Number getNewHp() {
        return newHp;
    }

    public void setNewHp(Number newHp) {
        this.newHp = newHp;
    }
}
