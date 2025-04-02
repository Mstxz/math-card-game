package Gameplay.Bot;

import Gameplay.Card;
import Gameplay.CardType;
import Gameplay.Cards.AngryCat;
import Gameplay.Cards.CatClown;
import Gameplay.Cards.CatNap;
import Gameplay.Cards.SleepyCat;
import Gameplay.Numbers.Constant;
import Gameplay.Player;

import java.util.ArrayList;
import java.util.Arrays;

public class Arsr extends Bot{
    private int turnCount = 1;
    private int playerTest = 0;

    private boolean isRealPlay = false;
    private int realTarget;

    private boolean isClumsy = false;
    public Arsr(){
        super("Arsr","assets/BotProfile/Bot_Arsr.webp","He's a weird cat that like a math too much. But he's very clumsy, If we tried, I think we're winnable! (He has a big crush on Pupr, Or is it his weakness!?)","Arsr");
    }

    @Override
    public Card play(Player self, Player enemy) {
        if (isClumsy){
            isClumsy = false;
            turnCount+=1;
            return null;
        }
        ArrayList<Integer> playable = Player.listPlayableCard(self,enemy);
        Player.log(self,enemy);
        Card c = null;
        //Bring this condition to bot while loop
        if (!playable.isEmpty()) {
            if (turnCount%3 == 0){
                isClumsy = true;
                System.out.println("Clumsy turn");
                int index = (int)(Math.random()*playable.size());
                c = this.getHand().remove((int) playable.get(index));
                realTarget = (int)(Math.random()*2);
                isRealPlay = true;
                c.action(self, enemy);

                System.out.println(this.getName() + " play " + c.getName() + " to " + c.getReceiver(self, enemy).getName());
                this.getDeck().addDispose(c);
                Player.log(self, enemy);
//            playable = self.showCard(self,enemy);
                System.out.println();
                return c;
            }
            int index = -1;
            String sequence;

            int selfMana = self.getMana();
            int selfHp = ((Constant) (self.getHp())).getNumber();
            int enemyHp = ((Constant) (enemy.getHp())).getNumber();

            int mostValue = 0;

            if (!playable.isEmpty()) {
                isRealPlay = false;
                for (int i : playable) {
                    Card tmp = self.getHand().get(i);
                    if (tmp instanceof SleepyCat || tmp instanceof CatNap || tmp instanceof AngryCat || tmp instanceof CatClown) {
                        index = i;
                        mostValue = 100;
                        break;
                    }
                    int compare;
                    int selfDiff;
                    int enemyDiff;
                    if (tmp.getType().equals(CardType.GREEN)){
                        playerTest = 0; // play to self
                        tmp.action(self, enemy);
                        selfDiff = Math.abs(((Constant) (self.getHp())).getNumber()) - Math.abs(selfHp);
                        resetSimulate(self,enemy,selfMana,selfHp,enemyHp);
                        playerTest = 1; // play to enemy
                        tmp.action(self,enemy);
                        enemyDiff = Math.abs(enemyHp)-Math.abs(((Constant) (enemy.getHp())).getNumber());
                        if (((Constant)(enemy.getHp())).getNumber()==0){
                            index = i;
                            realTarget = 1;
                            resetSimulate(self,enemy,selfMana,selfHp,enemyHp);
                            break;
                        }
                        if (selfDiff>enemyDiff && selfDiff>=0){
                            compare = selfDiff;
                        }
                        else {
                            compare = enemyDiff;
                        }
                        resetSimulate(self,enemy,selfMana,selfHp,enemyHp);
                    } else if (tmp.getType().equals(CardType.YELLOW)) {
                        if (index == -1){
                            index = i;
                        }
                        continue;
                    } else {
                        tmp.action(self,enemy);
                        selfDiff = Math.abs(((Constant) (self.getHp())).getNumber()) - Math.abs(selfHp);
                        enemyDiff = Math.abs(enemyHp) - Math.abs(((Constant) (enemy.getHp())).getNumber());
                        if (enemyDiff > 0) {
                            compare = enemyDiff;
                        } else if (selfDiff>0){
                            compare = selfDiff;
                        }
                        else {
                            compare = -100;
                        }
                    }
                    if (((Constant)(enemy.getHp())).getNumber()==0){
                        index = i;
                        self.setMana(selfMana);
                        self.setHp(new Constant(selfHp));
                        enemy.setHp(new Constant(enemyHp));
                        break;
                    }
                    if (compare > mostValue) {
                        mostValue = compare;
                        index = i;
                        if (getHand().get(i).getType().equals(CardType.GREEN)){
                            if (selfDiff>enemyDiff && selfDiff>=0){
                                realTarget = 0;
                            }
                            else {
                                realTarget = 1;
                            }
                        }
                    }

                    resetSimulate(self,enemy,selfMana,selfHp,enemyHp);

                }
                if (mostValue == 0){
                    System.out.println("Not Playing, value = 0");
                    System.out.println("Arsr: "+self.getHand().toString());
                    System.out.println("Arsr: "+playable.toString());
                    return null;
                }
                isRealPlay = true;
                resetSimulate(self,enemy,selfMana,selfHp,enemyHp);
                System.out.println("Arsr: "+self.getHand().toString());
                System.out.println("Arsr: "+playable.toString());
                c = this.getHand().remove(index);
                c.action(self, enemy);

                System.out.println(this.getName() + " play " + c.getName() + " to " + c.getReceiver(self, enemy).getName());
                this.getDeck().addDispose(c);
                //Player.log(self, enemy);
//            playable = self.showCard(self,enemy);
                System.out.println();
            }
        }
        System.out.println();

        System.out.println(c);
        if (c == null){
            turnCount+=1;
        }
        return c;
    }

    public void resetSimulate(Player self,Player enemy,int selfMana,int selfHp,int enemyHp){
        self.setMana(selfMana);
        self.setHp(new Constant(selfHp));
        enemy.setHp(new Constant(enemyHp));
    }

    @Override
    public Player getTargetId(Player self, Player enemy, Card c) {
        if (isRealPlay){
            System.out.println(realTarget);
            if (realTarget == 0){
                return self;
            }
            else {
                return enemy;
            }
        }
        if (playerTest == 0){
            return self;
        }
        return enemy;
    }
}
