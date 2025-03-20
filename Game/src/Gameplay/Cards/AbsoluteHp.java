package Gameplay.Cards;

import GUI.CardAction;
import GUI.CardActionType;
import Gameplay.Card;
import Gameplay.CardType;
import Gameplay.Difficulty;
import Gameplay.Numbers.Constant;
import Gameplay.Player;

import java.util.ArrayList;

public class AbsoluteHp extends Card {
    public AbsoluteHp(){
        super("AbsoluteC.Cat","Absolute user hp",2, Difficulty.MEDIUM, CardType.BLUE);
        //this.picture = "assets/Blue_BetaAbsoluteHP.png";
    }

    @Override
    public void action(Player self, Player enemy) {
        self.setMana(self.getMana()-this.manaUsed);
        self.setHp(new Constant(Math.abs(((Constant)(self.getHp())).getNumber())));
    }

    @Override
    public ArrayList<CardAction> getCardAction(Player self, Player enemy) {
        ArrayList<CardAction> arr = new ArrayList<CardAction>();
        arr.add(new CardAction(CardActionType.SET_HP,getReceiver(self,enemy).getPlayerNumber()));
        return arr;
    }
}
