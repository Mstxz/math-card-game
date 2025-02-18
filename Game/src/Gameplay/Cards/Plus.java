package Gameplay.Cards;

import Gameplay.Card;
import Gameplay.CardType;
import Gameplay.Difficulty;
import Gameplay.Numbers.Constant;
import Gameplay.Player;

public class Plus extends Card {
    private int number;
    public Plus(int number){
        super("Plus"+number,"Add "+number+" to hp",1, Difficulty.EASY, CardType.GREEN);
        this.number = number;
    }

    @Override
    public void action(Player self,Player enemy){
        self.setMana(self.getMana()-this.getManaUsed());
        Player receiver = this.getReciever(self,enemy);
        receiver.setHp(receiver.getHp().add(new Constant(this.number)));
    }
}
