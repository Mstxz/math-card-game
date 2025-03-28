package GUI.Component;

import Gameplay.Player;
import utils.ResourceLoader;

import javax.swing.*;

public class BotInfo {
    private Player botPlayer;
    private String name;
    private String description;
    private ImageIcon img;

    public BotInfo(Player botPlayer, String name, String description, String img) {
        this.botPlayer = botPlayer;
        this.name = name;
        this.description = description;
        this.img = ResourceLoader.loadPicture(img,100,100);
    }

    public Player getBotPlayer() {
        return botPlayer;
    }

    public void setBotPlayer(Player botPlayer) {
        this.botPlayer = botPlayer;
    }

    public String getName() {
        return name;
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

    public ImageIcon getImg() {
        return img;
    }

    public void setImg(ImageIcon img) {
        this.img = img;
    }
}
