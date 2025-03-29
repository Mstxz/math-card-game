package GUI.Page;


import AudioPlayer.BGMPlayer;
import AudioPlayer.SFXPlayer;
import GUI.Component.ExitButton;
import GUI.Router;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.swing.*;

import GUI.Setting.UserPreference;
import Gameplay.Bot.Mystyr;
import Gameplay.Deck;
import Gameplay.GameForGUI;
import Gameplay.Player;
import utils.SharedResource;

public class SelectBotRoom extends Page implements ActionListener {
    private JPanel panelA = new JPanel();
    private JPanel panelB = new JPanel();
    private JPanel panelC = new JPanel();
    private JPanel panelD = new JPanel();
    private JPanel panelE = new JPanel();
    private JButton exit = new JButton("< Exit");
    private JButton previousBotButton = new JButton("<");
    private JButton nextBotButton = new JButton(">");
    private JButton decksButton = new JButton("Decks");
    private JButton startButton = new JButton("Start");
    private JLabel chooseOpponent = new JLabel("Choose Your Opponent");
    private JLabel selectingBotName = new JLabel("Bot Name");
    private JLabel selectingBotProfileImage = new JLabel("Bot PFP");
    private JLabel selectingBotDescription = new JLabel("Bot description for explaining its behavior, pattern, and lore add-on");

    public SelectBotRoom() {
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

        this.mainPanel.setLayout(new GridLayout(5,1));
        this.mainPanel.add(panelA);
        this.mainPanel.add(panelB);
        this.mainPanel.add(panelC);
        this.mainPanel.add(panelD);
        this.mainPanel.add(panelE);

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

        setButton(previousBotButton);
        setButton(nextBotButton);
        setButton(exit);
        setButton(decksButton);
        setButton(startButton);

        this.mainPanel.setVisible(true);

        startButton.addActionListener(this);
        decksButton.addActionListener(this);
        exit.addActionListener(this);
    }

    /*มันมี Component อยู่แล้วครับอ้าย*/
    public void setButton(JButton button) {
        //setแล้วมันบั๊คครับ(textจะซ้อนทับเวลาHoverเมาส์ที่ปุ่ม)
        //button.setForeground(SharedResource.SIAMESE_BASE);
        //button.setFont(SharedResource.getCustomSizeFont(40));
        //button.setBackground(new Color(255, 255, 255, 0));
        //button.setBorder(new EmptyBorder(0, 0, 0, 0));
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton){
            BGMPlayer.stopBackgroundMusic();
            SFXPlayer.playSound("Game/src/assets/Audio/SFX/Game_Start.wav");

            Player player = new Player(UserPreference.getInstance().getProfile().getName(),UserPreference.getInstance().getProfile().getProfilePictureURL());
            player.setDeck(new Deck("a"));
            try {
                player.setDeck(Deck.LoadDeck("a"));
            }
            catch (FileNotFoundException ex){
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
