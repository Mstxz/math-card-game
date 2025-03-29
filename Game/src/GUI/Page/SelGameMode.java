package GUI.Page;

import AudioPlayer.BGMPlayer;
import AudioPlayer.SFXPlayer;
import GUI.Component.MainMenuButton;
import GUI.Router;
import GUI.Setting.UserPreference;
import Gameplay.Bot;
import Gameplay.Deck;
import Gameplay.GameForGUI;
import Gameplay.Player;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import utils.ResourceLoader;
import utils.SharedResource;

public class SelGameMode extends Page implements ActionListener {
    private JPanel ButtonZone;
    private JPanel TitlePanel;
    private JLabel Title;
    private MainMenuButton playerButton = new MainMenuButton("Player vs Player");
    private MainMenuButton botButton = new MainMenuButton("Player vs Bot");
    private MainMenuButton exitButton = new MainMenuButton("exit");
    private MainMenuButton backButton = new MainMenuButton("Back");
    private MainMenuButton selectBotButton = new MainMenuButton("Select Bot Test");

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

        ButtonZone.add(playerButton);
        setButton(playerButton);
        ButtonZone.add(botButton);
        setButton(botButton);
        ButtonZone.add(selectBotButton);
        setButton(selectBotButton);
        ButtonZone.add(backButton);
        setButton(backButton);
//        ButtonZone.add(exitButton);
//        setButton(exitButton);

        mainPanel.add(TitlePanel, BorderLayout.NORTH);
        mainPanel.add(ButtonZone, BorderLayout.CENTER);
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
            Player player = new Player(UserPreference.getInstance().getProfile().getName(),UserPreference.getInstance().getProfile().getProfilePictureURL());
            player.setDeck(new Deck("Clown"));
            try {
                player.setDeck(Deck.LoadDeck("Mstxz"));
            }
            catch (FileNotFoundException ex){
                ex.printStackTrace();
            }
            Player bot = new Bot();
            ArrayList<Player> p = new ArrayList<>();
            p.add(player);
            p.add(bot);
            GameForGUI botGame = new GameForGUI(p);
            Router.setRoute("Avenger",botGame);
        }
       else if (e.getSource().equals(playerButton)){
            Router.setRoute("Player",null);
        }
        else if (e.getSource().equals(backButton)){
            Router.setRoute("MainMenu",null);
        }
        else if (e.getSource().equals(selectBotButton)){
            Router.setRoute("SelBot",null);
        }

    }
}
