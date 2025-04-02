package Gameplay.Cards;

import Gameplay.Card;
import Gameplay.CardType;
import Gameplay.Difficulty;
import Gameplay.Numbers.Constant;
import Gameplay.Player;

import java.util.ArrayList;

public class GiftCat extends Card {
    public GiftCat(){
        super("GiftCat","Makes the opponent's HP equal to the user's HP.",3, Difficulty.EASY, CardType.RED);
    }

    @Override
    public void action(Player self, Player enemy) {
        self.setMana(self.getMana()-manaUsed);
        getReceiver(self,enemy).setHp(self.getHp());
    }

}
