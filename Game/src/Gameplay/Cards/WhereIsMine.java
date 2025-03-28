package Gameplay.Cards;

import Gameplay.Card;
import Gameplay.CardType;
import Gameplay.Difficulty;
import Gameplay.Numbers.Constant;
import Gameplay.Player;

public class WhereIsMine extends Card {
    public WhereIsMine() {
        super("WhereIsMine", "Converts the opponent's HP to half of their HP.", 6, Difficulty.EASY, CardType.RED);
    }

    @Override
    public void action(Player self, Player enemy) {
        self.setMana(self.getMana()-manaUsed);
        enemy.setHp((new Constant(((Constant)(enemy.getHp())).getNumber()/2)));
    }
}