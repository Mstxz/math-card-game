package Gameplay;

import java.util.ArrayList;

public class Bot extends Player{
    public Bot(){
        super("Bot1","assets/icon.png");
    }

    @Override
    public ArrayList<Integer> showCard(Player self, Player enemy) {
        ArrayList<Integer> playable = new ArrayList<>();
        for (int i=0;i<this.getHand().size();i++){
            Card c = this.getHand().get(i);
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
    public Card play(Player self, Player enemy) {
        ArrayList<Integer> playable = Player.listPlayableCard(self,enemy);
        Player.log(self,enemy);
        Card c = null;
        int index;
        //Bring this condition to bot while loop
        if (!playable.isEmpty()&& Player.checkWin(self,enemy) == null){
            index = playable.get(((int)(Math.random() * playable.size())));
            c = this.getHand().remove(index);
            c.action(self,enemy);
            System.out.println(this.getName()+" play "+c.getName()+" to "+c.getReceiver(self,enemy).getName());
            this.getDeck().addDispose(c);
            Player.log(self,enemy);
//            playable = self.showCard(self,enemy);
            System.out.println();
        }

        System.out.println();

        System.out.println(c);
        return c;
    }
}
