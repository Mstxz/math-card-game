package GUI.Page;

import GUI.Component.Loader;
import GUI.Router;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import GUI.Component.MainMenuButton;

import utils.SharedResource;
import AudioPlayer.SFXPlayer;
import AudioPlayer.BGMPlayer;

public class MainMenuPage extends Page implements ActionListener {
    private JPanel  ButtonZone;
    private JPanel  TitlePanel;
    private MainMenuButton playButton;
    private MainMenuButton yourDecksButton;
    private MainMenuButton tutorialButton;
    private MainMenuButton settingsButton;
    private MainMenuButton creditButton;
    private MainMenuButton exitButton;
    private JLabel  Title;
    private Image   bg;

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

        playButton = new MainMenuButton("PLAY", 40);
        yourDecksButton = new MainMenuButton("Your Decks");
        tutorialButton = new MainMenuButton("Tutorial");
        settingsButton = new MainMenuButton("Settings");
        creditButton = new MainMenuButton("Credits");
        exitButton = new MainMenuButton("Exit");

        ButtonZone.add(playButton);
        ButtonZone.add(yourDecksButton);
        ButtonZone.add(tutorialButton);
        ButtonZone.add(settingsButton);
        ButtonZone.add(creditButton);
        ButtonZone.add(exitButton);

        playButton.addActionListener(this);
        yourDecksButton.addActionListener(this);
        tutorialButton.addActionListener(this);
        settingsButton.addActionListener(this);
        creditButton.addActionListener(this);
        exitButton.addActionListener(this);

        mainPanel.add(TitlePanel, BorderLayout.NORTH);
        mainPanel.add(ButtonZone, BorderLayout.CENTER);
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
            Router.setRoute("SelMode",null);
        }
        else if (e.getSource().equals(exitButton)){
            System.exit(0);
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
                    Router.setRoute("DeckCreator",deckCreatorPage);
                }
            };
            loadingScreen.startLoad();
        }
        else if (e.getSource().equals(settingsButton)){
            Router.setRoute("Setting",null);
        }
        else if (e.getSource().equals(tutorialButton)){
            Router.setRoute("Tutorial",null);
        }
    }


}
