package GUI.Component;

import java.awt.*;
import java.util.Objects;
import javax.swing.*;



public class PlayerPanelComponent extends JPanel {
    public PlayerPanelComponent() {
        setLayout(new GridLayout(1, 4, 20, 20));
        setOpaque(false);
        //setBackground(new Color(210, 200, 180));

        for (int i = 1; i <= 4; i++) {
            ImageIcon playerImage = new ImageIcon(getClass().getResource("/assets/testLobby/Cat3.jpg"));

            if (playerImage.getImageLoadStatus() != MediaTracker.COMPLETE) {
                System.out.println("Error loading image");
            }

            Image scaledImage = playerImage.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);

            playerImage = new ImageIcon(scaledImage);

            PlayerChoose playerCard = new PlayerChoose(playerImage, "Player " + i, true);
            add(playerCard);
        }
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