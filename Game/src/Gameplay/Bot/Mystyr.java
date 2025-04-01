package Gameplay.Bot;

import Gameplay.Card;
import Gameplay.CardType;
import Gameplay.Numbers.Constant;
import Gameplay.Player;

import java.util.ArrayList;

public class Mystyr extends Bot {
    private int hpIncrease = 0;
    public Mystyr(){
        super("Mystyr","assets/BotProfile/Bot_Mystyr.webp","She is fear to lose, so she is always wanting to play safe by increase her self HP before everything (Even her signature is about darkness anyway)","Mstxz");
    }

    @Override
    public Card play(Player self, Player enemy) {
        if (hpIncrease>=20){
            hpIncrease = 0;
            return null;
        }
        ArrayList<Integer> playable = Player.listPlayableCard(self,enemy);
        Player.log(self,enemy);
        Card c = null;
        int index = -1;
        String sequence;

        if ((((Constant)(this.getHp())).getNumber()<0)){
            sequence = "Minus";
        }
        else{
            sequence = "Plus";
        }

        if (!playable.isEmpty()){
            for (int i:playable){
                if (getHand().get(i).getType() == CardType.BLUE || getHand().get(i).getName().contains(sequence)){
                    index = i;
                    break;
                }
                if (index == -1){
                    index = i;
                }
            }
            c = this.getHand().remove(index);
            Constant oldHp = (Constant) getHp();
            c.action(self,enemy);
            int tmp = Math.abs(((Constant)getHp()).getNumber())-Math.abs(oldHp.getNumber());
            hpIncrease+=tmp;
            System.out.println(this.getName()+" play "+c.getName()+" to "+c.getReceiver(self,enemy).getName());
            this.getDeck().addDispose(c);
            Player.log(self,enemy);
//            playable = self.showCard(self,enemy);
            System.out.println();
        }

        System.out.println();

        System.out.println(c);
        if (c==null){
            hpIncrease = 0;
        }
        return c;
    }

    @Override
    public Player getTargetId(Player self, Player enemy, Card c) {
        String sequence;

        if ((((Constant)(this.getHp())).getNumber()<0)){
            sequence = "Minus";
        }
        else{
            sequence = "Plus";
        }

        if (c.getName().contains(sequence)){
            return self;
        }

        int ran = (int) ((Math.random())*2)%2;
        if (ran == 0){
            return self;
        }
        return enemy;
    }
}
