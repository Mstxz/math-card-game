package Gameplay.Cards;

import Gameplay.Card;
import Gameplay.CardType;
import Gameplay.Difficulty;
import Gameplay.Numbers.Constant;
import Gameplay.Player;

public class CoolIs37 extends Card {
    public CoolIs37(){
        super("37isCool","Modulo opponent's HP by 37.",7, Difficulty.HARD, CardType.RED);
    }

    @Override
    public void action(Player self, Player enemy) {
        self.setMana(self.getMana()-manaUsed);
        enemy.setHp((new Constant((((Constant)(enemy.getHp())).absolute()%37))));
    }
}
