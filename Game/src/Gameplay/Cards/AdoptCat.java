package Gameplay.Cards;

import Gameplay.CardAction.*;
import Gameplay.*;

import java.util.ArrayList;

public class AdoptCat extends Card implements HaveCondition {
    public AdoptCat(){
        super("AdoptCat","Add the last card played or discarded by the user to the user's hand.",2, Difficulty.BABY, CardType.YELLOW);
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

    @Override
    public ArrayList<CardAction> getCardAction(Player self, Player enemy) {
        ArrayList<CardAction> arr = new ArrayList<CardAction>();
        Player receiver = this.getReceiver(self,enemy);
        arr.add(new SetMana(self.getMana()-this.getManaUsed(),self.getPlayerNumber()));
        arr.add(new GetFromDiscard(receiver.getPlayerNumber(), new Integer[]{self.getDeck().getDispose().size()-1}));
        return arr;
    }
}
