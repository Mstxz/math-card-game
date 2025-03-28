package Gameplay.CardAction;

import Gameplay.Card;
import Gameplay.Player;

import java.nio.ByteBuffer;
import java.util.ArrayList;

public class GetCard extends CardAction{

    private final Card[] cards;

    public GetCard(int target, Card ...cards){
        super(CardActionType.GET_CARD,target);
        this.cards = cards;
    }

    public Card[] getCards() {
        return cards;
    }

    @Override
    public byte[] encodeBytes() {
        byte[] typeAndTarget = super.encodeBytes();
        int cardBytes = 0;
        for (Card card:cards){
            cardBytes += 4 + card.encode().length;
        }
        ByteBuffer byteBuffer = ByteBuffer.allocate(4 + cardBytes + typeAndTarget.length);
        byteBuffer.put(typeAndTarget);
        byteBuffer.putInt(cardBytes);
        for (Card card:cards){
            byteBuffer.putInt(card.encode().length);
            byteBuffer.put(card.encode());
        }
        return byteBuffer.array();
    }
}
