package GUI.Page;

import GUI.Component.ButtonPanelComponent;
import GUI.Component.PlayerPanelComponent;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import utils.SharedResource;

public class PlayerVsPlayer extends Page {
    private JLabel title;

    public PlayerVsPlayer() {
        mainPanel.setLayout(new BorderLayout(20, 0));
        mainPanel.setBorder(new EmptyBorder(10, 80, 10, 80));
        // Title Component
        title = new JLabel("Player Vs Player");
        title.setFont(new Font("Arial", Font.BOLD, 48));
        title.setForeground(Color.DARK_GRAY);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setPreferredSize(new Dimension(0, 100));
        mainPanel.add(title, BorderLayout.NORTH);

        // Add ButtonPanelComponent
        ButtonPanelComponent buttonPanelComponent = new ButtonPanelComponent();
        JPanel panel = new JPanel(new BorderLayout(20, 0));
        panel.setBackground(SharedResource.SIAMESE_LIGHT);
        panel.add(buttonPanelComponent, BorderLayout.CENTER);
        mainPanel.add(panel, BorderLayout.SOUTH);

        // Add PlayerPanelComponent
        PlayerPanelComponent playerPanelComponent = new PlayerPanelComponent();
        mainPanel.add(playerPanelComponent, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        new PlayerVsPlayer();
    }
}
