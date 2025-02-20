package Gameplay;

import java.util.ArrayList;
import java.util.Scanner;

public class Bot extends Player{
    public Bot(){
        super("Bot1");
    }

    @Override
    public ArrayList<Integer> showCard(Player self, Player enemy) {
        ArrayList<Integer> playable = new ArrayList<>();
        for (int i=0;i<hand.size();i++){
            Card c = hand.get(i);
            System.out.print("["+i+"]");
            //System.out.println(self.getMana()>=c.getManaUsed());
            if (self.getMana()>=c.getManaUsed() && (!(c instanceof HaveCondition) || ((HaveCondition) c).checkCondition(self, enemy))){
                playable.add(i);
            }
        }
        System.out.println();
        return playable;
    }

    @Override
    public void play(Player self, Player enemy) {
        self.draw();
        ArrayList<Integer> playable = self.showCard(self,enemy);
        Player.log(self,enemy);
        int index;
        while (!playable.isEmpty()&&!Player.checkWin(self,enemy)){
            index = playable.get(((int)(Math.random() * playable.size())));
            Card c = hand.remove(index);
            c.action(self,enemy);
            System.out.println(this.getName()+" play "+c.getName()+" to "+c.getReciever(self,enemy).getName());
            deck.addDispose(c);
            Player.log(self,enemy);
            playable = self.showCard(self,enemy);
            System.out.println();
        }
        System.out.println();
        if (maxMana<10){
            maxMana+=1;
        }
    }
}
