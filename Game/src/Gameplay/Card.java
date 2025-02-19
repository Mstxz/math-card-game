package Gameplay;

import Gameplay.Cards.*;

import java.util.Scanner;

public abstract class Card {
    protected String name;
    protected String description;
    protected int manaUsed;
    protected Difficulty difficult;
    protected CardType type;
    protected String picture;

    public Card(String name, String description, int manaUsed, Difficulty difficult, CardType type) {
        this.name = name;
        this.description = description;
        this.manaUsed = manaUsed;
        this.difficult = difficult;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public static Card createCard(String name,int number){
        switch (name){
            case "Plus":
                return new Plus(number);
            case "Minus":
                return new Minus(number);
        }
        return null;
    }
    public static Card createCard(String name){
        switch (name){
            case "Absolute":
                return new Absolute();
            case "CatNap":
                return new CatNap();
            case "CatTreat":
                return new CatTreat();
            case "FlipSigned":
                return new FlipSigned();
            case "Sqrt":
                return new Sqrt();
        }
        return null;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getManaUsed() {
        return manaUsed;
    }

    public void setManaUsed(int manaUsed) {
        this.manaUsed = manaUsed;
    }

    public Difficulty getDifficult() {
        return difficult;
    }

    public void setDifficulty(Difficulty difficult) {
        this.difficult = difficult;
    }

    public CardType getType() {
        return type;
    }

    public void setType(CardType type) {
        this.type = type;
    }

    public Player getReciever(Player self,Player enemy){
        if (this.type == CardType.RED){
            return enemy;
        }
        else if (this.type == CardType.BLUE){
            return self;
        }
        else{
            System.out.print("Select Opponent : ");
            Scanner sc = new Scanner(System.in);
            String name = sc.nextLine();
            if (name.equals(self.getName())){
                return self;
            }
            return enemy;
        }
    }

    public abstract void action(Player self,Player enemy);
}
