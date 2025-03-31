package GUI.Page;

import AudioPlayer.BGMPlayer;
import AudioPlayer.SFXPlayer;
import GUI.Component.MainMenuAnimation;
import GUI.Component.MainMenuButton;
import GUI.Router;
import GUI.Setting.Controller.SettingController;
import GUI.Setting.UserPreference;
import Gameplay.Bot.Mystyr;
import Gameplay.Bot.Who;
import Gameplay.Deck;
import Gameplay.GameForGUI;
import Gameplay.Player;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import utils.ResourceLoader;
import utils.SharedResource;

public class SelGameMode extends Page implements ActionListener, KeyListener {
    private JPanel ButtonZone;
    private JPanel TitlePanel;
    private JLabel Title;
    private MainMenuButton playerButton = new MainMenuButton("Player vs Player");
    private MainMenuButton botButton = new MainMenuButton("Player vs Bot");
    private MainMenuButton backButton = new MainMenuButton("Back");

    private Image bg;

    private String track = "Game/src/assets/Audio/BGM/Lobby_BGM.wav";

    public SelGameMode() {
        super();
        // Ensure the background image exists
        try {
            bg = new ImageIcon(getClass().getClassLoader().getResource("assets/blankBG.jpg")).getImage();
        } catch (Exception e) {
            System.out.println("Error loading background image: " + e.getMessage());
            // You can use a default image or just a solid color as a fallback
            //bg = new Color(0, 0, 0); // Solid black as fallback
        }
        initComponents();
        try {
            ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("assets/icon.png"));
            //mainFrame.setIconImage(icon.getImage());
        } catch (Exception e) {
            System.out.println("Error loading icon: " + e.getMessage());
        }

        if((BGMPlayer.getBgmClip() == null || !BGMPlayer.getFilepath().equals(track)) || !BGMPlayer.checkIfPlaying()){
            if (BGMPlayer.getBgmClip() == null) {
                BGMPlayer.playBackgroundMusic(track, true);
            }
            else {
                BGMPlayer.stopBackgroundMusic();
                BGMPlayer.playBackgroundMusic(track, true);
            }
        }
    }

    private void initComponents() {
        // MainPanel: Use GridBagLayout instead of GridLayout for flexible positioning
        mainPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g); // Ensures the panel is painted first
                if (bg != null) {
                    g.drawImage(bg, 0, 0, getWidth(), getHeight(), this); // Draw the background image
                }
            }
        };

        TitlePanel = new JPanel(new BorderLayout());
        TitlePanel.setBackground(new Color(255, 255, 255, 0));
        TitlePanel.add(Title = new JLabel("Purr-fect Equations"));
        Title.setForeground(SharedResource.SIAMESE_BASE);
        Title.setFont(SharedResource.getCustomSizeFont(100));
        Title.setBorder(new EmptyBorder(150,150,0,0));

        ButtonZone = new JPanel();
        ButtonZone.setLayout(new BoxLayout(ButtonZone, BoxLayout.Y_AXIS));
        ButtonZone.setPreferredSize(new Dimension(400, 800));
        ButtonZone.setBackground(new Color(255, 255, 255, 0));
        ButtonZone.setBorder(new EmptyBorder(50,100,0,50));
        ButtonZone.setOpaque(false);


//        ButtonZone.add(exitButton);
//        setButton(exitButton);
        Title = new JLabel("Purr-fect Equations");
        Title.setForeground(SharedResource.SIAMESE_BASE);
        Title.setBorder(new EmptyBorder(0, 50, 0, 0));
        Title.setFont(SharedResource.getCustomSizeFont(100));


        ButtonZone = new JPanel();
        ButtonZone.setLayout(new BoxLayout(ButtonZone, BoxLayout.Y_AXIS));
        ButtonZone.setPreferredSize(new Dimension(600, 800));
        ButtonZone.setBackground(new Color(255, 255, 255, 0));
        ButtonZone.setBorder(new EmptyBorder(0,100,0,50));
        ButtonZone.setOpaque(false);

        ButtonZone.add(playerButton);
        setButton(playerButton);
        ButtonZone.add(botButton);
        setButton(botButton);
//        ButtonZone.add(selectBotButton);
//        setButton(selectBotButton);
        ButtonZone.add(backButton);
        setButton(backButton);

        ButtonZone.add(Title);
        ButtonZone.add(playerButton);
        ButtonZone.add(botButton);
        ButtonZone.add(backButton);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel,BoxLayout.Y_AXIS));
        rightPanel.add(Box.createVerticalGlue());
        rightPanel.add(ButtonZone);
        rightPanel.add(Box.createVerticalGlue());
        rightPanel.setBackground(SharedResource.SIAMESE_BRIGHT);
        ButtonZone.setPreferredSize(new Dimension(600,700));

        JLabel picture = new JLabel();
        picture.setIcon(ResourceLoader.loadPicture("assets/SelectMode.webp",800,600));

        JPanel animationPanel = new JPanel();
        animationPanel.setLayout(new BoxLayout(animationPanel,BoxLayout.X_AXIS));
        Box verticalBox = Box.createVerticalBox();
        verticalBox.add(Box.createVerticalGlue());
        verticalBox.add(picture);
        verticalBox.add(Box.createVerticalGlue());
        animationPanel.add(verticalBox);
        animationPanel.setBackground(SharedResource.SIAMESE_BRIGHT);

        mainPanel.add(rightPanel, BorderLayout.CENTER);
        mainPanel.add(animationPanel,BorderLayout.WEST);
        mainFrame.addKeyListener(this);
        String resolution = SettingController.resolutionList.get(UserPreference.getInstance().getResolutionIndex());
        if (resolution.equals("1366x768")){
            ButtonZone.setPreferredSize(new Dimension(600,520));
            Title.setFont(SharedResource.getCustomSizeFont(64));
            playerButton.setFont(SharedResource.getCustomSizeFont(36));
            botButton.setFont(SharedResource.getCustomSizeFont(36));
            backButton.setFont(SharedResource.getCustomSizeFont(36));
            picture.setIcon(ResourceLoader.loadPicture("assets/SelectMode.webp",480,360));
        }
        setupMainPanel();
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    // Set button properties for transparency
    public void setButton(JButton button) {
        button.setOpaque(false);
        button.setBackground(new Color(255, 255, 255, 0));
        button.setBorder(new EmptyBorder(5, 50, 0, 0));
        button.setFocusPainted(false);
        button.setForeground(SharedResource.SIAMESE_DARK);
        button.setIcon(ResourceLoader.loadPicture("assets/catpaw_icon.png", 30, 30));
        button.setHorizontalTextPosition(SwingConstants.RIGHT);
        button.setIconTextGap(10);
        Dimension defaultSize = new Dimension(300, 50);
        Font defaultFont = SharedResource.getCustomSizeFont(36);

        //button.setPreferredSize(defaultSize);
        button.setFont(defaultFont);

        // Increase Play button size and font

        button.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SFXPlayer.playSound("Game/src/assets/Audio/SFX/Button_Click.wav");

        if (e.getSource().equals(botButton)){
            //Player player = new Player(UserPreference.getInstance().getProfile().getName(),UserPreference.getInstance().getProfile().getProfilePictureURL());
            //player.setDeck(new Deck("Clown"));
            Router.setRoute("SelBot",null);
        }
       else if (e.getSource().equals(playerButton)){
            Router.setRoute("Player",null);
        }
        else if (e.getSource().equals(backButton)){
            Router.setRoute("MainMenu",null);
        }
        this.getMainFrame().removeKeyListener(this);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code;

        if (!this.getMainPanel().isFocusable())
            return;
        code = e.getKeyCode();
        if (code == KeyEvent.VK_ESCAPE){
            this.getMainFrame().removeKeyListener(this);
            Router.setRoute("MainMenu",null);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
