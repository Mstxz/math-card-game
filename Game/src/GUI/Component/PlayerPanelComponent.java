package GUI.Component;

import GameSocket.LobbyObserver;
import GameSocket.PlayerInfo;
import Gameplay.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;
import javax.swing.*;



public class PlayerPanelComponent extends JPanel implements LobbyObserver {
    private ArrayList<PlayerLobbyCard> slot;

    public PlayerPanelComponent(ArrayList<PlayerInfo> playerInfos,int ownerID) {
//        setLayout(new GridLayout(1, 4, 20, 20));
        setOpaque(false);
        this.slot = new ArrayList<PlayerLobbyCard>();
//        setBackground(new Color(210, 200, 180));

        //slot.add(new PlayerLobbyCard(new Player("Soda Mun Za", "assets/ProfileCat1.jpg"), true));
        for (int i =0;i<playerInfos.size();i++) {
            PlayerInfo playerInfo = playerInfos.get(i);
            if (playerInfo != null) {
                slot.add(new PlayerLobbyCard(new Player(playerInfo.getName(), playerInfo.getProfilePicture()), i == ownerID));
            }
        }
        //this.add(slot.getFirst()); // TODO: Set the constructor user to be owner.
//        this.addUser(new Player("Bot1", "assets/ProfileCat1.jpg")); // TODO: Remove comment for testing scaling.
//        this.addUser(new Player("Bot2", "assets/ProfileCat1.jpg")); // TODO: Remove comment for testing scaling.
//        this.addUser(new Player("Bot3", "assets/ProfileCat1.jpg")); // TODO: Remove comment for testing scaling.
        this.FlowScale();
    }

    public void addUser(Player player) {
        if (slot.size() >= 4 || player == null)
            return ;
        this.slot.add(new PlayerLobbyCard(player, false));
        this.add(slot.getLast());
        FlowScale();
    }

    public void removeUser() {
        // TODO: To remove user card when quiting.
    }

    public void updateLobby(){
        this.removeAll();
        for (PlayerLobbyCard lobbyCard:slot){
            this.add(lobbyCard);
        }
    }

    private void FlowScale(){
        this.setLayout(new FlowLayout(FlowLayout.CENTER, (int)(500 / slot.size()), 0));
    }

    @Override
    public void onChange() {
        updateLobby();
    }
}


/*
public class PlayerPanelComponent extends JPanel {
    public PlayerPanelComponent(String playerName, String status, String imagePath) {
        setLayout(new BorderLayout());
        setOpaque(false);

        // Load and scale the player's image
        ImageIcon playerImage = new ImageIcon(Objects.requireNonNull(getClass().getResource(imagePath)));
        if (playerImage.getImageLoadStatus() != MediaTracker.COMPLETE) {
            System.out.println("Error loading image: " + imagePath);
        }

        Image scaledImage = playerImage.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(scaledImage), SwingConstants.CENTER);

        // Player Name
        JLabel nameLabel = new JLabel(playerName, SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        nameLabel.setForeground(new Color(60, 50, 40));

        // Player Status (Ready or Not Ready)
        JLabel statusLabel = new JLabel(status, SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 16));

        // Color-coding status
        if (status.equalsIgnoreCase("Ready")) {
            statusLabel.setForeground(Color.GREEN);
        } else {
            statusLabel.setForeground(Color.ORANGE);
        }

        // Panel for name & status
        JPanel textPanel = new JPanel(new GridLayout(2, 1));
        textPanel.setOpaque(false);
        textPanel.add(nameLabel);
        textPanel.add(statusLabel);

        // Create rounded panel with the player's details
        RoundedPanel playerPanel = new RoundedPanel(20, new Color(220, 210, 190));
        playerPanel.setLayout(new BorderLayout());
        playerPanel.add(imageLabel, BorderLayout.CENTER);
        playerPanel.add(textPanel, BorderLayout.SOUTH);

        add(playerPanel, BorderLayout.CENTER);
    }
}

 */