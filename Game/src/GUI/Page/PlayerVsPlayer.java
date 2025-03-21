
package GUI.Page;

import GUI.Component.ButtonPanelComponent;
import GUI.Component.ExitButton;
import GUI.Component.PlayerPanelComponent; //จัดแสดงรูป
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import GameSocket.NIOClient;
import GameSocket.NIOServer;
import utils.SharedResource;
import Gameplay.Player;



public class PlayerVsPlayer extends Page {
    private ArrayList<Player> list;
    private JLabel title,head;
    private NIOClient client;
    private JButton exitButton;
    private JButton decksButton;
    private JButton readyButton;
    private JButton settingsButton;

    //private Image bg;

    public PlayerVsPlayer(NIOClient client) {
        list = new ArrayList<Player>();
        mainPanel.setLayout(new BorderLayout(20, 0));
        mainPanel.setBackground(new Color(221,218,210));
//        mainPanel.setBorder(BorderFactory.createCompoundBorder(
//                BorderFactory.createLineBorder(Color.yellow, 3), // Outer line border
//                new EmptyBorder(20, 40, 80, 40) // Inner padding
//        ));
        mainPanel.setBorder(new EmptyBorder(20, 40, 40, 40));
        //mai/nPanel.setBorder(BorderFactory.createLineBorder(Color.yellow));
        // Title Component
        title = new JLabel("Player Vs Player");
        title.setFont(SharedResource.getCustomSizeFont(80));
        title.setForeground(new Color(72, 62, 56));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setPreferredSize(new Dimension(0, 100));
        mainPanel.add(title, BorderLayout.NORTH);

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);
        head = new JLabel("Matchmaking...");
        head.setFont(SharedResource.getCustomSizeFont(40));
        head.setHorizontalAlignment(SwingConstants.CENTER);
        ExitButton exitButton = new ExitButton("SelMode");
        headerPanel.add(exitButton, BorderLayout.WEST);
        headerPanel.add(head, BorderLayout.CENTER);

        // Add ButtonPanelComponent
        ButtonPanelComponent buttonPanelComponent = new ButtonPanelComponent();
        JPanel panel = new JPanel(new BorderLayout(20, 0));
        panel.add(headerPanel,BorderLayout.NORTH);
        panel.setBackground(SharedResource.SIAMESE_LIGHT);
        buttonPanelComponent.setOpaque(false);

        panel.add(buttonPanelComponent, BorderLayout.SOUTH);

        // Create a JPanel to hold the PlayerPanelComponent and center it
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
        centerPanel.setOpaque(false);
        //centerPanel.setBackground(SharedResource.SIAMESE_BASE);
        PlayerPanelComponent playerPanelComponent = new PlayerPanelComponent(client.getPlayerInfos(),client.getPlayerID());
        centerPanel.add(playerPanelComponent);
        client.addLobbyObserver(playerPanelComponent);
        centerPanel.add(Box.createVerticalGlue());

//        centerPanel.add(Box.createVerticalGlue());
        panel.add(centerPanel, BorderLayout.CENTER);

        mainPanel.add(panel, BorderLayout.CENTER);

    }


    public static void main(String[] args) {
        //new PlayerVsPlayer();
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