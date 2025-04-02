package Gameplay.Cards;

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
        for (int i = 0; i < Math.min(2,self.getDeck().getCards().size()); i++) {
            self.draw();
        }
        self.setMana(self.getMana()-this.manaUsed);

    }

}
