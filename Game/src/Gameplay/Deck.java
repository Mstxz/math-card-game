package Gameplay;

import Gameplay.Cards.Plus;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Deck {
    private String name;
    private ArrayList<Card> cards;
    private ArrayList<Card> dispose;
    private Difficulty hardestDifficulty;

    public static Deck LoadDeck(String name) throws FileNotFoundException{
        File deckFile = new File("Assets/"+name+".deck");
        Deck d = new Deck(name);
        try (Scanner sc = new Scanner(deckFile)){

            while (sc.hasNextLine()){
                String card = sc.nextLine();
                String info[] = card.split(" ");
                Card c;
                if (info[0].equals("Plus")||info[0].equals("Minus")){
                    c = Card.createCard(info[0],Integer.parseInt(info[1]),info[2]);
                    for (int i=0;i<Integer.parseInt(info[3]);i++){
                        d.addCard(c);
                    }
                }
                else if (info[0].equals("FlipSigned")){
                    c = Card.createCard(info[0],info[1]);
                    for (int i=0;i<Integer.parseInt(info[2]);i++){
                        d.addCard(c);
                    }
                }
                else {
                    c = Card.createCard(info[0]);
                    for (int i = 0;i<Integer.parseInt(info[info.length-1]);i++){
                        d.addCard(c);
                    }
                }
            }
        }
        catch (FileNotFoundException e){
            throw e;
        }
        return d;
    }

    public static Deck LoadDeckFromPath(String deckPath) throws FileNotFoundException{
        File deckFile = new File(deckPath);
        Deck d = new Deck(deckFile.getName());
        try {
            Scanner sc = new Scanner(deckFile);
            while (sc.hasNextLine()){
                String card = sc.nextLine();
                String info[] = card.split(" ");
                Card c;
                if (info[0].equals("Plus")||info[0].equals("Minus")){
                    c = Card.createCard(info[0],Integer.parseInt(info[1]),info[2]);
                    for (int i=0;i<Integer.parseInt(info[3]);i++){
                        d.addCard(c);
                    }
                }
                else if (info[0].equals("FlipSigned")){
                    c = Card.createCard(info[0],info[1]);
                    for (int i=0;i<Integer.parseInt(info[2]);i++){
                        d.addCard(c);
                    }
                }
                else {
                    c = Card.createCard(info[0]);
                    for (int i = 0;i<Integer.parseInt(info[info.length-1]);i++){
                        d.addCard(c);
                    }
                }
            }
        }
        catch (FileNotFoundException e){
            throw e;
        }
        return d;
    }

    public static Deck getDeck(){
        Deck d = new Deck("Default");
        for (int i = 0; i < 40; i++) {
            d.addCard(null);
        }
        return d;
    }

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
