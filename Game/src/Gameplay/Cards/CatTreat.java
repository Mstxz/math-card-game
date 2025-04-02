package Gameplay.Cards;

import Gameplay.*;

import java.util.ArrayList;

public class CatTreat extends Card implements HaveCondition {
    public CatTreat(){
        super("CatTreat","Increase user mana by 2",0, Difficulty.BABY, CardType.YELLOW);
        //this.picture = "assets/Yellow_BetaCatFood.png";
    }

    @Override
    public boolean checkCondition(Player user, Player receiver) {
        return user.getMana() == 0;
    }

    @Override
    public void action(Player self, Player enemy) {
        if (checkCondition(self,self)){
            self.setMana(self.getMana()+2);
        }
    }

}
