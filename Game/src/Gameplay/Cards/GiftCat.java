package Gameplay.Cards;

import Gameplay.CardAction.*;
import Gameplay.Card;
import Gameplay.CardType;
import Gameplay.Difficulty;
import Gameplay.Numbers.Constant;
import Gameplay.Player;

import java.util.ArrayList;

public class GiftCat extends Card {
    public GiftCat(){
        super("GiftCat","Makes the opponent's HP equal to the user's HP.",3, Difficulty.EASY, CardType.RED);
    }

    @Override
    public void action(Player self, Player enemy) {
        self.setMana(self.getMana()-manaUsed);
        getReceiver(self,enemy).setHp(self.getHp());
    }

    @Override
    public ArrayList<CardAction> getCardAction(Player self, Player enemy) {
        ArrayList<CardAction> arr = new ArrayList<CardAction>();
        Player receiver = this.getReceiver(self,enemy);
        arr.add(new SetMana(self.getMana()-this.getManaUsed(),self));
        arr.add(new SetHp(self.getHp(),receiver));
        return arr;
    }
}
