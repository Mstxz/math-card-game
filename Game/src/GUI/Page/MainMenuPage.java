package GUI.Page;

import GUI.Component.*;
import GUI.Router;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import GUI.Setting.Controller.SettingController;
import GUI.Setting.UserPreference;
import utils.SharedResource;
import AudioPlayer.SFXPlayer;
import AudioPlayer.BGMPlayer;

public class MainMenuPage extends Page implements ActionListener, KeyListener {
    private JPanel  ButtonZone;
    private JPanel  TitlePanel;
    private MainMenuButton playButton;
    private MainMenuButton yourDecksButton;
    private MainMenuButton statsButton;
    private MainMenuButton settingsButton;
    private MainMenuButton tutorialButton;
    private MainMenuButton creditButton;
    private MainMenuButton exitButton;
    private JLabel  Title;
    private Image   bg;
    private MainMenuAnimation animation;

    private String track = "Game/src/assets/Audio/BGM/Lobby_BGM.wav";

    public MainMenuPage() {
        super();
        try {
            bg = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("assets/blankBG.jpg"))).getImage();
        } catch (Exception e) {
            System.out.println("Error loading background image: " + e.getMessage());
        }
        initComponents();

        try {
            ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("assets/icon.png")));
            mainFrame.setIconImage(icon.getImage());
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
        mainPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (bg != null) {
                    g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

//        TitlePanel = new JPanel(new BorderLayout());
//        TitlePanel.setBackground(new Color(255, 255, 255, 0));
//        TitlePanel.add();
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

        playButton = new MainMenuButton("PLAY", 40);
        yourDecksButton = new MainMenuButton("Your Decks");
        statsButton = new MainMenuButton("Statistics");
        tutorialButton = new MainMenuButton("Tutorial");
        settingsButton = new MainMenuButton("Settings");
        creditButton = new MainMenuButton("Credits");
        exitButton = new MainMenuButton("Exit");

        exitButton.setFocusable(false);

        ButtonZone.add(Title);
        ButtonZone.add(playButton);
        ButtonZone.add(yourDecksButton);
        ButtonZone.add(statsButton);
        ButtonZone.add(tutorialButton);
        ButtonZone.add(settingsButton);
        ButtonZone.add(creditButton);
        ButtonZone.add(exitButton);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel,BoxLayout.Y_AXIS));
        rightPanel.add(Box.createVerticalGlue());
        rightPanel.add(ButtonZone);
        rightPanel.add(Box.createVerticalGlue());
        rightPanel.setBackground(SharedResource.SIAMESE_BRIGHT);
        ButtonZone.setPreferredSize(new Dimension(600,700));

        animation = new MainMenuAnimation();

        playButton.addActionListener(this);
        yourDecksButton.addActionListener(this);
        statsButton.addActionListener(this);
        tutorialButton.addActionListener(this);
        settingsButton.addActionListener(this);
        creditButton.addActionListener(this);
        exitButton.addActionListener(this);

        JPanel animationPanel = new JPanel();
        animationPanel.setLayout(new BoxLayout(animationPanel,BoxLayout.X_AXIS));
        Box verticalBox = Box.createVerticalBox();
        verticalBox.add(Box.createVerticalGlue());
        verticalBox.add(animation);
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
            animation.updateSize(480,360);
            playButton.setFont(SharedResource.getCustomSizeFont(46));
            yourDecksButton.setFont(SharedResource.getCustomSizeFont(36));
            statsButton.setFont(SharedResource.getCustomSizeFont(36));
            tutorialButton.setFont(SharedResource.getCustomSizeFont(36));
            settingsButton.setFont(SharedResource.getCustomSizeFont(36));
            creditButton.setFont(SharedResource.getCustomSizeFont(36));
            exitButton.setFont(SharedResource.getCustomSizeFont(36));
        }
        setupMainPanel();
    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SFXPlayer.playSound("Game/src/assets/Audio/SFX/Button_Click.wav");

        if (e.getSource().equals(playButton)){
            animation.stop();
            Router.setRoute("SelMode",null);
        }
        else if (e.getSource().equals(exitButton)){
            showOverlay(new MenuConfirm(this, "Are you sure to exit?") {
                @Override
                public void onConfirm() {   System.exit(0); }

                @Override
                public void onDenied() {    this.setVisible(false); }
            }, OverlayPlacement.CENTER);
        }
        else if (e.getSource().equals(yourDecksButton)){
            Loader loadingScreen = new Loader(this,"Loading Your Deck..."){
                public Page deckCreatorPage;
                @Override
                public boolean closeCondition() {
                    deckCreatorPage = new DeckCreatorPage();
                    return true;
                }

                @Override
                public void onClose() {
                    animation.stop();
                    Router.setRoute("DeckCreator",deckCreatorPage);
                }
            };
            loadingScreen.startLoad();
        }
        else if (e.getSource().equals(settingsButton)){
            animation.stop();
            Router.setRoute("Setting",null);
        }
        else if (e.getSource().equals(statsButton)){
            animation.stop();
            Router.setRoute("Statistics",null);
        }
        else if (e.getSource().equals(tutorialButton)){
            animation.stop();
            (new HowToPlaySlide(this)).setVisible(
                    true
            );
            return;
            //Router.setRoute("Tutorial",null);
        }
        else if (e.getSource().equals(creditButton)){
            animation.stop();
            Router.setRoute("Credit",null);
        }
        this.mainFrame.removeKeyListener(this);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code;

        if (!this.getMainPanel().isFocusable())
            return;
        code = e.getKeyCode();
        if (code == KeyEvent.VK_ESCAPE){
            showOverlay(new MenuConfirm(this, "Are you sure to exit?") {
                @Override
                public void onConfirm() {
                    SFXPlayer.playSound("Game/src/assets/Audio/SFX/Button_Click.wav");
                    System.exit(0);
                }

                @Override
                public void onDenied() {
                    SFXPlayer.playSound("Game/src/assets/Audio/SFX/Button_Click.wav");
                    this.setVisible(false);
                }
            }, OverlayPlacement.CENTER);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
