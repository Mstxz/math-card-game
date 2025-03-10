package GUI.Component;

import java.awt.*;
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
