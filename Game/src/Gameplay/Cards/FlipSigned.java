package Gameplay.Cards;


import Gameplay.Card;
import Gameplay.CardType;
import Gameplay.Difficulty;
import Gameplay.Numbers.Constant;
import Gameplay.Player;

public class FlipSigned extends Card {
    public FlipSigned(){
        super("*(-1)","multiply -1 to your HP",2, Difficulty.MEDIUM, CardType.BLUE);
    }

    @Override
    public void action(Player self, Player enemy) {
        self.setMana(self.getMana()-this.manaUsed);
        self.setHp(((Constant)(self.getHp())).multiply(-1));
    }
}
