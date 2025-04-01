package Gameplay.Cards;

import Gameplay.*;
import Gameplay.Numbers.Constant;

public class Cat_3D extends Card implements HaveCondition {
    public Cat_3D(){
        super("3D_CAT","Convert the target's HP to the target's HP raised to the power of 3.",8, Difficulty.HARD, CardType.GREEN);
    }

    @Override
    public void action(Player self, Player enemy) {
        self.setMana(self.getMana()-manaUsed);
        getReceiver(self,enemy).setHp(new Constant((int)Math.pow(((Constant)getReceiver(self,enemy).getHp()).getNumber(),3)));
    }

    @Override
    public boolean checkCondition(Player user, Player receiver) {
        return ((Constant)(user.getHp())).getNumber()<=5 && ((Constant)(user.getHp())).getNumber()>=-5;
    }
}
