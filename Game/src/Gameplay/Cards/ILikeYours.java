package Gameplay.Cards;

import Gameplay.CardAction.*;
import Gameplay.Card;
import Gameplay.CardType;
import Gameplay.Difficulty;
import Gameplay.Number;
import Gameplay.Player;

import java.util.ArrayList;

public class ILikeYours extends Card {
    public ILikeYours(){
        super("I like yours","Swap the user's HP with that of the opponent.",4, Difficulty.EASY, CardType.RED);
    }

    @Override
    public void action(Player self, Player enemy) {
        self.setMana(self.getMana()-manaUsed);
        Number tmp = self.getHp();
        self.setHp(enemy.getHp());
        enemy.setHp(tmp);
    }

    @Override
    public ArrayList<CardAction> getCardAction(Player self, Player enemy) {
        ArrayList<CardAction> arr = new ArrayList<CardAction>();
        arr.add(new SetMana(self.getMana()-this.getManaUsed(),self));
        arr.add(new SetHp(enemy.getHp(),self));
        arr.add(new SetHp(self.getHp(),enemy));
        return arr;
    }
}
