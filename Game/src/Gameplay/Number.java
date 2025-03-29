package Gameplay;

import GameSocket.PlayerInfo;
import Gameplay.Numbers.Constant;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class Number {
    protected final NumberType type;
    public Number(NumberType numberType){
        type = numberType;
    }
    public abstract Number add(Number a);
    public abstract Number subtract(Number a);
    public abstract Number multiply(Number a);
    public abstract Number divided(Number a);
    public abstract Number absolute();
    @Override
    public abstract String toString();

    public abstract byte[] encodedBytes();
    public static Number decodedBytes(byte[] bytes){
        try (DataInputStream in = new DataInputStream(new ByteArrayInputStream(bytes))) {
            int numberType = in.readInt();
            NumberType type = NumberType.values()[numberType];
            switch (type){
                case CONSTANT -> {
                    return new Constant(in.readInt());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
