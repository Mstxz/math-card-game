package Gameplay.CardAction;

import Gameplay.Number;
import Gameplay.Numbers.Constant;
import Gameplay.Player;

import java.nio.ByteBuffer;

public class SetHp extends CardAction{
    private Number newHp;

    public SetHp(int target){
        this(new Constant(1),target);
    }

    public SetHp(Number newHp, int target){
        super(CardActionType.SET_HP,target);
        this.newHp = newHp;
    }

    public Number getNewHp() {
        return newHp;
    }

    public void setNewHp(Number newHp) {
        this.newHp = newHp;
    }
    @Override
    public byte[] encodeBytes() {
        byte[] typeAndTarget = super.encodeBytes();
        ByteBuffer byteBuffer = ByteBuffer.allocate(4 + newHp.encodedBytes().length + typeAndTarget.length);
        byteBuffer.put(typeAndTarget);
        byteBuffer.putInt(newHp.encodedBytes().length);
        byteBuffer.put(newHp.encodedBytes());
        return byteBuffer.array();
    }
}
