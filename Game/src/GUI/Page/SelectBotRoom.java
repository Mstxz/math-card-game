/*
package GUI.Page;

import AudioPlayer.BGMPlayer;
import AudioPlayer.SFXPlayer;
import GUI.Component.ExitButton;
import GUI.Component.BotSelectedPanel;
import GUI.Router;
import GUI.Setting.UserPreference;
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
    private JButton exit, decksButton, startButton;
    private JLabel chooseOpponent;
    private BotSelectedPanel botSelectedPanel;

    public SelectBotRoom() {
        initComponents();
        setupLayout();
        setupListeners();
    }

    private void initComponents() {
        botSelectedPanel = new BotSelectedPanel();

        mainColorPanel = new JPanel();
        panelA = new JPanel();
        panelB = new JPanel();
        panelC = new JPanel();
        panelD = new JPanel();
        panelE = new JPanel();

        exit = new ExitButton("SelMode");

        decksButton = new JButton("Decks");
        decksButton.setUI(new utils.UIManager.ButtonUI());
        decksButton.setPreferredSize(new Dimension(250, 80));

        startButton = new JButton("Start");
        startButton.setUI(new utils.UIManager.ButtonUI());
        startButton.setPreferredSize(new Dimension(250, 80));

        chooseOpponent = new JLabel("Choose Your Opponent");
        chooseOpponent.setForeground(SharedResource.SIAMESE_BASE);
        chooseOpponent.setFont(SharedResource.getCustomSizeFont(80));
    }

    private void setupLayout() {
        this.mainPanel.setLayout(new BorderLayout());

        mainColorPanel.setLayout(new GridLayout(5, 1));
        mainColorPanel.setBackground(new Color(221, 218, 210));

        panelA.setOpaque(false);
        panelB.setOpaque(false);
        panelC.setOpaque(false);
        panelD.setOpaque(false);
        panelE.setOpaque(false);

        panelA.setLayout(new FlowLayout());
        panelA.add(exit);
        panelA.add(chooseOpponent);

        panelC.setLayout(new BorderLayout());
        panelC.add(botSelectedPanel, BorderLayout.CENTER);

        panelE.add(decksButton);
        panelE.add(startButton);

        mainColorPanel.add(panelA);
        mainColorPanel.add(panelC);
        mainColorPanel.add(panelE);

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
            Player bot = new Player(botSelectedPanel.getSelectedBot());
            ArrayList<Player> p = new ArrayList<>();
            p.add(player);
            p.add(bot);
            GameForGUI botGame = new GameForGUI(p);
            Router.setRoute("Avenger", botGame);
        } else if (e.getSource() == decksButton) {
            SFXPlayer.playSound("Game/src/assets/Audio/SFX/Button_Click.wav");
        }
    }
}

*/



package GUI.Page;

import AudioPlayer.BGMPlayer;
import AudioPlayer.SFXPlayer;
import AudioPlayer.SFXSwitcher;
import GUI.Component.*;
import GUI.Router;
import GUI.Setting.UserPreference;
import Gameplay.Bot.*;
import Gameplay.Deck;
import Gameplay.GameForGUI;
import Gameplay.Player;
import utils.SharedResource;

import java.io.File;
import java.net.URL;

import utils.*;

import utils.UIManager.ButtonUI;
import utils.UIManager.CustomScrollBarUI;
import utils.ResourceLoader;
import utils.SharedResource;
import utils.UIManager.RoundPanelUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;

public class SelectBotRoom extends Page implements ActionListener {
    private JPanel panelA, panelB, panelC, panelD, panelE;
    private JButton exit, previousBotButton, nextBotButton, decksButton, startButton;
    private JLabel chooseOpponent, selectingBotName, selectingBotProfileImage, selectingBotDescription;;
    private JPanel botProfilePanel;

    private ArrayList<BotInfo> botList;
    private int currentIndex = 0;

    private String userDeckName;

    public SelectBotRoom() {
        setUpBotList();
        initComponents();
        setupLayout();
        setupListeners();
    }

    private void initComponents() {
        selectingBotProfileImage = new JLabel(new ImageIcon(botList.get(0).getImg().getImage().getScaledInstance(200,200,Image.SCALE_SMOOTH
        )));
        selectingBotProfileImage.setHorizontalAlignment(SwingConstants.CENTER);

        selectingBotName = new JLabel(botList.get(0).getName());
        selectingBotName.setForeground(Color.WHITE); // ทำให้ข้อความอ่านง่ายขึ้น
        selectingBotName.setFont(SharedResource.getCustomSizeFont(24));
        selectingBotName.setHorizontalAlignment(SwingConstants.CENTER);

        botProfilePanel = new JPanel();
        botProfilePanel.setLayout(new BorderLayout());
        botProfilePanel.setOpaque(false);
        botProfilePanel.add(selectingBotProfileImage,BorderLayout.CENTER);
        botProfilePanel.add(selectingBotName,BorderLayout.NORTH);

        panelA = new JPanel();
        panelB = new JPanel();
        panelC = new JPanel();
        panelD = new JPanel();
        panelE = new JPanel();

        exit = new ExitButton("SelMode");
        previousBotButton = new JButton("<");
        cleanup(previousBotButton);
        nextBotButton = new JButton(">");
        cleanup(nextBotButton);

        decksButton = new JButton("Decks");
        decksButton.setUI(new ButtonUI());
        decksButton.setPreferredSize(new Dimension(250, 80));

        startButton = new JButton("Start");
        startButton.setUI(new ButtonUI());
        startButton.setPreferredSize(new Dimension(250, 80));

        chooseOpponent = new JLabel("Choose Your Opponent");
        selectingBotDescription = new JLabel(botList.get(0).getDescription());

        //JTextArea selectingBotDescription = new JTextArea("Bot description for explaining its behavior, pattern, and lore add-on");

        chooseOpponent.setForeground(SharedResource.SIAMESE_BASE);
        chooseOpponent.setFont(SharedResource.getCustomSizeFont(80));
        selectingBotName.setForeground(SharedResource.SIAMESE_BASE);
        selectingBotName.setFont(SharedResource.getCustomSizeFont(40));

        startButton.setEnabled(false);
    }


    private void setupLayout() {
        this.mainPanel.setLayout(new BorderLayout());

        mainPanel.setBackground(SharedResource.SIAMESE_BRIGHT);

        panelA.setOpaque(false);
        panelB.setOpaque(false);
        //panelD.setBorder(new LineBorder(SharedResource.SIAMESE_DARK,5));
        panelE.setPreferredSize(new Dimension(500,180));
        //panelA.setBorder(new LineBorder(SharedResource.SIAMESE_DARK,5));
        panelC.setOpaque(false);
        panelD.setOpaque(false);
        panelE.setOpaque(false);

        panelA.setLayout(new BorderLayout());
        panelA.add(exit,BorderLayout.WEST);
        exit.setAlignmentY(Component.TOP_ALIGNMENT);
        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));
        p.setBackground(SharedResource.SIAMESE_BRIGHT);
        p.add(chooseOpponent);
        panelA.add(p);

        panelB.setLayout(new BorderLayout());

        panelC.setLayout(new BorderLayout());
        panelC.add(selectingBotName, BorderLayout.NORTH);

        // ใช้ FlowLayout กับการจัดตำแหน่งให้กลางในแนวนอน
        JPanel tmp = new JPanel();
        tmp.setLayout(new FlowLayout(FlowLayout.CENTER)); // การตั้งค่าตรงนี้สำคัญ
        tmp.add(previousBotButton);
        tmp.add(botProfilePanel); // ✅ ใช้ botProfilePanel แทน selectingBotProfileImage
        tmp.add(nextBotButton);
        tmp.setBackground(SharedResource.SIAMESE_BRIGHT);
        panelC.add(tmp,BorderLayout.CENTER);

        panelB.add(panelC, BorderLayout.NORTH);

        JPanel descriptionPanel = new JPanel();
        descriptionPanel.setUI(new RoundPanelUI(SharedResource.SIAMESE_LIGHT,30,30));
        descriptionPanel.setLayout(new BorderLayout());
        descriptionPanel.setPreferredSize(new Dimension(800, 240)); // ขนาดของ Panel

        selectingBotDescription.setHorizontalAlignment(SwingConstants.CENTER);
        selectingBotDescription.setForeground(SharedResource.SIAMESE_BASE);

        descriptionPanel.setBorder(new EmptyBorder(0,30,0,30));
        descriptionPanel.add(selectingBotDescription, BorderLayout.CENTER);

        panelD.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelD.add(descriptionPanel);

        panelB.add(panelD,BorderLayout.CENTER);

        panelE.add(decksButton);
        panelE.add(startButton);

        panelB.add(panelE,BorderLayout.SOUTH);

        mainPanel.add(panelA,BorderLayout.NORTH);
        mainPanel.add(panelB,BorderLayout.CENTER);
        //mainColorPanel.add(panelC);
        //mainColorPanel.add(panelD);
        //mainPanel.add(panelE,BorderLayout.SOUTH);
    }

    // 📌 ฟังก์ชันสำหรับอัปเดตรูปภาพบอท
    private void updateBotProfile() {
        //System.out.println("Loading new bot image from path: " + imagePath);

        ImageIcon loadedImage = new ImageIcon(botList.get(currentIndex).getImg().getImage().getScaledInstance(200,200,Image.SCALE_SMOOTH));

        if (loadedImage != null) {
            selectingBotProfileImage.setIcon(loadedImage);
            selectingBotName.setText(botList.get(currentIndex).getName()); // ✅ อัปเดตชื่อบอท
            selectingBotDescription.setText("<html>"+botList.get(currentIndex).getDescription()+"</html>"); // ✅ อัปเดตคำอธิบายบอท

            selectingBotProfileImage.revalidate();
            selectingBotProfileImage.repaint();
            selectingBotName.revalidate();
            selectingBotName.repaint();
            selectingBotDescription.revalidate();
            selectingBotDescription.repaint();

            System.out.println("Updated bot profile successfully.");
        } else {
            System.err.println("Failed to update bot profile - Image not found");
        }
    }

    // 📌 เชื่อมปุ่มเปลี่ยนบอทกับ updateBotProfile()
    private void setupListeners() {
        startButton.addActionListener(this);
        decksButton.addActionListener(this);
        exit.addActionListener(this);

        previousBotButton.addActionListener(this);

        nextBotButton.addActionListener(this);
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
            player.setDeck(new Deck(userDeckName));

            try {
                player.setDeck(Deck.LoadDeck(userDeckName));
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            ArrayList<Player> p = new ArrayList<>();
            p.add(player);
            p.add(botList.get(currentIndex).getBotPlayer());
            GameForGUI botGame = new GameForGUI(p);
            Router.setRoute("Avenger",botGame);
        }
        else if (e.getSource() == decksButton){
            SFXPlayer.playSound("Game/src/assets/Audio/SFX/Button_Click.wav");
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.setCurrentDirectory(new File("Assets"));
            jFileChooser.setFileFilter(new FileNameExtensionFilter("Deck File","deck"));
            int l = jFileChooser.showOpenDialog(mainFrame);
            if (l == JFileChooser.APPROVE_OPTION){
                String deckPath = jFileChooser.getSelectedFile().getName();
                userDeckName = deckPath.split("\\.")[0];
                startButton.setEnabled(true);

            }
        }
        else if (e.getSource() == nextBotButton){
            if (currentIndex == botList.size()-1){
                currentIndex = 0;
            }
            else {
                currentIndex+=1;
            }
            SFXPlayer.playSound("Game/src/assets/Audio/SFX/Button_Click.wav");
            SFXSwitcher.botSelectSwitcher(botList.get(currentIndex).getName()); //use in case of someone wanting a signature sound to bots
            updateBotProfile();

        }
        else if (e.getSource() == previousBotButton){
            if (currentIndex == 0){
                currentIndex = botList.size()-1;
            }
            else {
                currentIndex-=1;
            }
            SFXPlayer.playSound("Game/src/assets/Audio/SFX/Button_Click.wav");
            SFXSwitcher.botSelectSwitcher(botList.get(currentIndex).getName()); //use in case of someone wanting a signature sound to bots
            updateBotProfile();
        }
    }

    private void setUpBotList(){
        botList = new ArrayList<BotInfo>();
        botList.add(new BotInfo(new Pupr()));
        botList.add(new BotInfo(new Mystyr()));
        botList.add(new BotInfo(new Who()));
        botList.add(new BotInfo(new Arsr()));
        botList.add(new BotInfo(new OmmThuk()));
    }

    private void cleanup(JButton button){
        button.setBackground(SharedResource.TRANSPARENT);
        button.setFont(SharedResource.getCustomSizeFont(48));
        button.setPreferredSize(new Dimension(50, 50));
        button.setBorder(null);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
    }
}


