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
        super("Pupr","assets/BotProfile/Bot_Pupr.webp","Can you teach me how to play this game?","Pupr");
    }

    @Override
    public ArrayList<Integer> showCard(Player self, Player enemy) {
        ArrayList<Integer> playable = new ArrayList<>();
        for (int i=0;i<this.getHand().size();i++){
            Card c = this.getHand().get(i);
            //System.out.println(self.getMana()>=c.getManaUsed());
            if (self.getMana()>=c.getManaUsed() && (!(c instanceof HaveCondition) || ((HaveCondition) c).checkCondition(self, enemy))){
                playable.add(i);
            }
        }

        return playable;
    }

    @Override
    public Card play(Player self, Player enemy) {
        ArrayList<Integer> playable = Player.listPlayableCard(self,enemy);
        Card c = null;
        int index;
        //Bring this condition to bot while loop
        if (!playable.isEmpty()){
            index = playable.get(((int)(Math.random() * playable.size())));
            c = this.getHand().remove(index);
            c.action(self,enemy);

            this.getDeck().addDispose(c);
        }


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
