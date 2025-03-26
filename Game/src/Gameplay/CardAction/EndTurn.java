package Gameplay.CardAction;

import Gameplay.Player;

public class EndTurn extends CardAction{

    public EndTurn(Player target){
        super(CardActionType.END_TURN,target);
    }

}
