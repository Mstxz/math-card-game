
package GUI.Page;

import GUI.Component.*;
import GUI.Router;
import GameSocket.NIOClient;
import GameSocket.NIOServer;
import utils.SharedResource;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class RoomSelect extends Page implements ActionListener {
    private JPanel optionPanel;
    private JPanel middlePanel;
    private JLabel header;
    private JLabel createRoomLabel;
    private JLabel joinRoomLabel;
    private PlayerVSPlayerSelectButton  createButton;
    private PlayerVSPlayerSelectButton  joinButton;
    private JTextField hostIpField;
    private ExitButton exitLabel;
    private boolean loading;

    public RoomSelect() {
        middlePanel = new JPanel();
        optionPanel = new JPanel();
        header = new JLabel("Select");
        header.setFont(SharedResource.getCustomSizeFont(48));
        header.setHorizontalAlignment(SwingConstants.CENTER);

        createRoomLabel = new JLabel("Create Room");
        createRoomLabel.setBackground(SharedResource.SIAMESE_BRIGHT);
        createRoomLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        joinRoomLabel = new JLabel("Join Room");
        joinRoomLabel.setBackground(SharedResource.SIAMESE_BRIGHT);
        joinRoomLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        createButton = new PlayerVSPlayerSelectButton("Create");
        createButton.setPreferredSize(new Dimension(350, 80));

        JLabel createButtonLabel = new JLabel("                     Create                     ", SwingConstants.CENTER);
        createButtonLabel.setHorizontalAlignment(SwingConstants.CENTER);
        createButtonLabel.setFont(SharedResource.getCustomSizeFont(28));
        createButtonLabel.setForeground(new Color(102, 142, 169));
        createButtonLabel.setBounds(0, 0, 356, 99);
        createButton.add(createButtonLabel);
//        createButton.setBorder(new EmptyBorder(10,60,10,60));
//        createButton.setPreferredSize(new Dimension(200,60));
        createButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        joinButton = new PlayerVSPlayerSelectButton ("Join");
        joinButton.setPreferredSize(new Dimension(350, 80));

        JLabel joinButtonLabel = new JLabel("                        Join                        ", SwingConstants.CENTER);
        joinButtonLabel.setHorizontalAlignment(SwingConstants.CENTER);
        joinButtonLabel.setFont(SharedResource.getCustomSizeFont(28));
        joinButtonLabel.setForeground(new Color(102, 142, 169));
        joinButtonLabel.setBounds(0, 0, 356, 99);
        joinButton.add(joinButtonLabel);
        joinButton.setAlignmentX(Component.CENTER_ALIGNMENT);

//        joinButton.setBorder(new EmptyBorder(10,60,10,60));
//        joinButton.setPreferredSize(new Dimension(200,60));
        joinButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        hostIpField = new JTextField();
        hostIpField.setColumns(50);
        hostIpField.setMaximumSize(new Dimension(200,50));
        hostIpField.setAlignmentX(Component.CENTER_ALIGNMENT);

        exitLabel = new ExitButton("SelMode");
        exitLabel.setVerticalAlignment(SwingConstants.NORTH);
        exitLabel.setHorizontalAlignment(SwingConstants.LEFT);

        optionPanel.setLayout(new BoxLayout(optionPanel,BoxLayout.Y_AXIS));
        optionPanel.setPreferredSize(new Dimension(300,600));
        optionPanel.add(createRoomLabel);
        optionPanel.add(Box.createRigidArea(new Dimension(0,10)));
        optionPanel.add(createButton);
        optionPanel.add(Box.createRigidArea(new Dimension(0,20)));
        optionPanel.add(joinRoomLabel);
        optionPanel.add(Box.createRigidArea(new Dimension(0,10)));
        optionPanel.add(hostIpField);
        optionPanel.add(Box.createRigidArea(new Dimension(0,10)));
        optionPanel.add(joinButton);
        optionPanel.setBackground(SharedResource.SIAMESE_BRIGHT);

        middlePanel.setLayout(new BorderLayout(0,100));
        middlePanel.add(header,BorderLayout.NORTH);
        middlePanel.add(optionPanel);
        middlePanel.setBackground(SharedResource.SIAMESE_BRIGHT);

        mainPanel.setLayout(new GridLayout(1,3));
        mainPanel.setBackground(SharedResource.SIAMESE_BRIGHT);
        mainPanel.add(exitLabel);
        mainPanel.add(middlePanel);
        mainPanel.add(new JLabel());

        createButton.addActionListener(this);
        joinButton.addActionListener(this);

        setupMainPanel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(createButton)){
            NIOServer.getInstance().stopServer();
            NIOServer.getInstance().start();
            NIOClient client = new NIOClient();
            Loader l = new Loader(this,"Creating Session"){
                @Override
                public void running(){
                    try{
                        if (NIOServer.getInstance().isBound()){
                            client.connect("localhost");
                            while (!client.isLobbyLoaded()){
                                Thread.sleep(1000);
                            }
                        }
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
                @Override
                public boolean closeCondition(){
                    return client.isLobbyLoaded();
                }
                @Override
                public void onClose(){
                    super.onClose();
                    Router.setRoute("Lobby",client);
                }
            };
            l.startLoad();
        }
        else if(e.getSource().equals(joinButton)){
            NIOClient client = new NIOClient();
            client.connect(hostIpField.getText());
            Loader l = new Loader(this,"Joining Session"){
                @Override
                public void running(){
                    try{
                        Thread.sleep(1000);

                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
                @Override
                public boolean closeCondition(){
                    return client.isLobbyLoaded();
                }
                @Override
                public void onClose(){
                    super.onClose();
                    Router.setRoute("Lobby",client);
                }
            };
            l.startLoad();
        }
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