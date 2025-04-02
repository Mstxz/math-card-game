package Gameplay;

import GameSocket.PlayerInfo;
import Gameplay.Numbers.Constant;

import java.util.ArrayList;
import java.util.Scanner;

public class Player implements Cloneable{
    protected String name;
    protected Deck deck = Deck.getDeck();
    protected int mana = 1;
    protected ArrayList<Card> hand = new ArrayList<Card>();
    protected Number hp = new Constant(100);
    protected NumberType numberType = NumberType.CONSTANT;
    protected int maxMana = 1;
    protected String profilePicture;
    protected int playerNumber;

    public Player(String name){
        this.name = name;

    }

    public Player(String name,String profilePicture){
        this.name = name;
        this.profilePicture = profilePicture;
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

    public NumberType getNumberType() {
        return numberType;
    }

    public void setNumberType(NumberType numberType) {
        this.numberType = numberType;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public Card draw(){
        Card c = deck.getCards().removeLast();
        hand.add(c);
        return c;
        //Game.cardGui.updateHand();

    }
    public static ArrayList<Integer> listPlayableCard(Player self, Player enemy){
        ArrayList<Integer> playable = new ArrayList<>();
        for (int i=0;i<self.hand.size();i++){
            Card c = self.hand.get(i);
            //System.out.println(self.getMana()>=c.getManaUsed());
            if (self.getMana()>=c.getManaUsed() && (!(c instanceof HaveCondition) || ((HaveCondition) c).checkCondition(self, c.getReceiver(self,enemy)))){
                playable.add(i);
            }
        }
        return playable;
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
        return playable;
    }
    public Card play(Player self,Player enemy){
        ArrayList<Integer> playable = Player.listPlayableCard(self,enemy);
        Card c = null;
        while (!playable.isEmpty()){
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
            c = hand.remove(index);
            c.action(self,enemy);
            deck.addDispose(c);
            System.out.println(deck.getDispose().getLast().getName());
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
        return c;
    }


    public static Player checkWin(ArrayList<Player> playerList){
        ArrayList<Integer> stillAlive = new ArrayList<>();
        for (int i=0;i<playerList.size();i++){
            stillAlive.add(i);
        }
        for (int lose:checkLose(playerList)){
            stillAlive.remove(lose);
        }
        if(stillAlive.size() != 1) {
            return null;
        }
        return playerList.get(stillAlive.getFirst());
        //return true;
    }


    public static ArrayList<Integer> checkLose(ArrayList<Player> playerList){
        ArrayList<Integer> lose = new ArrayList<>();
        for (Player p:playerList){
            if (((Constant)(p.getHp())).getNumber() == 0 || p.getDeck().getCards().isEmpty()){
                lose.add(p.getPlayerNumber());
            }
        }
        return lose;
    }

    public static ArrayList<Integer> checkLoseHP(ArrayList<Player> playerList){
        ArrayList<Integer> lose = new ArrayList<>();
        for (Player p:playerList){
            if (((Constant)(p.getHp())).getNumber() == 0){
                lose.add(p.getPlayerNumber());
            }
        }
        return lose;
    }



    public static boolean checkWinNonPrint(Player a,Player b){
        return ((Constant) (a.getHp())).getNumber() == 0 || a.getDeck().getCards().isEmpty() || ((Constant) (b.getHp())).getNumber() == 0 || b.getDeck().getCards().isEmpty();
    }

    public void playCard(Player self,Player enemy,int index){
        ArrayList<Integer> playable = self.showCard(self,enemy);
        if (!playable.contains(index)) {
            System.out.println("It's not playable! Choose the white color text (Start from 0)");
            return;
        }
        Card c = hand.remove(index);
        c.action(self,enemy);
        deck.addDispose(c);

    }

    public void startTurn(){
        this.mana = maxMana;
        this.draw();
    }

    public void endTurn(){
        if (maxMana<10){
            maxMana+=1;
        }
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", deck=" + deck +
                ", mana=" + mana +
                ", hand=" + hand +
                ", hp=" + hp +
                ", numberType=" + numberType +
                ", maxMana=" + maxMana +
                ", profilePicture='" + profilePicture + '\'' +
                ", playerNumber=" + playerNumber +
                '}';
    }

    @Override
    protected Object clone() {
        try {
            return (Player)super.clone();
        }
        catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return null;
    }
}
