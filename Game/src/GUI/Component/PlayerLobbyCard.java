package GUI.Component;

import Gameplay.Player;
import utils.ResourceLoader;
import utils.SharedResource;

import javax.swing.*;
import java.awt.*;

public class PlayerLobbyCard extends JPanel {
    private Player player;
    private JLabel playerPic;
    private JLabel nameLabel;
    private JLabel readyLabel;
    private boolean isOwner;
    private boolean isReady;

    public PlayerLobbyCard(Player player, boolean isOwner,boolean isReady)
    {
        this.player = player;
        this.isOwner = isOwner;
        this.isReady = isReady;
        this.setLayout(new BorderLayout(10, 10));
        this.setAlignmentX(CENTER_ALIGNMENT);
        this.setOpaque(false);

        playerPic = new JLabel(ResourceLoader.loadPicture(player.getProfilePicture(), 200, 200));
        this.add(playerPic, BorderLayout.NORTH);

        nameLabel = new JLabel(player.getName());
        nameLabel.setFont(SharedResource.getCustomSizeFont(48));
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(nameLabel, BorderLayout.CENTER);

        readyLabel = new JLabel();
        readyLabel.setFont(SharedResource.getCustomSizeFont(36));
        readyLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.update_status();
        this.add(Box.createVerticalBox(), BorderLayout.SOUTH);
        this.add(readyLabel, BorderLayout.SOUTH);

        this.setPreferredSize(new Dimension(300, 320));
        this.setSize(300, 320);// TODO: set size
        this.setVisible(true);
    }

    private void update_status()
    {
        if (this.isReady) {
            readyLabel.setText("READY");
        } else {
            readyLabel.setText("NOT READY");
        }
        revalidate();
        repaint();
    }

    public void setReady(boolean ready) {
        isReady = ready;
        update_status();
    }

    public boolean getReady() {
        return  isReady;
    }

    public void setOwner(boolean owner) {
        isOwner = owner;
        update_status();
    }

    public boolean getOwner() {
        return isOwner;
    }
}
