package Gameplay.Cards;

import Gameplay.*;

public class AdoptCat extends Card implements HaveCondition {
    public AdoptCat(){
        super("AdoptCat","Add the last card played or discarded by the user to the user's hand.",2, Difficulty.EASY, CardType.YELLOW);
    }

    @Override
    public void action(Player self, Player enemy) {
        self.setMana(self.getMana()-manaUsed);
        Card tmp = self.getDeck().getDispose().removeLast();
        self.getHand().add(tmp);
    }

    @Override
    public boolean checkCondition(Player user, Player receiver) {
        return !user.getDeck().getDispose().isEmpty();
    }
}
