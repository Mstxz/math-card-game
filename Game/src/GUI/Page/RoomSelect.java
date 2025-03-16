
package GUI.Page;

import GUI.Component.ButtonPanelComponent;
import GUI.Component.ExitButton;
import GUI.Component.PlayerPanelComponent;
import utils.SharedResource;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;


public class RoomSelect extends Page {
    private JPanel middlePanel;
    private JLabel title;
    private JLabel createRoomLabel;
    private JLabel joinRoomLabel;
    private JButton createButton;
    private JButton joinButton;
    private JTextField hostIpField;
    //private Image bg;

    public RoomSelect() {
        mainPanel.setLayout(new BorderLayout(20, 0));
        mainPanel.setBackground(new Color(221,218,210));
//        mainPanel.setBorder(BorderFactory.createCompoundBorder(
//                BorderFactory.createLineBorder(Color.yellow, 3), // Outer line border
//                new EmptyBorder(20, 40, 80, 40) // Inner padding
//        ));
        mainPanel.setBorder(new EmptyBorder(20, 40, 40, 40));
        setupMainPanel();
    }


    public static void main(String[] args) {
        new RoomSelect();
    }
}



/*

public class PlayerVsPlayer extends Page {
    private JLabel title, head, matchCode;
    private JButton exitButton, settingsButton, decksButton, readyButton;

    public PlayerVsPlayer() {
        mainPanel.setLayout(new BorderLayout(20, 0));
        mainPanel.setBackground(new Color(240, 235, 225)); // Light beige
        mainPanel.setBorder(new EmptyBorder(20, 40, 40, 40));

        // Title
        title = new JLabel("Player vs Player");
        title.setFont(SharedResource.getCustomSizeFont(60)); // Adjusted font
        title.setForeground(new Color(50, 40, 35)); // Dark brown text
        title.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(title, BorderLayout.NORTH);

        // Rounded Border Panel (Main Panel)
        RoundedPanel centerPanel = new RoundedPanel(30, new Color(205, 190, 170)); // Light brown rounded panel
        centerPanel.setLayout(new BorderLayout(20, 10));

        // Top Panel (Exit & Settings)
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);

        exitButton = new JButton("< Exit");
        exitButton.setFont(SharedResource.getCustomSizeFont(20));
        exitButton.setBorderPainted(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setFocusPainted(false);
        topPanel.add(exitButton, BorderLayout.WEST);

        settingsButton = new JButton(new ImageIcon("settings_icon.png")); // Replace with actual image path
        settingsButton.setBorderPainted(false);
        settingsButton.setContentAreaFilled(false);
        settingsButton.setFocusPainted(false);
        topPanel.add(settingsButton, BorderLayout.EAST);

        centerPanel.add(topPanel, BorderLayout.NORTH);

        // Matchmaking Header & Code
        JPanel matchmakingPanel = new JPanel(new GridLayout(2, 1, 0, 5));
        matchmakingPanel.setOpaque(false);

        head = new JLabel("Matchmaking...", SwingConstants.CENTER);
        head.setFont(SharedResource.getCustomSizeFont(30));
        head.setForeground(new Color(80, 60, 50));

        matchCode = new JLabel("Code: ABCDEF", SwingConstants.CENTER);
        matchCode.setFont(SharedResource.getCustomSizeFont(20));
        matchCode.setForeground(new Color(90, 80, 70));

        matchmakingPanel.add(head);
        matchmakingPanel.add(matchCode);

        centerPanel.add(matchmakingPanel, BorderLayout.CENTER);

        // Player Panel (4 players with images and names)
        JPanel playerContainer = new JPanel(new GridLayout(1, 4, 15, 0));
        playerContainer.setOpaque(false);

        playerContainer.add(new PlayerPanelComponent("Soda Mun Za", "ready", "assets/ProfileCat1.jpg"));
        playerContainer.add(new PlayerPanelComponent("Bob Hair Karn", "ready", "assets/ProfileCat1.jpg"));
        playerContainer.add(new PlayerPanelComponent("Klong Ang Ha", "notReady", "assets/ProfileCat1.jpg"));
        playerContainer.add(new PlayerPanelComponent("Om Tuk", "ready", "assets/ProfileCat1.jpg"));

        centerPanel.add(playerContainer, BorderLayout.SOUTH);

        // Buttons (Decks & Ready with rounded effect)
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        buttonPanel.setOpaque(false);

        decksButton = new RoundedButton("Decks");
        readyButton = new RoundedButton("Ready");

        buttonPanel.add(decksButton);
        buttonPanel.add(readyButton);

        centerPanel.add(buttonPanel, BorderLayout.PAGE_END);

        // Add to Main Panel
        mainPanel.add(centerPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        new PlayerVsPlayer();
    }
}
*/