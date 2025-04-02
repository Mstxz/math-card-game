package Gameplay.Cards;

import Gameplay.*;
import Gameplay.Numbers.Constant;

import java.util.ArrayList;

public class PersistenceCat extends Card implements HaveCondition {
    public PersistenceCat(){
        super("PersistenceCat","Sqare root opponent HP",9, Difficulty.MEDIUM, CardType.RED);
//        this.picture = "assets/Red_BetaTimeSquare.png";
    }

    @Override
    public void action(Player self, Player enemy) {
        Player receiver = this.getReceiver(self,enemy);
        self.setMana(self.getMana()-this.manaUsed);
        receiver.setHp(new Constant((int)(Math.sqrt(((Constant)(receiver.getHp())).getNumber()))));
    }

    @Override
    public boolean checkCondition(Player user, Player receiver) {
        if (((Constant)(receiver.getHp())).getNumber()<0){
            return false;
        }
        return true;
    }


}
