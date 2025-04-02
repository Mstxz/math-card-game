package Gameplay.Cards;


import Gameplay.Card;
import Gameplay.CardType;
import Gameplay.Difficulty;
import Gameplay.Numbers.Constant;
import Gameplay.Player;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class FlipSigned extends Card {
    public FlipSigned(CardType cardType){
        super("FlipSigned","multiply -1 to your HP",2, Difficulty.MEDIUM, cardType);
        if (type==CardType.GREEN){
            manaUsed+=1;
        }
        //this.picture = "assets/Blue_BetaFlipSignedHP.png";
    }

    @Override
    public void action(Player self, Player enemy) {
        self.setMana(self.getMana()-this.manaUsed);
        getReceiver(self,enemy).setHp(((Constant)(getReceiver(self,enemy).getHp())).multiply(-1));
    }


}
