package Gameplay.Cards;

import Gameplay.CardAction.*;
import Gameplay.*;
import Gameplay.Numbers.Constant;

import java.util.ArrayList;

public class LogAndRoll extends Card implements HaveCondition {
    public LogAndRoll(){
        super("LogAndRoll","Make opponent's HP base 10 logarithm of theirs HP. The turn ends after this card is played.",10, Difficulty.EXPERT, CardType.RED);
    }

    @Override
    public void action(Player self, Player enemy) {
        self.setMana(self.getMana()-manaUsed);
        Player receiver = getReceiver(self,enemy);
        receiver.setHp(new Constant((int)Math.log10(((Constant)(receiver.getHp())).getNumber())));
        // Add End turn
    }

    @Override
    public boolean checkCondition(Player user, Player receiver) {
        return (((Constant)(receiver.getHp())).getNumber() >=10);
    }

    @Override
    public ArrayList<CardAction> getCardAction(Player self, Player enemy) {
        ArrayList<CardAction> arr = new ArrayList<CardAction>();
        Player receiver = this.getReceiver(self,enemy);
        arr.add(new SetMana(self.getMana()-this.getManaUsed(),self));
        arr.add(new SetHp(new Constant((int)Math.log10(((Constant)(receiver.getHp())).getNumber())),receiver));
        return arr;
    }
}
