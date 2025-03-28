package Gameplay.CardAction;

import Gameplay.Player;

import java.nio.ByteBuffer;

public class SetMana extends CardAction{
    private int newMana;

    public SetMana(int target){
        this(1,target);
    }

    public SetMana(int newMana, int target){
        super(CardActionType.SET_MANA,target);
        this.newMana = newMana;
    }

    public int getNewMana() {
        return newMana;
    }

    public void setNewMana(int newMana) {
        this.newMana = newMana;
    }

    @Override
    public byte[] encodeBytes() {
        byte[] typeAndTarget = super.encodeBytes();
        ByteBuffer byteBuffer = ByteBuffer.allocate(4 + typeAndTarget.length);
        byteBuffer.put(typeAndTarget);
        byteBuffer.putInt(newMana);
        return byteBuffer.array();
    }
}
