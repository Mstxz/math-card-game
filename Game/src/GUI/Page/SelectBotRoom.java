package GUI.Page;

import AudioPlayer.BGMPlayer;
import AudioPlayer.SFXPlayer;
import GUI.Component.ExitButton;
import GUI.Router;
import GUI.Setting.UserPreference;
import Gameplay.Bot.Mystyr;
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
    private JPanel mainColorPanel, panelA, panelB, panelC, panelD, panelE;
    private JButton exit, previousBotButton, nextBotButton, decksButton, startButton;
    private JLabel chooseOpponent, selectingBotName, selectingBotProfileImage, selectingBotDescription;

    public SelectBotRoom() {
        initComponents();
        setupLayout();
        setupListeners();
    }

    private void initComponents() {
        mainColorPanel = new JPanel();
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
        // กำหนด Layout ให้ mainPanel
        this.mainPanel.setLayout(new BorderLayout());

        // ตั้งค่าพื้นหลังและ Layout ของ mainColorPanel
        mainColorPanel.setLayout(new GridLayout(5, 1));
        mainColorPanel.setBackground(new Color(221, 218, 210)); // ✅ เพิ่มสีพื้นหลัง

        // ทำให้ Panel ย่อยโปร่งใส
        panelA.setOpaque(false);
        panelB.setOpaque(false);
        panelC.setOpaque(false);
        panelD.setOpaque(false);
        panelE.setOpaque(false);

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

        mainColorPanel.add(panelA);
        mainColorPanel.add(panelB);
        mainColorPanel.add(panelC);
        mainColorPanel.add(panelD);
        mainColorPanel.add(panelE);

        // ✅ เพิ่ม mainColorPanel เข้าไปใน mainPanel
        this.mainPanel.add(mainColorPanel, BorderLayout.CENTER);
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
            Player bot = new Mystyr();
            ArrayList<Player> p = new ArrayList<>();
            p.add(player);
            p.add(bot);
            GameForGUI botGame = new GameForGUI(p);
            Router.setRoute("Avenger",botGame);
        }
        else if (e.getSource() == decksButton){
            SFXPlayer.playSound("Game/src/assets/Audio/SFX/Button_Click.wav");
        }
    }
}
