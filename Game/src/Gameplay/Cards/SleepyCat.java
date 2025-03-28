package Gameplay.Cards;

import Gameplay.Card;
import Gameplay.CardType;
import Gameplay.Difficulty;
import Gameplay.Player;

public class SleepyCat extends Card {
    public SleepyCat(){
        super("SleepyCat","Add two \"CatNap\" to user's hand.",6, Difficulty.BABY, CardType.YELLOW);
    }

    @Override
    public void action(Player self, Player enemy) {
        self.setMana(self.getMana()-manaUsed);
        self.getHand().add(new CatNap());
        self.getHand().add(new CatNap());
    }
}
