package Gameplay.Cards;

import Gameplay.*;

import java.util.ArrayList;

public class AdoptCat extends Card implements HaveCondition {
    public AdoptCat(){
        super("AdoptCat","Add the last card played or discarded by the user to the user's hand.",2, Difficulty.BABY, CardType.YELLOW);
    }

    @Override
    public void action(Player self, Player enemy) {
        self.setMana(self.getMana()-manaUsed);
        Card tmp = self.getDeck().getDispose().getLast();
        self.getHand().add(tmp);
    }

    @Override
    public boolean checkCondition(Player user, Player receiver) {
        return !user.getDeck().getDispose().isEmpty();
    }

}
