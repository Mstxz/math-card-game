package Gameplay.Cards;

import Gameplay.Card;
import Gameplay.CardType;
import Gameplay.Difficulty;
import Gameplay.Numbers.Constant;
import Gameplay.Player;

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
}
