package Gameplay.Cards;

import Gameplay.Card;
import Gameplay.CardType;
import Gameplay.Difficulty;
import Gameplay.Numbers.Constant;
import Gameplay.Player;

public class DehydrateCat extends Card {
    public DehydrateCat(){
        super("DehydrateCat","Increases the user's HP according to the mana the user has.",3, Difficulty.EASY, CardType.BLUE);
    }

    @Override
    public void action(Player self, Player enemy) {
        self.setHp(self.getHp().add(new Constant(self.getMana())));
        self.setMana(self.getMana()-manaUsed);
    }
}
