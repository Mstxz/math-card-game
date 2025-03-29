package GUI.Component;

import Gameplay.Player;
import org.w3c.dom.ls.LSOutput;
import utils.ResourceLoader;
import utils.SharedResource;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class PlayerLobbyCard extends JPanel {
    private final JLabel readyLabel;
    private boolean isOwner;
    private boolean isReady;


    public PlayerLobbyCard(Player player, boolean isOwner,boolean isReady)
    {
        this.isOwner = isOwner;
        this.isReady = isReady;

        JLabel isUserLabel = new JLabel((isOwner) ? "You" : " ");
        isUserLabel.setFont(SharedResource.getCustomSizeFont(32));
        isUserLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel playerPic = new JLabel(ResourceLoader.loadPicture(player.getProfilePicture(), 200, 200));

        JLabel nameLabel = new JLabel(player.getName());
        nameLabel.setFont(SharedResource.getCustomSizeFont(36));
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel profilePanel = new JPanel(new BorderLayout());
        profilePanel.add(playerPic, BorderLayout.CENTER);
        profilePanel.add(nameLabel,BorderLayout.SOUTH);
        profilePanel.setBackground(SharedResource.SIAMESE_LIGHT);

        readyLabel = new JLabel();
        readyLabel.setFont(SharedResource.getCustomSizeFont(28));
        readyLabel.setHorizontalAlignment(SwingConstants.CENTER);

        this.setLayout(new BorderLayout(10, 0));
        this.setOpaque(false);

        this.add(isUserLabel,BorderLayout.NORTH);
        this.add(profilePanel,BorderLayout.CENTER);
        this.add(readyLabel, BorderLayout.SOUTH);

        this.update_status();
        this.setPreferredSize(new Dimension(300, 360));
        this.setSize(300, 360);// TODO: set size
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
