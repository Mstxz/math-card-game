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
import GUI.Component.ExitButton;
import GUI.Component.BotLeftButton;
import GUI.Component.BotRightButton;
import GUI.Router;
import GUI.Setting.UserPreference;
import Gameplay.Bot.Mystyr;
import Gameplay.Deck;
import Gameplay.GameForGUI;
import Gameplay.Player;
import utils.SharedResource;
import java.net.URL;
import GUI.Component.RoundBorder;
import GUI.Component.RoundedPanel;
import utils.*;

import utils.UIManager.ButtonUI;
import utils.UIManager.CustomScrollBarUI;
import utils.ResourceLoader;
import utils.SharedResource;
import utils.UIManager.RoundPanelUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;

public class SelectBotRoom extends Page implements ActionListener {
    private JPanel mainColorPanel, panelA, panelB, panelC, panelD, panelE;
    private JButton exit, previousBotButton, nextBotButton, decksButton, startButton;
    private JLabel chooseOpponent, selectingBotName, selectingBotProfileImage, selectingBotDescription;
    private ImageIcon botImage;
    private JPanel botProfilePanel;

    public SelectBotRoom() {
        initComponents();
        setupLayout();
        setupListeners();
    }

    private void initComponents() {
        botImage = ResourceLoader.loadPicture("assets/Profile/Karn Bob.webp", 200, 200);

        selectingBotProfileImage = new JLabel(botImage);
        selectingBotProfileImage.setHorizontalAlignment(SwingConstants.CENTER);

        selectingBotName = new JLabel("Select Bot");
        selectingBotName.setForeground(Color.WHITE); // ทำให้ข้อความอ่านง่ายขึ้น
        selectingBotName.setFont(SharedResource.getCustomSizeFont(24));
        selectingBotName.setHorizontalAlignment(SwingConstants.CENTER);

        // ✅ ใช้ OverlayLayout เพื่อให้ชื่อบอทอยู่บนรูปภาพ
        botProfilePanel = new JPanel();
        botProfilePanel.setLayout(new OverlayLayout(botProfilePanel));
        botProfilePanel.setOpaque(false); // ทำให้โปร่งใส
        botProfilePanel.add(selectingBotProfileImage);
        botProfilePanel.add(selectingBotName); // ชื่อบอทจะอยู่ด้านบนของรูป

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
        decksButton.setUI(new ButtonUI());
        decksButton.setPreferredSize(new Dimension(356, 99));

        startButton = new JButton("Start");
        startButton.setUI(new ButtonUI());
        startButton.setPreferredSize(new Dimension(356, 99));

        chooseOpponent = new JLabel("Choose Your Opponent");
        selectingBotDescription = new JLabel("Bot description for explaining its behavior, pattern, and lore add-on");

        //JTextArea selectingBotDescription = new JTextArea("Bot description for explaining its behavior, pattern, and lore add-on");

        chooseOpponent.setForeground(SharedResource.SIAMESE_BASE);
        chooseOpponent.setFont(SharedResource.getCustomSizeFont(80));
        selectingBotName.setForeground(SharedResource.SIAMESE_BASE);
        selectingBotName.setFont(SharedResource.getCustomSizeFont(40));
    }


    private void setupLayout() {
        this.mainPanel.setLayout(new BorderLayout());

        mainColorPanel.setLayout(new GridLayout(4, 1));
        mainColorPanel.setBackground(new Color(221, 218, 210));

        panelA.setOpaque(false);
        panelB.setOpaque(false);
        panelC.setOpaque(false);
        panelD.setOpaque(false);
        panelE.setOpaque(false);

        panelA.setLayout(new FlowLayout());
        panelA.add(exit);
        panelA.add(chooseOpponent);

        panelB.setLayout(new BorderLayout());

        panelB.add(selectingBotName, BorderLayout.NORTH);

        // ใช้ FlowLayout กับการจัดตำแหน่งให้กลางในแนวนอน
        panelC.setLayout(new FlowLayout(FlowLayout.CENTER)); // การตั้งค่าตรงนี้สำคัญ
        panelC.add(previousBotButton);
        panelC.add(botProfilePanel); // ✅ ใช้ botProfilePanel แทน selectingBotProfileImage
        panelC.add(nextBotButton);

        panelB.add(panelC, BorderLayout.CENTER);

        RoundedPanel descriptionPanel = new RoundedPanel(30, new Color(191, 180, 168));
        descriptionPanel.setLayout(new BorderLayout());
        descriptionPanel.setPreferredSize(new Dimension(800, 240)); // ขนาดของ Panel

        descriptionPanel.setBorder(new RoundBorder(new Color(191, 180, 168), 0, 45)); // ไม่มีเส้นขอบ แต่มีความโค้ง

        selectingBotDescription.setBorder(new EmptyBorder(10, 15, 10, 15));
        selectingBotDescription.setHorizontalAlignment(SwingConstants.CENTER);
        selectingBotDescription.setForeground(new Color(100, 90, 82)); // เปลี่ยนสีข้อความให้ตัดกับพื้นหลัง

        descriptionPanel.add(selectingBotDescription, BorderLayout.CENTER);

        panelD.setLayout(new FlowLayout(FlowLayout.CENTER)); // จัดตำแหน่งให้อยู่ตรงกลาง
        panelD.add(descriptionPanel);

        panelE.add(decksButton);
        panelE.add(startButton);

        mainColorPanel.add(panelA);
        mainColorPanel.add(panelB);
        //mainColorPanel.add(panelC);
        mainColorPanel.add(panelD);
        mainColorPanel.add(panelE);

        this.mainPanel.add(mainColorPanel, BorderLayout.CENTER);
    }

    // 📌 ฟังก์ชันสำหรับอัปเดตรูปภาพบอท
    private void updateBotProfile(String imagePath, String botName, String botDescription) {
        System.out.println("Loading new bot image from path: " + imagePath);

        ImageIcon loadedImage = ResourceLoader.loadPicture(imagePath, 200, 200);

        if (loadedImage != null) {
            selectingBotProfileImage.setIcon(loadedImage);
            selectingBotName.setText(botName); // ✅ อัปเดตชื่อบอท
            selectingBotDescription.setText(botDescription); // ✅ อัปเดตคำอธิบายบอท

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

        previousBotButton.addActionListener(e -> updateBotProfile(
                "assets/Profile/Karn Bob.webp",
                "Karn Bob",
                "Karn Bob is a master level player of Purrfect equation.\n" +
                        "(S)he will use various strategy to win."
        ));

        nextBotButton.addActionListener(e -> updateBotProfile(
                "assets/Profile/Mystyr.webp",
                "Mystyr",
                "Mystyr is a new bot I wait for description..."
        ));
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


