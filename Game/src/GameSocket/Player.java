package GameSocket;

import Gameplay.Number;
import Gameplay.*;
import Gameplay.Numbers.Constant;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    public String name;
    public Deck deck = new Deck("Test");
    public int mana = 1;
    public ArrayList<Card> hand = new ArrayList<Card>();
    public Gameplay.Number hp = new Constant(100);
    public NumberType numberType = NumberType.CONSTANT;
    public int maxMana = 1;

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
        if (this.mana<0){
            this.mana = 0;
            return;
        }
        if (this.mana>mana){
            this.mana = maxMana;
        }
        this.mana = mana;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    public Gameplay.Number getHp() {
        if (numberType == NumberType.CONSTANT){
            return (Constant)(hp);
        }
        return hp;
    }

    public void setHp(Number hp) {
        this.hp = hp;
    }

    public void draw(){
        hand.add(deck.getCards().removeLast());
    }

    public void play(Player self,Player enemy){

        System.out.println();
        if (maxMana<10){
            maxMana+=1;
        }

    }
    public static void log(Player self,Player enemy){
        System.out.println(self.getName()+"'s hp : "+self.getHp());
        System.out.println(self.getName()+"'s mana : "+self.getMana());
        System.out.println(enemy.getName()+"'s hp : "+enemy.getHp());
        System.out.println(enemy.getName()+"'s mana : "+enemy.getMana());
    }
}
