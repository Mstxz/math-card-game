package Gameplay.CardAction;

import Gameplay.Player;

import java.nio.ByteBuffer;

public class Draw extends CardAction{
    private int amount;

    public Draw(int target){
        this(1,target);
    }

    public Draw(int amount, int target){
        super(CardActionType.DRAW,target);
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public byte[] encodeBytes() {
        byte[] typeAndTarget = super.encodeBytes();
        ByteBuffer byteBuffer = ByteBuffer.allocate(4 + typeAndTarget.length);
        byteBuffer.put(typeAndTarget);
        byteBuffer.putInt(amount);
        return byteBuffer.array();
    }
}
