package Gameplay.Cards;

import Gameplay.Card;
import Gameplay.CardType;
import Gameplay.Difficulty;
import Gameplay.Numbers.Constant;
import Gameplay.Player;

public class EquallyFair extends Card {
    public EquallyFair(){
        super("EquallyFair","Change each player's HP to their average HP.",6, Difficulty.MEDIUM, CardType.RED);
    }

    @Override
    public void action(Player self, Player enemy) {
        self.setMana(self.getMana()-manaUsed);
        Constant selfHp = (Constant) self.getHp();
        Constant enemyHp = (Constant) enemy.getHp();
        int tmp = (int) ((selfHp.getNumber()+enemyHp.getNumber())/2);
        self.setHp(new Constant(tmp));
        enemy.setHp(new Constant(tmp));
    }
}
