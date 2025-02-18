package Gameplay;

import Gameplay.Numbers.Constant;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    public String name;
    public Deck deck = new Deck("Test");
    public int mana = 20;
    public ArrayList<Card> hand = new ArrayList<Card>();
    public Number hp = new Constant(100);
    public NumberType numberType = NumberType.CONSTANT;

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

    public void setMana(int mana) {
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

    public void draw(){
        hand.add(deck.getCards().removeLast());
    }
    public void showCard(){
        for (int i = 0;i<hand.size();i++){
            System.out.print(hand.get(i).getName()+" ");
        }
        System.out.println();
    }
    public void play(Player self,Player enemy){
        Scanner sc = new Scanner(System.in);
        Card c = hand.remove(sc.nextInt());
        c.action(self,enemy);
        deck.addDispose(c);
    }
}
