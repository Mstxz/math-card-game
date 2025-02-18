package Gameplay;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Deck {
    private String name;
    private ArrayList<Card> cards;
    private ArrayList<Card> dispose;
    private Difficulty hardestDifficulty;

    public Deck(String name){
        this.name = name;
        this.cards = new ArrayList<Card>();
        this.dispose = new ArrayList<Card>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public ArrayList<Card> getDispose() {
        return dispose;
    }

    public void setDispose(ArrayList<Card> dispose) {
        this.dispose = dispose;
    }

    public Difficulty getHardestDifficulty() {
        return hardestDifficulty;
    }

    public void setHardestDifficulty(Difficulty hardestDifficulty) {
        this.hardestDifficulty = hardestDifficulty;
    }

    public void addCard(Card newCard){
        this.cards.add(newCard);
    }
    public void addDispose(Card disposeCard){
        this.dispose.add(disposeCard);
    }
    public void shuffle(){
        Collections.shuffle(this.cards);
    }


}
