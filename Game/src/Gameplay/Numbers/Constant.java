package Gameplay.Numbers;

import Gameplay.Number;
import Gameplay.NumberType;

import java.nio.ByteBuffer;

public class Constant extends Number {
    private final int number;
    public Constant(int number){
        super(NumberType.CONSTANT);
        this.number = number;
    }

    public int getNumber(){
        return number;
    }
    @Override
    public Number add(Number a) {
        return add(((Constant)(a)).getNumber());
    }

    @Override
    public Number subtract(Number a) {
        return subtract(((Constant)(a)).getNumber());
    }

    @Override
    public Number multiply(Number a) {
        return multiply(((Constant)(a)).getNumber());
    }

    @Override
    public Number divided(Number a) {
        return divide(((Constant)(a)).getNumber());
    }

    public Number add(int a){
        return  new Constant(this.number+a);
    }
    public Number subtract(int a){
        return new Constant(this.number-a);
    }
    public Number multiply(int a){
        return new Constant(this.number*a);
    }
    public Number divide(int a){
        return new Constant(this.number/a);
    }

    @Override
    public int absolute() {
        return Math.abs(number);
    }

    public String toString(){
        return this.number+"";
    }

    @Override
    public byte[] encodedBytes() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        byteBuffer.putInt(this.type.ordinal());
        byteBuffer.putInt(number);
        return byteBuffer.array();
    }
}
