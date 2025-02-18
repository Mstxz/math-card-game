package Gameplay;

public abstract class Number {
    private NumberType type;

    public abstract Number add(Number a);
    public abstract Number subtract(Number a);
    public abstract Number multiply(Number a);
    public abstract Number divided(Number a);

}
