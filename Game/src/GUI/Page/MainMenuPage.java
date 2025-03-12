package GUI.Page;

import GUI.Router;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import GUI.Component.MainMenuButton;

import utils.SharedResource;
import Audio.SFXPlayer;
import Audio.BGMPlayer;

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

    public MainMenuPage() {
        super();
        try {
            bg = new ImageIcon(getClass().getClassLoader().getResource("assets/blankBG.jpg")).getImage();
        } catch (Exception e) {
            System.out.println("Error loading background image: " + e.getMessage());
        }
        initComponents();
=======
        try {
            ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("assets/icon.png"));
            mainFrame.setIconImage(icon.getImage());
        } catch (Exception e) {
            System.out.println("Error loading icon: " + e.getMessage());
        }

        BGMPlayer.playBackgroundMusic("Game/src/assets/Audio/ID20_LoFi.wav", -10.0f);
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
        TitlePanel.add(Title = new JLabel("Meowthematicians"));
        Title.setForeground(SharedResource.SIAMESE_BASE);
        Title.setFont(SharedResource.getCustomSizeFont(100));
        Title.setBorder(new EmptyBorder(150,150,0,0));

        ButtonZone = new JPanel();
        ButtonZone.setLayout(new BoxLayout(ButtonZone, BoxLayout.Y_AXIS));
        ButtonZone.setPreferredSize(new Dimension(400, 800));
        ButtonZone.setBackground(new Color(255, 255, 255, 0));
        ButtonZone.setBorder(new EmptyBorder(50,100,0,50));
        ButtonZone.setOpaque(false);

        playButton = new MainMenuButton("PLAY", "assets/catpaw_icon.png", 30, 30);
        yourDecksButton = new MainMenuButton("Your Decks", "assets/catpaw_icon.png", 30, 30);
        tutorialButton = new MainMenuButton("Tutorial", "assets/catpaw_icon.png", 30, 30);
        settingsButton = new MainMenuButton("Settings", "assets/catpaw_icon.png", 30, 30);
        creditButton = new MainMenuButton("Credits", "assets/catpaw_icon.png", 30, 30);
        exitButton = new MainMenuButton("Exit", "assets/catpaw_icon.png", 30, 30);

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
    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SFXPlayer.playSound("Game/src/assets/Audio/Test.wav", -10.0f);

        if (e.getSource().equals(playButton)){
            Router.setRoute("SelMode",null);
        }
        else if (e.getSource().equals(exitButton)){
            System.exit(0);
        }
        else if (e.getSource().equals(yourDecksButton)){
            Router.setRoute("DeckCreator",null);
        }
    }

}
