package Gameplay.Cards;


import Gameplay.CardAction.*;
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
        self.setHp(((Constant)(self.getHp())).multiply(-1));
    }

    @Override
    public ArrayList<CardAction> getCardAction(Player self, Player enemy) {
        ArrayList<CardAction> arr = new ArrayList<CardAction>();

        arr.add(new SetMana(self.getMana()-this.getManaUsed(),self.getPlayerNumber()));
        arr.add(new SetHp(((Constant)(self.getHp())).multiply(-1),self.getPlayerNumber()));
        return arr;
    }

}
