package Gameplay.Cards;

import GUI.CardAction;
import GUI.CardActionType;
import Gameplay.*;
import Gameplay.Numbers.Constant;

import java.util.ArrayList;

public class TimeSquare extends Card implements HaveCondition {
    public TimeSquare(){
        super("Sqrt(HP)","Sqare root opponent HP",7, Difficulty.MEDIUM, CardType.RED);
        this.picture = "assets/Red_BetaTimeSquare.png";
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


    @Override
    public ArrayList<CardAction> getCardAction(Player self, Player enemy) {
        ArrayList<CardAction> arr = new ArrayList<CardAction>();
        arr.add(new CardAction(CardActionType.SET_HP,getReceiver(self,enemy).getPlayerNumber()));
        return arr;
    }
}
