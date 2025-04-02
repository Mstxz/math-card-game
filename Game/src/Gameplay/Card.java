package Gameplay;

import Gameplay.Bot.Bot;
import Gameplay.Cards.*;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

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

    public static Card createCard(String name,String type){
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
            case "FlipSigned":
                return new FlipSigned(cardType);
        }
        return Card.createCard(name);
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
    public byte[] encode(){
        byte[] nameBytes = getClass().getSimpleName().getBytes(StandardCharsets.UTF_8);
        ByteBuffer bf = ByteBuffer.allocate(8+nameBytes.length);
        bf.putInt(2);
        bf.putInt(type.ordinal());
        bf.put(nameBytes);
        return bf.array();
    }

    public static Card decode(byte[] bytes){
        try (DataInputStream in = new DataInputStream(new ByteArrayInputStream(bytes))){
            int segmentCount = in.readInt();
            if (segmentCount == 2){
                int cardTypeNumber = in.readInt();
                CardType cardType = CardType.values()[cardTypeNumber];
                String cardName = new String(in.readAllBytes(),StandardCharsets.UTF_8);
                return Card.createCard(cardName,cardType.toString());
            }
            else if (segmentCount == 3){
                int cardTypeNumber = in.readInt();
                CardType cardType = CardType.values()[cardTypeNumber];
                int cardNumber = in.readInt();
                String cardName = new String(in.readAllBytes(),StandardCharsets.UTF_8);
                return Card.createCard(cardName,cardNumber,cardType.toString());
            }

        }
        catch(IOException e){
            e.printStackTrace();
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

    public Player getReceiver(Player self, Player enemy){
        if (this.type == CardType.RED){
            return enemy;
        }
        else if (this.type == CardType.BLUE){
            return self;
        }
        else{
            if (self instanceof Bot) {
                return ((Bot)(self)).getTargetId(self,enemy,this);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card card)) return false;
        return manaUsed == card.manaUsed && Objects.equals(name, card.name) && type == card.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, manaUsed, type);
    }

    public abstract void action(Player self, Player enemy);
}
