package Gameplay.CardAction;

import Gameplay.Player;

import java.nio.ByteBuffer;

public class EndTurn extends CardAction{

    public EndTurn(int target){
        super(CardActionType.END_TURN,target);
    }
    @Override
    public byte[] encodeBytes() {
        return super.encodeBytes();
    }
}
