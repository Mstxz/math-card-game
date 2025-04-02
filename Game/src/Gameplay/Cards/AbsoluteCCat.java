package Gameplay.Cards;

import Gameplay.Card;
import Gameplay.CardType;
import Gameplay.Difficulty;
import Gameplay.Numbers.Constant;
import Gameplay.Player;

import java.util.ArrayList;

public class AbsoluteCCat extends Card {
    public AbsoluteCCat(){
        super("AbsoluteCCat","Absolute user hp",2, Difficulty.MEDIUM, CardType.BLUE);
        //this.picture = "assets/Blue_BetaAbsoluteHP.png";
    }

    @Override
    public void action(Player self, Player enemy) {
        self.setMana(self.getMana()-this.manaUsed);
        self.setHp(new Constant(Math.abs(((Constant)(self.getHp())).getNumber())));
    }


}
