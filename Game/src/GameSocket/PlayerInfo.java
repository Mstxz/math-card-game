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
    private int cardsInDeck;

    public PlayerInfo() {
        this("","assets/icon.png",new Constant(100),1,1,0,false);
    }

    public PlayerInfo(String name, String profilePicture, Number hp, int mana, int maxMana, int playerNumber,boolean isReady) {
        super(name,profilePicture);
        this.isReady = isReady;
        this.cardsInHand = 0;
        this.hp = hp;
        this.mana = mana;
        this.maxMana = maxMana;
        this.playerNumber = playerNumber;
    }


    public PlayerInfo(String name, String profilePicture, Number hp, int mana, int maxMana, int playerNumber,boolean isReady,int cardsInDeck,int cardsInHand) {
        this(name,profilePicture,hp, mana, maxMana,playerNumber,isReady);
        this.cardsInHand = cardsInHand;
        this.cardsInDeck = cardsInDeck;
        for (int i = 0; i < cardsInHand; i++) {
            this.hand.add(null);
        }
        this.deck.getCards().clear();
        for (int i = 0; i < cardsInDeck; i++){
            this.deck.getCards().add(null);
        }
    }

    public PlayerInfo(String name, String profilePicture, Number hp, int mana, int maxMana, int playerNumber,boolean isReady,int cardsInDeck,int cardsInHand, ArrayList<Card> hand) {
        this(name,profilePicture,hp, mana, maxMana,playerNumber,isReady,cardsInDeck,cardsInHand);
        this.hand = hand;
    }


    public byte[] encodeBytes(){
        ByteBuffer bf = ByteBuffer.allocate(36 +hp.encodedBytes().length + name.getBytes(StandardCharsets.UTF_8).length+profilePicture.getBytes(StandardCharsets.UTF_8).length);
        bf.putInt(playerNumber);
        bf.putInt(hp.encodedBytes().length);
        bf.put(hp.encodedBytes());
        bf.putInt(mana);
        bf.putInt(maxMana);
        bf.putInt(name.getBytes(StandardCharsets.UTF_8).length);
        bf.put(name.getBytes(StandardCharsets.UTF_8));
        bf.putInt(profilePicture.getBytes(StandardCharsets.UTF_8).length);
        bf.put(profilePicture.getBytes(StandardCharsets.UTF_8));
        bf.putInt((isReady ? 1: 0));
        bf.putInt(cardsInHand);
        bf.putInt(cardsInDeck);
        return bf.array();
    }

    public byte[] encodeBytesIncludeHand(){
        int handLength = 0;
        for (Card card:hand){
            handLength += 4 + card.encode().length;
        }
        ByteBuffer bf = ByteBuffer.allocate(36 +hp.encodedBytes().length + name.getBytes(StandardCharsets.UTF_8).length+profilePicture.getBytes(StandardCharsets.UTF_8).length + handLength);
        bf.putInt(playerNumber);
        bf.putInt(hp.encodedBytes().length);
        bf.put(hp.encodedBytes());
        bf.putInt(mana);
        bf.putInt(maxMana);
        bf.putInt(name.getBytes(StandardCharsets.UTF_8).length);
        bf.put(name.getBytes(StandardCharsets.UTF_8));
        bf.putInt(profilePicture.getBytes(StandardCharsets.UTF_8).length);
        bf.put(profilePicture.getBytes(StandardCharsets.UTF_8));
        bf.putInt((isReady ? 1: 0));
        bf.putInt(cardsInHand);
        bf.putInt(cardsInDeck);
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
            System.out.println(Arrays.toString(bytes));
            int byteRead = 0;
            int playerID = in.readInt();
            int playerHPByte = in.readInt();
            Number playerHP = Number.decodedBytes(in.readNBytes(playerHPByte));
            int mana = in.readInt();
            int maxMana = in.readInt();
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
            int cardInDeck = in.readInt();
            byteRead = nameByte + profilePictureByte + playerHPByte + 36;
            System.out.println( "hp: " + playerHPByte + " name: " + nameByte + " profile: " + profilePictureByte + " total: " + byteRead);
            if (byteRead == bytes.length){
                return new PlayerInfo(playerName,profilePicture,playerHP,mana,maxMana,playerID,isReady,cardInDeck,cardInHand);
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
            return new PlayerInfo(playerName,profilePicture,playerHP,mana,maxMana,playerID,isReady,cardInDeck,cardInHand,cards);
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return new PlayerInfo("","assets/icon.png",new Constant(100),1,1,0,false);
    }

    @Override
    public Card draw() {
        Card c = super.draw();
        cardsInDeck -= 1;
        return c;
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

    public int getCardsInDeck() {
        return cardsInDeck;
    }

    public void setCardsInDeck(int cardsInDeck) {
        this.cardsInDeck = cardsInDeck;
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
                ", cardsInDeck=" + cardsInDeck +
                ", deck" + deck.getCards() +
                ", hand=" + hand +
                '}';
    }
}
