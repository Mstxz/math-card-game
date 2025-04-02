package Gameplay.Cards;

import Gameplay.Card;
import Gameplay.CardType;
import Gameplay.Difficulty;
import Gameplay.Player;

import java.util.ArrayList;

public class AngryCat extends Card {
    public AngryCat(){
        super("AngryCat","Each player discards all cards in their hand and draws new cards equal to the number of cards discarded.",6, Difficulty.BABY, CardType.YELLOW);
    }

    @Override
    public void action(Player self, Player enemy) {
        int selfHandSize = self.getHand().size();
        int enemyHandSize = enemy.getHand().size();
        self.setMana(self.getMana()-manaUsed);

        for (Card i:self.getHand()){
            self.getDeck().addDispose(i);
        }
        self.setHand(new ArrayList<Card>());
        for (Card i:enemy.getHand()){
            enemy.getDeck().addDispose(i);
        }
        enemy.setHand(new ArrayList<Card>());
        for (int i = 0; i < Math.min(selfHandSize,self.getDeck().getCards().size()); i++) {
            self.draw();
        }
        for (int i = 0; i < Math.min(enemyHandSize,enemy.getDeck().getCards().size()); i++) {
            enemy.draw();
        }
    }

}
