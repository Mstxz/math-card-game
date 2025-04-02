package Gameplay.Bot;

import Gameplay.Card;
import Gameplay.CardType;
import Gameplay.Numbers.Constant;
import Gameplay.Player;

import java.util.ArrayList;

public class Who extends Bot{
    private boolean endTurn = false;
    public Who(){
        super("Who","assets/BotProfile/Bot_Who.webp","We don't know who is him, but Everyone know that he lives at 43 park. (Actually he's not a stray cat)","Who");
    }

    @Override
    public Card play(Player self, Player enemy) {
        if (endTurn){
            endTurn = false;
            return null;
        }
        ArrayList<Integer> playable = Player.listPlayableCard(self,enemy);
        Card c = null;
        //Bring this condition to bot while loop
        if (!playable.isEmpty()) {
            int index = -1;
            String sequence;

            int selfMana = self.getMana();
            int selfHp = ((Constant) (self.getHp())).getNumber();
            int enemyHp = ((Constant) (enemy.getHp())).getNumber();

            int mostValue = 0;

            if ((((Constant) (enemy.getHp())).getNumber() > 0)) {
                sequence = "Minus";
            } else {
                sequence = "Plus";
            }

            if (!playable.isEmpty()) {
                for (int i : playable) {
                    Card tmp = self.getHand().get(i);
                    if (index == -1) {
                        index = i;
                    }
                    if (tmp.getType().equals(CardType.YELLOW)) {
                        continue;
                    }
                    tmp.action(self, enemy);
                    int selfDiff = Math.abs(((Constant) (self.getHp())).getNumber()) - Math.abs(selfHp);
                    int enemyDiff = Math.abs(enemyHp)-Math.abs(((Constant) (enemy.getHp())).getNumber());
                    int compare;

                    if (enemyDiff != 0) {
                        compare = enemyDiff+10;
                    } else {
                        compare = selfDiff;
                    }

                    if (((Constant)(enemy.getHp())).getNumber()==0){
                        index = i;
                        self.setMana(selfMana);
                        self.setHp(new Constant(selfHp));
                        enemy.setHp(new Constant(enemyHp));
                        break;
                    }
                    if (mostValue == 0) {
                        mostValue = compare;
                        index = i;
                    } else if (compare > mostValue) {
                        mostValue = compare;
                        index = i;
                    }

                    self.setMana(selfMana);
                    self.setHp(new Constant(selfHp));
                    enemy.setHp(new Constant(enemyHp));
                }

                c = this.getHand().remove(index);

                Constant oldHp = (Constant) getHp();
                Constant enemyOldHp = (Constant)enemy.getHp();
                c.action(self, enemy);
                int tmp = Math.abs(((Constant)getHp()).getNumber())-Math.abs(oldHp.getNumber());
                if (tmp<0){
                    endTurn = true;
                }
                tmp = Math.abs(((Constant)enemy.getHp()).getNumber())-Math.abs(enemyOldHp.getNumber());
                if (tmp>0){
                    endTurn = true;
                }


                this.getDeck().addDispose(c);
            }
        }

        return c;

    }

    @Override
    public Player getTargetId(Player self, Player enemy, Card c) {
        String sequence;

        if ((((Constant)(enemy.getHp())).getNumber()>0)){
            sequence = "Minus";
        }
        else{
            sequence = "Plus";
        }

        if (c.getName().contains(sequence)){
            return self;
        }

        return self;
    }
}
