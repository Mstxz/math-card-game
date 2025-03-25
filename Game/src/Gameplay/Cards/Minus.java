package Gameplay.Cards;

import GUI.CardAction;
import GUI.CardActionType;
import Gameplay.Card;
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
        arr.add(new CardAction(CardActionType.SET_HP,getReceiver(self,enemy).getPlayerNumber()));
        return arr;
    }
}
