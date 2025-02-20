package Gameplay;

import Gameplay.Numbers.Constant;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    public String name;
    public Deck deck = new Deck("Test");
    public int mana = 1;
    public ArrayList<Card> hand = new ArrayList<Card>();
    public Number hp = new Constant(100);
    public NumberType numberType = NumberType.CONSTANT;
    public int maxMana = 1;
    private int playerNumber;

    public Player(String name){
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public int getMana() {
        return mana;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }

    public void setMana(int mana) {
        if (mana<0){
            this.mana = 0;
            return;
        }
        if (mana>maxMana){
            this.mana = maxMana;
            return;
        }
        this.mana = mana;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    public Number getHp() {
        if (numberType == NumberType.CONSTANT){
            return (Constant)(hp);
        }
        return hp;
    }

    public void setHp(Number hp) {
        this.hp = hp;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public void draw(){
        hand.add(deck.getCards().removeLast());
    }
    public ArrayList<Integer> showCard(Player self, Player enemy){
        System.out.print("This is your hand: ");
        String reset = "\u001B[0m";
        String red = "\u001B[31m";
        ArrayList<Integer> playable = new ArrayList<>();
        for (int i=0;i<hand.size();i++){
            Card c = hand.get(i);
            System.out.print("["+i+"]");
            //System.out.println(self.getMana()>=c.getManaUsed());
            if (self.getMana()>=c.getManaUsed() && (!(c instanceof HaveCondition) || ((HaveCondition) c).checkCondition(self, enemy))){
                System.out.print(c.getName()+"/");
                playable.add(i);
            }
            else {
                System.out.print(red+c.getName()+reset+"/");
            }
        }
        System.out.println();
        return playable;
    }
    public void play(Player self,Player enemy){
        self.draw();
        Player.log(self,enemy);
        ArrayList<Integer> playable = self.showCard(self,enemy);
        while (!playable.isEmpty()&&!Player.checkWin(self,enemy)){
            Scanner sc = new Scanner(System.in);
            int index;
            do {
                System.out.print("Input the number of card: ");
                index = sc.nextInt();
                if (!playable.contains(index)){
                    System.out.println("It's not playable! Choose the white color text (Start from 0)");
                    self.showCard(self,enemy);
                }
            }
            while (!playable.contains(index));
            Card c = hand.remove(index);
            c.action(self,enemy);
            deck.addDispose(c);
            Player.log(self,enemy);
            playable = self.showCard(self,enemy);
            if (!playable.isEmpty()){
                System.out.print("Do you want to end turn (Y/N) : ");
                sc.nextLine();
                char a = sc.nextLine().charAt(0);
                //System.out.println(b);
                if (a == 'Y' || a == 'y'){
                    break;
                }

            }
        }
        System.out.println();
        if (maxMana<10){
            maxMana+=1;
        }

    }
    public static void log(Player self,Player enemy){
        System.out.println(self.getName()+"'s hp ("+self.getPlayerNumber()+") : "+self.getHp());
        System.out.println(self.getName()+"'s mana ("+self.getPlayerNumber()+") : "+self.getMana());
        System.out.println(enemy.getName()+"'s hp ("+enemy.getPlayerNumber()+") : "+enemy.getHp());
        System.out.println(enemy.getName()+"'s mana ("+enemy.getPlayerNumber()+") : "+enemy.getMana());
    }

    public static boolean checkWin(Player a,Player b){
        if (((Constant)(a.getHp())).getNumber() == 0){
            System.out.println("Player "+a.getName()+" has eliminated.");
            System.out.println("Player "+b.getName()+" is victory!!");
        }
        else if (a.getDeck().getCards().isEmpty()){
            System.out.println("Player "+a.getName()+" has 0 card to draw.");
            System.out.println("Player "+b.getName()+" is victory!!");
        } else if (((Constant)(b.getHp())).getNumber() == 0) {
            System.out.println("Player "+b.getName()+" has eliminated.");
            System.out.println("Player "+a.getName()+" is victory!!");
        } else if (b.getDeck().getCards().isEmpty()) {
            System.out.println("Player "+b.getName()+" has 0 card to draw.");
            System.out.println("Player "+a.getName()+" is victory!!");
        }
        else {
            return false;
        }
        return true;
    }

    public static boolean checkWinNonPrint(Player a,Player b){
        return ((Constant) (a.getHp())).getNumber() == 0 || a.getDeck().getCards().isEmpty() || ((Constant) (b.getHp())).getNumber() == 0 || b.getDeck().getCards().isEmpty();
    }
}
