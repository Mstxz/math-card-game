package Gameplay.Numbers;

import Gameplay.Number;

public class Constant extends Number {
    private int number;
    public Constant(int number){
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

    public String toString(){
        return this.number+"";
    }
}
