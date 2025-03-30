package GUI.Component;

import Gameplay.Bot.Bot;
import Gameplay.Player;
import utils.ResourceLoader;

import javax.swing.*;

public class BotInfo {
    private Bot botPlayer;
    private String name;
    private String description;
    private ImageIcon img;

    public BotInfo(Bot botPlayer) {
        this.botPlayer = botPlayer;
        this.name = botPlayer.getName();
        this.description = botPlayer.getDescription();
        this.img = ResourceLoader.loadPicture(botPlayer.getProfilePicture());

    }

    public Bot getBotPlayer() {
        return botPlayer;
    }

    public void setBotPlayer(Bot botPlayer) {
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
