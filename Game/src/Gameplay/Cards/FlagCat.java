package Gameplay.Cards;

import Gameplay.*;
import Gameplay.Numbers.Constant;

import java.math.BigInteger;

public class FlagCat extends Card implements HaveCondition {
    public FlagCat(){
        super("FlagCat","Convert the user's HP to the factorial of the user's HP.",6, Difficulty.HARD, CardType.BLUE);
    }

    @Override
    public void action(Player self, Player enemy) {
        self.setMana(self.getMana()-manaUsed);
        int tmp = 1;
        for (int i = 1;i<=((Constant)self.getHp()).getNumber();i++){
            tmp*=i;
        }
        self.setHp(new Constant(tmp));
    }

    @Override
    public boolean checkCondition(Player user, Player receiver) {
        return ((Constant)receiver.getHp()).getNumber()<6 && ((Constant)(receiver.getHp())).getNumber()>=1;
    }
}
