package Gameplay.Bot;

import Gameplay.Card;
import Gameplay.Cards.Minus;
import Gameplay.Cards.Plus;
import Gameplay.Deck;
import Gameplay.HaveCondition;
import Gameplay.Player;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Pupr extends Bot {
    public Pupr(){
        super("Pupr","assets/BotProfile/Bot_Pupr.webp","Can you teach me how to play this game?","a");
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
        if (!playable.isEmpty()){
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

    @Override
    public Player getTargetId(Player self, Player enemy,Card c) {
        if (c instanceof Plus){
            return self;
        }
        if (c instanceof Minus){
            return enemy;
        }
        return enemy;
    }
}
