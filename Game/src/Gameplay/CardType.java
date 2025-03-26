package Gameplay;

public enum CardType {
    RED,
    GREEN,
    BLUE,
    YELLOW;

    @Override
    public String toString() {
        return switch (this){
            case RED -> "Red";
            case BLUE -> "Blue";
            case GREEN -> "Green";
            case YELLOW -> "Yellow";
        };
    }
}

