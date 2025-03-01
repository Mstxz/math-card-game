package GameSocket;

import Gameplay.Card;
import Gameplay.Number;
import Gameplay.NumberType;
import Gameplay.Numbers.Constant;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class PlayerInfo {
    private String name;
    private String profilePicture;
    private int mana;
    private ArrayList<Card> hand;
    private Number hp;
    private NumberType numberType;
    private int maxMana;
    private int playerNumber;

    public PlayerInfo() {
        this("","assets/icon.png",0,0);
    }

    public PlayerInfo(String name, String profilePicture, int hp, int playerNumber) {
        this.name = name;
        this.profilePicture = profilePicture;
        this.mana = 1;
        this.hand = new ArrayList<Card>();
        this.hp = new Constant(hp);
        this.numberType = NumberType.CONSTANT;
        this.maxMana = 1;
        this.playerNumber = playerNumber;
    }

    public byte[] encodeBytes(){
        ByteBuffer bf = ByteBuffer.allocate(1024);
        bf.putInt(playerNumber);
        bf.putInt(100);
        bf.putInt(name.getBytes(StandardCharsets.UTF_8).length);
        bf.put(name.getBytes(StandardCharsets.UTF_8));
        bf.putInt(profilePicture.getBytes(StandardCharsets.UTF_8).length);
        bf.put(profilePicture.getBytes());
        return bf.array();
    }

    public static PlayerInfo decodeBytes(byte[] bytes){
        DataInputStream in = new DataInputStream(new ByteArrayInputStream(bytes));
        try {
            int playerID = in.readInt();
            int playerHP = in.readInt();
            int nameByte = in.readInt();
            String playerName = new String(in.readNBytes(nameByte),StandardCharsets.UTF_8);
            int profilePictureByte = in.readInt();
            String profilePicture = new String(in.readNBytes(profilePictureByte),StandardCharsets.UTF_8);
            return new PlayerInfo(playerName,profilePicture,playerHP,playerID);
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return new PlayerInfo("","assets/icon.png",0,0);
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

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
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

    @Override
    public String toString() {
        return "PlayerInfo{" +
                "name='" + name + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                ", mana=" + mana +
                ", hand=" + hand +
                ", hp=" + hp +
                ", numberType=" + numberType +
                ", maxMana=" + maxMana +
                ", playerNumber=" + playerNumber +
                '}';
    }
}
