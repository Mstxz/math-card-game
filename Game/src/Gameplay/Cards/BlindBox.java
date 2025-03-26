package Gameplay.Cards;

import Gameplay.CardAction.*;
import Gameplay.Card;
import Gameplay.CardType;
import Gameplay.Difficulty;
import Gameplay.Numbers.Constant;
import Gameplay.Player;

import java.util.ArrayList;

public class BlindBox extends Card {
    public BlindBox(){
        super("BlindBox","Select a random number from 1 to 10 to increase the target's HP.",2, Difficulty.MEDIUM, CardType.GREEN);
    }

    @Override
    public void action(Player self, Player enemy) {
        int tmp = (int)(Math.random()*9)+1;
        Player receiver = getReceiver(self,enemy);
        receiver.setHp(receiver.getHp().add(new Constant(tmp)));
        self.setMana(self.getMana()-manaUsed);
    }

    @Override
    public ArrayList<CardAction> getCardAction(Player self, Player enemy) {
        ArrayList<CardAction> arr = new ArrayList<CardAction>();
        Player receiver = this.getReceiver(self,enemy);
        arr.add(new SetMana(self.getMana()-this.getManaUsed(),self));
        int tmp = (int)(Math.random()*9)+1;
        arr.add(new SetHp(receiver.getHp().add(new Constant(tmp)),receiver));
        return arr;
    }
}
