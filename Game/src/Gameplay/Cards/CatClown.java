package Gameplay.Cards;

import Gameplay.Card;
import Gameplay.CardType;
import Gameplay.Difficulty;
import Gameplay.Player;

public class CatClown extends Card {
    public CatClown(){
        super("CatClown","Add a random copy card from opponent to user's hand.",3, Difficulty.EASY, CardType.YELLOW);
    }

    @Override
    public void action(Player self, Player enemy) {
        self.setMana(self.getMana()-manaUsed);
        int index = (int) (Math.random()*enemy.getHand().size())%enemy.getHand().size();
        self.getHand().add(enemy.getHand().get(index));
    }
}
