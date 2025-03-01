package GameSocket;

import Gameplay.Card;
import Gameplay.Number;
import Gameplay.NumberType;
import Gameplay.Numbers.Constant;
import Gameplay.Player;

import java.util.ArrayList;

public class PlayerResource{
    private String name;
    private int mana;
    private ArrayList<Card> hand;
    private Number hp;
    private NumberType numberType;
    private int maxMana;
    private int playerNumber;

    public PlayerResource(String name, int hp, int playerNumber) {
        this.name = name;
        this.mana = 1;
        this.hand = new ArrayList<Card>();
        this.hp = new Constant(hp);
        this.numberType = NumberType.CONSTANT;
        this.maxMana = 1;
        this.playerNumber = playerNumber;
    }
}
