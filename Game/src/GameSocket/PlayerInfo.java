package GameSocket;

import Gameplay.Card;
import Gameplay.Number;
import Gameplay.NumberType;
import Gameplay.Numbers.Constant;
import Gameplay.Player;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

public class PlayerInfo extends Player {
    private boolean isReady;
    private int cardsInHand;

    public PlayerInfo() {
        this("","assets/icon.png",0,0,false);
    }

    public PlayerInfo(String name, String profilePicture, int hp, int playerNumber,boolean isReady) {
        super(name,profilePicture);
        this.isReady = isReady;
        this.cardsInHand = 0;
        this.hp = new Constant(hp);
        this.playerNumber = playerNumber;
    }


    public PlayerInfo(String name, String profilePicture, int hp, int playerNumber,boolean isReady,int cardsInHand) {
        this(name,profilePicture,hp,playerNumber,isReady);
        this.cardsInHand = cardsInHand;
        for (int i = 0; i < cardsInHand; i++) {
            this.hand.add(null);
        }
    }

    public PlayerInfo(String name, String profilePicture, int hp, int playerNumber,boolean isReady,int cardsInHand, ArrayList<Card> hand) {
        this(name,profilePicture,hp,playerNumber,isReady,cardsInHand);
        this.hand = hand;
    }


    public byte[] encodeBytes(){
        ByteBuffer bf = ByteBuffer.allocate(24+name.getBytes(StandardCharsets.UTF_8).length+profilePicture.getBytes(StandardCharsets.UTF_8).length);
        bf.putInt(playerNumber);
        bf.putInt(100);
        bf.putInt(name.getBytes(StandardCharsets.UTF_8).length);
        bf.put(name.getBytes(StandardCharsets.UTF_8));
        bf.putInt(profilePicture.getBytes(StandardCharsets.UTF_8).length);
        bf.put(profilePicture.getBytes(StandardCharsets.UTF_8));
        bf.putInt((isReady ? 1: 0));
        bf.putInt(cardsInHand);
        return bf.array();
    }

    public byte[] encodeBytesIncludeHand(){
        int handLength = 0;
        for (Card card:hand){
            handLength += 4 + card.encode().length;
        }
        ByteBuffer bf = ByteBuffer.allocate(24+name.getBytes(StandardCharsets.UTF_8).length+profilePicture.getBytes(StandardCharsets.UTF_8).length + handLength);
        bf.putInt(playerNumber);
        bf.putInt(100);
        bf.putInt(name.getBytes(StandardCharsets.UTF_8).length);
        bf.put(name.getBytes(StandardCharsets.UTF_8));
        bf.putInt(profilePicture.getBytes(StandardCharsets.UTF_8).length);
        bf.put(profilePicture.getBytes(StandardCharsets.UTF_8));
        bf.putInt((isReady ? 1: 0));
        bf.putInt(cardsInHand);
        for (Card card:hand){
            bf.putInt(card.encode().length);
            bf.put(card.encode());
        }
        return bf.array();
    }

    public static PlayerInfo decodeBytes(byte[] bytes){
        if (bytes.length == 0) {
            return null;
        }
        try (DataInputStream in = new DataInputStream(new ByteArrayInputStream(bytes))){
            int byteRead = 0;
            int playerID = in.readInt();
            int playerHP = in.readInt();
            int nameByte = in.readInt();
            String playerName = new String(in.readNBytes(nameByte),StandardCharsets.UTF_8);
            int profilePictureByte = in.readInt();
            String profilePicture = new String(in.readNBytes(profilePictureByte),StandardCharsets.UTF_8);
            int ready = in.readInt();
            boolean isReady = false;
            if (ready == 1){
                isReady = true;
            }
            int cardInHand = in.readInt();
            byteRead = nameByte + profilePictureByte + 24;
            if (byteRead == bytes.length){
                return new PlayerInfo(playerName,profilePicture,playerHP,playerID,isReady,cardInHand);
            }
            ArrayList<Card> cards = new ArrayList<>();
            while (byteRead != bytes.length){
                int cardBytes = in.readInt();
                byteRead += 4;
                Card card = Card.decode(Arrays.copyOfRange(bytes,byteRead,byteRead + cardBytes));
                cards.add(card);
                byteRead += cardBytes;
                in.skipNBytes(cardBytes);
            }
            return new PlayerInfo(playerName,profilePicture,playerHP,playerID,isReady,cardInHand,cards);
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return new PlayerInfo("","assets/icon.png",0,0,false);
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getCardsInHand() {
        return cardsInHand;
    }

    public void setCardsInHand(int cardsInHand) {
        this.cardsInHand = cardsInHand;
    }

    public Number getHp() {
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

    public int getMaxMana() {
        return maxMana;
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }

    public int getPlayerID() {
        return playerNumber;
    }

    public void setPlayerID(int playerNumber) {
        this.playerNumber = playerNumber;
    }


    public boolean isReady() {
        return isReady;
    }

    public void setReady(boolean ready) {
        isReady = ready;
    }

    @Override
    public int getPlayerNumber() {
        return playerNumber;
    }

    @Override
    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    @Override
    public String toString() {
        return "PlayerInfo{" +
                "isReady=" + isReady +
                ", name='" + name + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                ", mana=" + mana +
                ", cardsInHand=" + cardsInHand +
                ", hp=" + hp +
                ", numberType=" + numberType +
                ", maxMana=" + maxMana +
                ", playerNumber=" + playerNumber +
                ", deck" + deck.getCards() +
                ", hand=" + hand +
                '}';
    }
}
