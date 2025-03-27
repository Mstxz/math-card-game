package Gameplay.Cards;

import Gameplay.*;
import Gameplay.Numbers.Constant;

public class TwinsCat extends Card implements HaveCondition {
    public TwinsCat(){
        super("TwinsCat","Converts the user's HP to twice the user's HP.",5, Difficulty.EASY, CardType.BLUE);
    }

    @Override
    public void action(Player self, Player enemy) {
        self.setMana(self.getMana()-manaUsed);
        self.setHp(self.getHp().multiply(new Constant(2)));
    }

    @Override
    public boolean checkCondition(Player user, Player receiver) {
        return ((Constant)(user.getHp())).getNumber()<=50 && ((Constant)(user.getHp())).getNumber()>=-50;
    }
}
