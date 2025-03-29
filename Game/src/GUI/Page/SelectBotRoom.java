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
import utils.UIManager.ButtonUI;

import java.net.URL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class SelectBotRoom extends Page implements ActionListener {
    private JPanel mainColorPanel, panelA, panelB, panelC, panelD, panelE;
    private JButton exit, previousBotButton, nextBotButton, decksButton, startButton;
    private JLabel chooseOpponent, selectingBotName, selectingBotDescription;

    public SelectBotRoom() {
        initComponents();
        setupLayout();
        setupListeners();
    }

    private void initComponents() {
        // Initialize the panels
        mainColorPanel = new JPanel();
        panelA = new JPanel();
        panelB = new JPanel();
        panelC = new JPanel();
        panelD = new JPanel();
        panelE = new JPanel();

        // Initialize the buttons
        exit = new ExitButton("SelMode");
        previousBotButton = new JButton("<");
        nextBotButton = new JButton(">");

        decksButton = new JButton("Decks");
        decksButton.setUI(new ButtonUI());
        decksButton.setPreferredSize(new Dimension(250, 80));

        startButton = new JButton("Start");
        startButton.setUI(new ButtonUI());
        startButton.setPreferredSize(new Dimension(250, 80));

        // Initialize the labels
        chooseOpponent = new JLabel("Choose Your Opponent");
        selectingBotName = new JLabel("Bot Name");
        selectingBotDescription = new JLabel("Bot description for explaining its behavior, pattern, and lore add-on");

        // Customize label styles
        chooseOpponent.setForeground(SharedResource.SIAMESE_BASE);
        chooseOpponent.setFont(SharedResource.getCustomSizeFont(80));
        selectingBotName.setForeground(SharedResource.SIAMESE_BASE);
        selectingBotName.setFont(SharedResource.getCustomSizeFont(40));
    }

    private void setupLayout() {
        // Set layout for the mainPanel
        this.mainPanel.setLayout(new BorderLayout());

        // Set layout and background color for mainColorPanel
        mainColorPanel.setLayout(new GridLayout(5, 1));
        mainColorPanel.setBackground(new Color(221, 218, 210)); // Background color

        // Make sub-panels transparent
        panelA.setOpaque(false);
        panelB.setOpaque(false);
        panelC.setOpaque(false);
        panelD.setOpaque(false);
        panelE.setOpaque(false);

        // Add components to the panels
        panelA.setLayout(new FlowLayout());
        panelA.add(exit);
        panelA.add(chooseOpponent);

        panelB.add(selectingBotName);

        panelC.add(previousBotButton);
        panelC.add(selectingBotDescription);
        panelC.add(nextBotButton);

        panelD.add(selectingBotDescription);

        panelE.add(decksButton);
        panelE.add(startButton);

        // Add the panels to the mainColorPanel
        mainColorPanel.add(panelA);
        mainColorPanel.add(panelB);
        mainColorPanel.add(panelC);
        mainColorPanel.add(panelD);
        mainColorPanel.add(panelE);

        // Add mainColorPanel to the mainPanel
        this.mainPanel.add(mainColorPanel, BorderLayout.CENTER);
    }

    // Function to load and update bot profile image
    private void updateBotProfile(String imagePath) {
        // Load the image directly and display
        ImageIcon loadedImage = loadImage(imagePath);

        if (loadedImage != null) {
            JLabel selectingBotProfileImage = new JLabel(); // Create a new label for image
            Image scaledImage = loadedImage.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            selectingBotProfileImage.setIcon(new ImageIcon(scaledImage));
            panelC.add(selectingBotProfileImage); // Add the image to panelC
            panelC.revalidate();
            panelC.repaint();
        } else {
            System.err.println("Failed to update bot profile - Image not found");
        }
    }

    // Function to load image from a path
    private ImageIcon loadImage(String imagePath) {
        URL imageURL = getClass().getClassLoader().getResource(imagePath);
        if (imageURL != null) {
            return new ImageIcon(imageURL);
        } else {
            System.err.println("Error: Image not found at path: " + imagePath);
            return null;
        }
    }

    private void setupListeners() {
        startButton.addActionListener(this);
        decksButton.addActionListener(this);
        exit.addActionListener(this);

        // Update bot profile on button click
        previousBotButton.addActionListener(e -> updateBotProfile("assets/Profile/Klong ha.webp"));
        nextBotButton.addActionListener(e -> updateBotProfile("assets/Profile/Karn Bob.webp"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            // Stop background music and play start sound effect
            BGMPlayer.stopBackgroundMusic();
            SFXPlayer.playSound("Game/src/assets/Audio/SFX/Game_Start.wav");

            // Set up player profile
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

            // Set up bot player
            Player bot = new Bot();
            ArrayList<Player> players = new ArrayList<>();
            players.add(player);
            players.add(bot);

            // Start game
            GameForGUI botGame = new GameForGUI(players);
            Router.setRoute("Avenger", botGame);
        } else if (e.getSource() == decksButton) {
            // Play button click sound
            SFXPlayer.playSound("Game/src/assets/Audio/SFX/Button_Click.wav");
        }
    }
}




/*
package GUI.Page;

import AudioPlayer.BGMPlayer;
import AudioPlayer.SFXPlayer;
import GUI.Component.ExitButton;
import GUI.Component.BotLeftButton;
import GUI.Component.BotRightButton;
import GUI.Router;
import GUI.Setting.UserPreference;
import Gameplay.Bot;
import Gameplay.Deck;
import Gameplay.GameForGUI;
import Gameplay.Player;
import utils.SharedResource;
import java.net.URL;

import javax.swing.*;
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

    public SelectBotRoom() {
        initComponents();
        setupLayout();
        setupListeners();
    }

    private void initComponents() {
        // โหลดรูปภาพบอทเริ่มต้น
        //String defaultImagePath = "assets/Profile/Karn Bob.webp";
        botImage = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("assets/Profile/Karn Bob.webp")));

        // สร้าง JLabel เพื่อแสดงภาพ
        selectingBotProfileImage = new JLabel(botImage);

        // ตั้งค่า UI อื่นๆ
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

    private ImageIcon loadImage(String imagePath) {
        // แสดงข้อความเพื่อดีบัก
        System.out.println("Trying to load image from path: " + imagePath);

        // โหลดไฟล์ภาพจาก classpath
        URL imageURL = getClass().getClassLoader().getResource(imagePath);

        // ตรวจสอบว่าไฟล์ภาพถูกโหลดได้หรือไม่
        if (imageURL == null) {
            System.err.println("Image not found at path: " + imagePath);
            return null;
        } else {
            System.out.println("Image loaded successfully from: " + imagePath);
        }

        // คืนค่า ImageIcon
        return new ImageIcon(imageURL);
    }

    // 📌 ฟังก์ชันสำหรับอัปเดตรูปภาพบอท
    private void updateBotProfile(String imagePath) {
        System.out.println("Loading new bot image from path: " + imagePath);
        ImageIcon loadedImage = loadImage(imagePath);

        if (loadedImage != null) {
            Image scaledImage = loadedImage.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            selectingBotProfileImage.setIcon(new ImageIcon(scaledImage));
            System.out.println(selectingBotProfileImage.getName());
        } else {
            System.err.println("Failed to update bot profile - Image not found");
        }

        // หลังจากโหลดเสร็จให้ลบข้อความ
    }

    // 📌 เชื่อมปุ่มเปลี่ยนบอทกับ updateBotProfile()
    private void setupListeners() {
        startButton.addActionListener(this);
        decksButton.addActionListener(this);
        exit.addActionListener(this);

        previousBotButton.addActionListener(e -> updateBotProfile("assets/Profile/Klong ha.webp"));
        nextBotButton.addActionListener(e -> updateBotProfile("assets/Profile/Pleng's cat.webp"));
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

 */