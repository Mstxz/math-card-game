package Gameplay.Cards;

import Gameplay.Card;
import Gameplay.CardType;
import Gameplay.Difficulty;
import Gameplay.Numbers.Constant;
import Gameplay.Player;

public class Absolute extends Card {
    public Absolute(){
        super("Absolute HP","Absolute user hp",2, Difficulty.MEDIUM, CardType.BLUE);
        this.picture = "assets/Blue_BetaAbsoluteHP.png";
    }

    @Override
    public void action(Player self, Player enemy) {
        self.setMana(self.getMana()-this.manaUsed);
        self.setHp(new Constant(Math.abs(((Constant)(self.getHp())).getNumber())));
    }
}
