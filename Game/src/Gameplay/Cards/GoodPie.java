package Gameplay.Cards;

import Gameplay.*;
import Gameplay.Numbers.Constant;

public class GoodPie extends Card implements HaveCondition {
    public GoodPie(){
        super("GoodPie","Convert user's HP as a percent ( 1 - 100 ) to degree on Pie chart ( 3.6 - 360 )",8, Difficulty.HARD, CardType.BLUE);
    }

    @Override
    public void action(Player self, Player enemy) {
        self.setMana(self.getMana()-manaUsed);
        getReceiver(self,enemy).setHp(new Constant((((Constant)(getReceiver(self,enemy).getHp())).getNumber()*360)/100));
    }

    @Override
    public boolean checkCondition(Player user, Player receiver) {
        return ((Constant)receiver.getHp()).getNumber()>=1 && ((Constant)receiver.getHp()).getNumber()<=100;
    }
}
