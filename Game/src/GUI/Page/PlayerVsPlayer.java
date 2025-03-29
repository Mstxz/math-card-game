
package GUI.Page;

import AudioPlayer.BGMPlayer;
import GUI.Component.ButtonPanelComponent;
import GUI.Component.ExitButton;
import GUI.Component.PlayerPanelComponent; //จัดแสดงรูป
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import GUI.Router;
import GameSocket.LobbyObserver;
import GameSocket.NIOClient;
import GameSocket.NIOServer;
import utils.SharedResource;
import Gameplay.Player;
import utils.UIManager.RoundPanelUI;


public class PlayerVsPlayer extends Page implements ActionListener, LobbyObserver {
    private ArrayList<Player> list;
    private JLabel title,head;
    private NIOClient client;
    private ButtonPanelComponent btnPanel;
    private PlayerPanelComponent playerPanel;
    //private Image bg;

    public PlayerVsPlayer(NIOClient client) {
        BGMPlayer.playBackgroundMusic("Game/src/assets/Audio/BGM/PVP_Lobby_BGM.wav", true);
        this.client = client;
        list = new ArrayList<Player>();
        mainPanel.setLayout(new BorderLayout(20, 0));
        mainPanel.setBackground(SharedResource.SIAMESE_BRIGHT);
        mainPanel.setBorder(new EmptyBorder(20, 40, 80, 40));
        //mai/nPanel.setBorder(BorderFactory.createLineBorder(Color.yellow));
        // Title Component
        title = new JLabel("Player Vs Player");
        title.setFont(SharedResource.getCustomSizeFont(48));
        title.setForeground(SharedResource.SIAMESE_DARK);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setPreferredSize(new Dimension(0, 100));
        mainPanel.add(title, BorderLayout.NORTH);

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);
        head = new JLabel("Matchmaking...");
        head.setFont(SharedResource.getCustomSizeFont(36));
        head.setHorizontalAlignment(SwingConstants.CENTER);
        ExitButton exitButton = new ExitButton("SelMode"){
            @Override
            public void cleanUp() {
                client.closeClient();
                NIOServer.getInstance().stopServer();

            }
        };

        JLabel emptySpace = new JLabel();
        emptySpace.setPreferredSize(exitButton.getPreferredSize());
        headerPanel.add(exitButton, BorderLayout.WEST);
        headerPanel.add(head, BorderLayout.CENTER);
        headerPanel.add(emptySpace,BorderLayout.EAST);
        headerPanel.setBorder(new EmptyBorder(10,10,20,20));

        // Add ButtonPanelComponent
        btnPanel = new ButtonPanelComponent();

        btnPanel.setBackground(SharedResource.SIAMESE_LIGHT);
        JPanel panel = new JPanel(new BorderLayout(20, 0));
        panel.setUI(new RoundPanelUI(SharedResource.SIAMESE_LIGHT,30,30));
        panel.add(headerPanel,BorderLayout.NORTH);

        panel.add(btnPanel, BorderLayout.SOUTH);

        // Create a JPanel to hold the PlayerPanelComponent and center it
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
        centerPanel.setOpaque(false);
        //centerPanel.setBackground(SharedResource.SIAMESE_BASE);
        playerPanel = new PlayerPanelComponent(client.getTurnOrder(),client.getPlayerOrder());
        centerPanel.add(playerPanel);

        centerPanel.add(Box.createVerticalGlue());

        btnPanel.getDeckButton().addActionListener(this);
        btnPanel.getReadyButton().addActionListener(this);

        panel.add(centerPanel, BorderLayout.CENTER);
        //panel.setBorder(new LineBorder(SharedResource.SIAMESE_BASE,5));
        mainPanel.add(panel, BorderLayout.CENTER);
        client.addLobbyObserver(this);
        client.addLobbyObserver(playerPanel);
        client.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnPanel.getReadyButton())){
            client.pressedReady();
        } else if (e.getSource().equals(btnPanel.getDeckButton())) {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.setCurrentDirectory(new File("Assets"));
            jFileChooser.setFileFilter(new FileNameExtensionFilter("Deck File","deck"));
            int l = jFileChooser.showOpenDialog(mainFrame);
            if (l == JFileChooser.APPROVE_OPTION){
                String deckPath = jFileChooser.getSelectedFile().getAbsolutePath();
                client.setDeckPath(deckPath);
                btnPanel.getReadyButton().setEnabled(true);
            }

        }
    }

    @Override
    public void onLobbyChange(ArrayList<Player> playerInfos) {
        if (client.isGameStarted()){
            Router.setRoute("Avenger",client);
        }
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