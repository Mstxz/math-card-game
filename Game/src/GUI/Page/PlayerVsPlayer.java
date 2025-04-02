
package GUI.Page;

import AudioPlayer.BGMPlayer;
import GUI.Component.ButtonPanelComponent;
import GUI.Component.CountObserver;
import GUI.Component.ExitButton;
import GUI.Component.PlayerPanelComponent; //จัดแสดงรูป
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
import GameSocket.PlayerInfo;
import utils.SharedResource;
import Gameplay.Player;
import utils.UIManager.RoundPanelUI;


public class PlayerVsPlayer extends Page implements ActionListener, KeyListener, LobbyObserver, CountObserver {
    private ArrayList<Player> list;
    private JLabel title,head;
    private NIOClient client;
    private ButtonPanelComponent btnPanel;
    private PlayerPanelComponent playerPanel;
    private ExitButton exitButton;
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
        exitButton = new ExitButton("SelMode"){
            @Override
            public void cleanUp() {
                client.notifyQuit();
                NIOServer.getInstance().stopServer();
                mainFrame.removeKeyListener(PlayerVsPlayer.this);
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

        mainFrame.addKeyListener(this);

        client.addLobbyObserver(this);
        client.setCountObserver(this);
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

        } else if (e.getSource().equals(exitButton)) {
//            this.mainFrame.removeKeyListener(this);
        }
    }

    @Override
    public void onLobbyChange(ArrayList<Player> playerInfos) {
        int countAllPlayer = 0;
        int countReady = 0;
        for (Player p:playerInfos){
            PlayerInfo playerInfo = (PlayerInfo) p;
            if (playerInfo != null){
                if (playerInfo.getPlayerID() == client.getPlayerOrder()){
                    if (playerInfo.isReady()){
                        btnPanel.getReadyButton().setText("Unready");
                    }
                    else{
                        btnPanel.getReadyButton().setText("Ready");
                    }
                }
                if (playerInfo.isReady()){
                    countReady += 1;
                }
                countAllPlayer += 1;
            }
        }

        if (countReady != countAllPlayer){
            head.setText("Matchmaking...");
        }

        if (client.isGameStarted()){
            mainFrame.removeKeyListener(PlayerVsPlayer.this);
            Router.setRoute("Avenger",client);
        }
    }


    @Override
    public void onCountChange(int count) {
        head.setText("Starting in " + count);
    }

    @Override
    public void onLobbyClose() {
        client.notifyQuit();
        //NIOServer.getInstance().stopServer();
        Router.setRoute("SelMode",null);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code;

//        if (!this.getMainPanel().isFocusable())
//            return;
        code = e.getKeyCode();
        if (code == KeyEvent.VK_ESCAPE){
            this.getMainFrame().removeKeyListener(this);
            Router.setRoute("SelMode",null);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
