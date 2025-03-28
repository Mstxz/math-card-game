package Gameplay.CardAction;


import Gameplay.Card;
import Gameplay.CardType;
import Gameplay.Number;
import Gameplay.Player;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public abstract class CardAction {
    protected final CardActionType type;
    protected final int targetID;
    public CardAction(CardActionType type, int target) {
        this.type = type;
        this.targetID = target;
    }

    public CardActionType getType() {
        return type;
    }


    public int getTargetID() {
        return targetID;
    }

    public byte[] encodeBytes(){
        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        byteBuffer.putInt(type.ordinal());
        byteBuffer.putInt(targetID);
        return byteBuffer.array();
    }

    public static CardAction decodeBytes(byte[] bytes){
        try (DataInputStream in = new DataInputStream(new ByteArrayInputStream(bytes))){
            int actionTypeOrdinal = in.readInt();
            CardActionType cardActionType = CardActionType.values()[actionTypeOrdinal];
            int targetID = in.readInt();
            switch (cardActionType){
                case DRAW -> {
                    int amount = in.readInt();
                    return new Draw(targetID,amount);
                }
                case SET_HP -> {
                    int hpBytes = in.readInt();
                    Number newHP = Number.decodedBytes(in.readNBytes(hpBytes));
                    return new SetHp(newHP,targetID);
                }
                case END_TURN -> {
                    return new EndTurn(targetID);
                }
            }

        }
        catch(IOException e){
            e.printStackTrace();
        }
        return new EndTurn(0);
    }


}
