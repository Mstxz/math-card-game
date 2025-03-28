package Gameplay.Cards;

import Gameplay.CardAction.*;
import Gameplay.Card;
import Gameplay.CardType;
import Gameplay.Difficulty;
import Gameplay.Player;

import java.util.ArrayList;

public class Copycat extends Card {
    public Copycat(){
        super("Copycat","Copy your HP to be the same as your opponent.",3, Difficulty.EASY, CardType.BLUE);
    }

    @Override
    public void action(Player self, Player enemy) {
        self.setHp(enemy.getHp());
        self.setMana(self.getMana()-this.manaUsed);
    }
    @Override
    public ArrayList<CardAction> getCardAction(Player self, Player enemy) {
        ArrayList<CardAction> arr = new ArrayList<CardAction>();
        Player receiver = this.getReceiver(self,enemy);
        arr.add(new SetMana(self.getMana()-this.getManaUsed(),self.getPlayerNumber()));
        arr.add(new SetHp(enemy.getHp(),receiver.getPlayerNumber()));
        return arr;
    }
}
