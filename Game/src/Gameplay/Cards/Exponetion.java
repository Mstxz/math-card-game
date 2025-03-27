package Gameplay.Cards;

import Gameplay.*;
import Gameplay.Numbers.Constant;

public class Exponetion extends Card implements HaveCondition {
    public Exponetion(){
        super("Exponetion","Convert the user's HP to the square of the user's HP.",5, Difficulty.MEDIUM, CardType.BLUE);
    }

    @Override
    public void action(Player self, Player enemy) {
        self.setMana(self.getMana()-manaUsed);
        Constant tmp = new Constant(((Constant)(self.getHp())).getNumber()*((Constant)(self.getHp())).getNumber());
        self.setHp(tmp);
    }

    @Override
    public boolean checkCondition(Player user, Player receiver) {
        return ((Constant)(user.getHp())).getNumber()<=10 && ((Constant)(user.getHp())).getNumber()>=-10;
    }
}
