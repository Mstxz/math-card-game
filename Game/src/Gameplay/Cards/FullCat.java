package Gameplay.Cards;

import Gameplay.Card;
import Gameplay.CardType;
import Gameplay.Difficulty;
import Gameplay.Player;

public class FullCat extends Card {
    public FullCat(){
        super("FullCat","Add two \"CatTreat\" to user's hand.",5, Difficulty.BABY, CardType.YELLOW);
    }

    @Override
    public void action(Player self, Player enemy) {
        self.setMana(self.getMana()-manaUsed);
        self.getHand().add(new CatTreat());
        self.getHand().add(new CatTreat());
    }
}
