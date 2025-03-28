package Gameplay.CardAction;

import Gameplay.Player;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Collections;

public class GetFromDiscard extends CardAction{
    private final Integer[] cardsIndex;

    public GetFromDiscard(int target, Integer[] cardsIndex) {
        super(CardActionType.GET_FROM_DISCARD,target);
        this.cardsIndex = cardsIndex;
        Arrays.sort(this.cardsIndex,Collections.reverseOrder());
    }

    public Integer[] getCards() {
        return cardsIndex;
    }

    @Override
    public byte[] encodeBytes() {
        byte[] typeAndTarget = super.encodeBytes();
        ByteBuffer byteBuffer = ByteBuffer.allocate(4 + (cardsIndex.length * 4) + typeAndTarget.length);
        byteBuffer.put(typeAndTarget);
        byteBuffer.putInt(cardsIndex.length * 4);
        for (Integer i:cardsIndex){
            byteBuffer.putInt(i);
        }
        return byteBuffer.array();
    }
}
