package Gameplay.Cards;

import GUI.CardAction;
import GUI.CardActionType;
import Gameplay.Card;
import Gameplay.CardType;
import Gameplay.Difficulty;
import Gameplay.Player;

import java.util.ArrayList;

public class CatNap extends Card {
    public CatNap(){
        super("Cat Nap","Draw 2 cards",2, Difficulty.EASY, CardType.BLUE);
        this.picture = "assets/Yellow_BetaCatNap.png";
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
        arr.add(new CardAction(CardActionType.DRAW,getReceiver(self,enemy).getPlayerNumber(),2));
        return arr;
    }
}
