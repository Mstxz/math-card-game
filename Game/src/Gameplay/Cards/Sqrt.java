package Gameplay.Cards;

import Gameplay.Card;
import Gameplay.CardType;
import Gameplay.Difficulty;
import Gameplay.Number;
import Gameplay.Numbers.Constant;
import Gameplay.Player;

public class Sqrt extends Card {
    public Sqrt(){
        super("Sqrt(HP)","Sqare root opponent HP",7, Difficulty.MEDIUM, CardType.RED);
    }

    @Override
    public void action(Player self, Player enemy) {
        Player receiver = this.getReciever(self,enemy);
        self.setMana(self.getMana()-this.manaUsed);
        receiver.setHp(new Constant((int)(Math.sqrt(((Constant)(receiver.getHp())).getNumber()))));
    }
}
