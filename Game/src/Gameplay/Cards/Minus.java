package Gameplay.Cards;

import Gameplay.CardAction.CardAction;
import Gameplay.Card;
import Gameplay.CardAction.SetHp;
import Gameplay.CardAction.SetMana;
import Gameplay.CardType;
import Gameplay.Difficulty;
import Gameplay.Numbers.Constant;
import Gameplay.Player;

import java.util.ArrayList;

public class Minus extends Card {
    private int number;
    public Minus(int number,CardType type){
        super("Minus "+number,"Subtract "+number+" to hp",1, Difficulty.EASY, type);
        this.number = number;
        this.picture = "assets/Card/"+getFolder()+"/"+ getFolder() +"_HP-"+number+".png";
        if (type==CardType.GREEN){
            manaUsed+=1;
        }
    }

    @Override
    public void action(Player self, Player enemy) {
        self.setMana(self.getMana()-this.getManaUsed());
        Player receiver = this.getReceiver(self,enemy);
        receiver.setHp(receiver.getHp().subtract(new Constant(this.number)));
    }

    @Override
    public ArrayList<CardAction> getCardAction(Player self, Player enemy) {
        ArrayList<CardAction> arr = new ArrayList<CardAction>();
        Player receiver = this.getReceiver(self,enemy);
        arr.add(new SetMana(self.getMana()-this.getManaUsed(),self));
        arr.add(new SetHp(receiver.getHp().subtract(new Constant(this.number)),receiver));
        return arr;
    }
}
