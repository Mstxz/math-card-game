package Gameplay.Cards;

import Gameplay.CardAction.*;
import Gameplay.Card;
import Gameplay.CardType;
import Gameplay.Difficulty;
import Gameplay.Player;

import java.util.ArrayList;

public class CatNap extends Card {
    public CatNap(){
        super("CatNap","Draw 2 cards",2, Difficulty.BABY, CardType.YELLOW);
        //this.picture = "assets/Yellow_BetaCatNap.png";
    }

    @Override
    public void action(Player self, Player enemy) {
        self.draw();
        self.draw();
        self.setMana(self.getMana()-this.manaUsed);

    }

    @Override
    public ArrayList<CardAction> getCardAction(Player self,Player enemy) {
        ArrayList<CardAction> arr = new ArrayList<CardAction>();
        Player receiver = this.getReceiver(self,enemy);
        arr.add(new SetMana(self.getMana()-this.getManaUsed(),self));
        arr.add(new Draw(2,receiver));
        return arr;
    }
}
