package Gameplay.Cards;

import Gameplay.CardAction.*;
import Gameplay.*;
import Gameplay.Numbers.Constant;

import java.util.ArrayList;

public class Digitalize extends Card implements HaveCondition {
    public Digitalize(){
        super("Digitalize","If the opponent's HP has only 1 or 0, change their HP to base10 number.",4, Difficulty.HARD, CardType.RED);
    }

    @Override
    public boolean checkCondition(Player user, Player receiver) {
        boolean is0or1 = true;
        for (char i:(String.valueOf(((Constant)(receiver.getHp())).getNumber())).toCharArray()){
            if (i == '1' || i == '0' || i == '-'){
                continue;
            }
            is0or1 = false;
            break;
        }
        return is0or1;
    }

    @Override
    public void action(Player self, Player enemy) {
        if (checkCondition(self,enemy)){
            int pow2 = 1;
            int sum = 0;
            char[] enemyHp = (String.valueOf(((Constant)(enemy.getHp())).getNumber())).toCharArray();
            for (int i = enemyHp.length-1;i>=0;i--){
                if (enemyHp[i] == '1'){
                    sum+=pow2;
                }
                pow2*=2;
            }
            if (enemyHp[0] == '-'){
                sum = sum*(-1);
            }
            enemy.setHp(new Constant(sum));
            self.setMana(self.getMana()-this.manaUsed);
        }
    }
    @Override
    public ArrayList<CardAction> getCardAction(Player self, Player enemy) {
        ArrayList<CardAction> arr = new ArrayList<CardAction>();
        Player receiver = this.getReceiver(self,enemy);
        arr.add(new SetMana(self.getMana()-this.getManaUsed(),self.getPlayerNumber()));
        int pow2 = 1;
        int sum = 0;
        char[] enemyHp = (String.valueOf(((Constant)(enemy.getHp())).getNumber())).toCharArray();
        for (int i = enemyHp.length-1;i>=0;i--){
            if (enemyHp[i] == '1'){
                sum+=pow2;
            }
            pow2*=2;
        }
        if (enemyHp[0] == '-'){
            sum = sum*(-1);
        }
        arr.add(new SetHp(new Constant(sum),receiver.getPlayerNumber()));
        return arr;
    }
}
