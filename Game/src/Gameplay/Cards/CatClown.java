package Gameplay.Cards;


import Gameplay.*;

import java.util.ArrayList;

public class CatClown extends Card implements HaveCondition {
    public CatClown(){
        super("CatClown","Add a random copy card from opponent to user's hand.",3, Difficulty.BABY, CardType.YELLOW);
    }

    @Override
    public void action(Player self, Player enemy) {
        self.setMana(self.getMana()-manaUsed);
        int index = (int) (Math.random()*enemy.getHand().size())%enemy.getHand().size();
        self.getHand().add(enemy.getHand().get(index));
    }

    @Override
    public boolean checkCondition(Player user, Player receiver) {
        return receiver.getHand().size()>0;
    }

}
