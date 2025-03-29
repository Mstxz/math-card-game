package GUI.Page;

import AudioPlayer.BGMPlayer;
import AudioPlayer.SFXPlayer;
import GUI.Component.ExitButton;
import GUI.Router;
import GUI.Setting.UserPreference;
import Gameplay.Bot;
import Gameplay.Deck;
import Gameplay.GameForGUI;
import Gameplay.Player;
import utils.SharedResource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class SelectBotRoom extends Page implements ActionListener {
    private JPanel panelA, panelB, panelC, panelD, panelE;
    private JButton exit, previousBotButton, nextBotButton, decksButton, startButton;
    private JLabel chooseOpponent, selectingBotName, selectingBotProfileImage, selectingBotDescription;

    public SelectBotRoom() {
        initComponents();
        setupLayout();
        setupListeners();
    }

    private void initComponents() {
        panelA = new JPanel();
        panelB = new JPanel();
        panelC = new JPanel();
        panelD = new JPanel();
        panelE = new JPanel();

        exit = new ExitButton("SelMode");
        previousBotButton = new JButton("<");
        nextBotButton = new JButton(">");
        decksButton = new JButton("Decks");
        startButton = new JButton("Start");

        chooseOpponent = new JLabel("Choose Your Opponent");
        selectingBotName = new JLabel("Bot Name");
        selectingBotProfileImage = new JLabel("Bot PFP");
        selectingBotDescription = new JLabel("Bot description for explaining its behavior, pattern, and lore add-on");

        chooseOpponent.setForeground(SharedResource.SIAMESE_BASE);
        chooseOpponent.setFont(SharedResource.getCustomSizeFont(80));
        selectingBotName.setForeground(SharedResource.SIAMESE_BASE);
        selectingBotName.setFont(SharedResource.getCustomSizeFont(40));
    }

    private void setupLayout() {
        this.mainPanel.setLayout(new GridLayout(5, 1));

        panelA.setLayout(new FlowLayout());
        panelA.add(exit);
        panelA.add(chooseOpponent);

        panelB.add(selectingBotName);

        panelC.add(previousBotButton);
        panelC.add(selectingBotProfileImage);
        panelC.add(nextBotButton);

        panelD.add(selectingBotDescription);

        panelE.add(decksButton);
        panelE.add(startButton);

        this.mainPanel.add(panelA);
        this.mainPanel.add(panelB);
        this.mainPanel.add(panelC);
        this.mainPanel.add(panelD);
        this.mainPanel.add(panelE);
    }

    private void setupListeners() {
        startButton.addActionListener(this);
        decksButton.addActionListener(this);
        exit.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            BGMPlayer.stopBackgroundMusic();
            SFXPlayer.playSound("Game/src/assets/Audio/SFX/Game_Start.wav");

            Player player = new Player(
                    UserPreference.getInstance().getProfile().getName(),
                    UserPreference.getInstance().getProfile().getProfilePictureURL()
            );
            player.setDeck(new Deck("a"));

            try {
                player.setDeck(Deck.LoadDeck("a"));
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }

            Player bot = new Bot();
            ArrayList<Player> players = new ArrayList<>();
            players.add(player);
            players.add(bot);

            GameForGUI botGame = new GameForGUI(players);
            Router.setRoute("Avenger", botGame);
        } else if (e.getSource() == decksButton) {
            SFXPlayer.playSound("Game/src/assets/Audio/SFX/Button_Click.wav");
        }
    }
}
