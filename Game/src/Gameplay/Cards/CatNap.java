package Gameplay.Cards;

import Gameplay.Card;
import Gameplay.CardType;
import Gameplay.Difficulty;
import Gameplay.Player;

public class CatNap extends Card {
    public CatNap(){
        super("Cat Nap","Draw 2 cards",0, Difficulty.EASY, CardType.BLUE);
    }

    @Override
    public void action(Player self, Player enemy) {
        self.draw();
        self.draw();
    }
}
