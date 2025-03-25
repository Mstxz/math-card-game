package GUI.Component;

import Gameplay.Player;
import utils.ResourceLoader;
import utils.SharedResource;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class PlayerProfile extends JPanel {
    private String username;
    private String profilePicture;
    private JLabel nameLabel;
    private JLabel pictureHolder;
    private Player owner;

    public PlayerProfile(Player owner) {
        super();

        this.owner = owner;
        this.username = owner.getName();
        this.profilePicture = owner.getProfilePicture();

        nameLabel = new JLabel(username);
        nameLabel.setFont(SharedResource.getCustomSizeFont(28));
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);

        pictureHolder = new JLabel();
        pictureHolder.setBackground(SharedResource.SIAMESE_DARK);
        pictureHolder.setBorder(new LineBorder(SharedResource.SIAMESE_DARK,10));
        pictureHolder.setHorizontalAlignment(SwingConstants.CENTER);
        pictureHolder.setSize(170, 170);

        ImageIcon ic = ResourceLoader.loadPicture(profilePicture,171,171);
        pictureHolder.setIcon(ic);
        this.setLayout(new BorderLayout(0,15));
        this.add(nameLabel,BorderLayout.SOUTH);
        this.add(pictureHolder,BorderLayout.CENTER);
        this.setBackground(SharedResource.SIAMESE_BRIGHT);

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }
}