package Gameplay.Cards;


import GUI.CardAction;
import GUI.CardActionType;
import Gameplay.Card;
import Gameplay.CardType;
import Gameplay.Difficulty;
import Gameplay.Numbers.Constant;
import Gameplay.Player;

import java.util.ArrayList;

public class FlipSigned extends Card {
    public FlipSigned(){
        super("FlipSigned","multiply -1 to your HP",2, Difficulty.MEDIUM, CardType.BLUE);
        //this.picture = "assets/Blue_BetaFlipSignedHP.png";
    }

    @Override
    public void action(Player self, Player enemy) {
        self.setMana(self.getMana()-this.manaUsed);
        self.setHp(((Constant)(self.getHp())).multiply(-1));
    }

    @Override
    public ArrayList<CardAction> getCardAction(Player self, Player enemy) {
        ArrayList<CardAction> arr = new ArrayList<CardAction>();
        arr.add(new CardAction(CardActionType.SET_HP,getReceiver(self,enemy).getPlayerNumber()));
        return arr;
    }
}
