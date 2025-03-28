package Gameplay.CardAction;

import Gameplay.Player;

import java.nio.ByteBuffer;

public class Discard extends CardAction{
    private int amount;

    public Discard(int target){
        this(1,target);
    }

    public Discard(int amount, int target){
        super(CardActionType.DISCARD,target);
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
