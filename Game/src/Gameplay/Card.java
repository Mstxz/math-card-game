package Gameplay;

import GUI.CardAction;
import Gameplay.Cards.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
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
        this.picture = "assets/Card/"+getFolder()+"/"+getFolder()+"_"+name+".png";
    }
    public String getFolder(){
        if (type == CardType.BLUE){
            return "Blue";
        }
        if (type == CardType.RED){
            return "Red";
        }
        if (type == CardType.GREEN){
            return "Green";
        }
        return "Yellow";
    }
    public String getName() {
        return name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public static Card createCard(String name, int number,String type){
        CardType cardType = CardType.YELLOW;
        switch (type){
            case "Blue":
                cardType = CardType.BLUE;
                break;
            case "Red":
                cardType = CardType.RED;
                break;
            case "Green":
                cardType = CardType.GREEN;
        }
        switch (name){
            case "Plus":
                return new Plus(number,cardType);
            case "Minus":
                return new Minus(number,cardType);
        }
        return null;
    }
    public static Card createCard(String name){
        try {
            Class<?> cclass = Class.forName("Gameplay.Cards."+name);
            return (Card) cclass.newInstance();
        }
        catch (ClassNotFoundException | IllegalAccessException | InstantiationException e){
            e.printStackTrace();
        }
        return null;
//        switch (name){
//            case "Absolute":
//                return new Absolute();
//            case "CatNap":
//                return new CatNap();
//            case "CatTreat":
//                return new CatTreat();
//            case "FlipSigned":
//                return new FlipSigned();
//            case "Sqrt":
//                return new Sqrt();
//            case "Copycat":
//                return new Copycat();
//            case "Digitalize":
//                return new Digitalize();
//        }
//        return null;
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

    public Player getReceiver(Player self, Player enemy){
        if (this.type == CardType.RED){
            return enemy;
        }
        else if (this.type == CardType.BLUE){
            return self;
        }
        else{
            if (self instanceof Bot) {
                if (this instanceof Plus){
                    return self;
                }
                return enemy;
            }
            return enemy;
//            System.out.print("Select Opponent : ");
//            Scanner sc = new Scanner(System.in);
//            int index = sc.nextInt();
//            if (index == 0 || index == 1){
//                return Game.getPlayer(index);
//            }
//            return null;
        }

    }

    public abstract void action(Player self,Player enemy);
    public ArrayList<CardAction> getCardAction(Player self,Player enemy){
        return null;
    }
}
