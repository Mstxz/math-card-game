package GUI;

import javax.swing.*;
import java.awt.*;

public class newGui {
    /* Main Frame */
    private JFrame frame = new JFrame("Math Card Game");

    /* Zones */
    private JPanel playerZone = new JPanel();
    private JPanel gameZone = new JPanel();
    private JPanel enemyZone = new JPanel();

    /* Status Elements */
    private JLabel hpLabel;
    private JLabel manaLabel;
    private JProgressBar hpBar;
    private JProgressBar manaBar;

    private int hp = 100;
    private int mana = 100;

    private int _hp = 150; // for test only
    private int _mana = 150;

    public newGui() {
        frame.setLayout(new BorderLayout());

        // Load Background Image
        JLabel background = new JLabel(new ImageIcon("background.jpg"));
        background.setLayout(new BorderLayout());
        frame.setContentPane(background); // Set as background

        // Main Panel to hold everything
        JPanel mainPanel = new JPanel(new GridLayout(3, 1));
        mainPanel.setOpaque(false); // Make it transparent

        // Enemy Zone (Top)
//        enemyZone.setLayout(new GridLayout(1, 3));
//        createProfilePanel(enemyZone, "assets/ProfileCat1.jpg", "SoKen");
//        enemyZone.add(new JPanel()); // Spacer
//        createStatusPanel(enemyZone);

        enemyZone.setLayout(new FlowLayout(FlowLayout.LEFT));
        createEnemyProfilePanel(enemyZone, "assets/ProfileCat1.jpg", "SoKen", hp);

        // Game Zone (Middle)
        gameZone.setOpaque(false);

        // Player Zone (Bottom)
        playerZone.setLayout(new GridLayout(1, 3));
        createStatusPanel(playerZone);
        playerZone.add(new JPanel()); // Spacer
        createProfilePanel(playerZone, "assets/ProfileCat2.png", "TheHerta");

        // Add everything to main panel
        mainPanel.add(enemyZone);
        mainPanel.add(gameZone);
        mainPanel.add(playerZone);

        // Add panel to frame
        frame.add(mainPanel, BorderLayout.CENTER);

        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void createStatusPanel(JPanel zone) {
        JPanel statusContainer = new JPanel(new GridLayout(2, 1, 5, 5));
        statusContainer.setOpaque(false);

        // Labels
        hpLabel = new JLabel("HP: " + hp);
        manaLabel = new JLabel("Mana: " + mana);

        hpLabel.setFont(new Font("Arial", Font.BOLD, 24));
        manaLabel.setFont(new Font("Arial", Font.BOLD, 24));

        // Add components
        statusContainer.add(hpLabel);

        statusContainer.add(manaLabel);


        zone.add(statusContainer);
    }

//    private void createStatusPanel(JPanel zone) {
//        JPanel statusContainer = new JPanel();
//        statusContainer.setOpaque(false);
//        // ใช้ BoxLayout ในแนวแกน Y
//        statusContainer.setLayout(new BoxLayout(statusContainer, BoxLayout.Y_AXIS));
//        // กำหนด padding โดยใช้ EmptyBorder
//        statusContainer.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));
//
//        hpLabel = new JLabel("HP: " + hp);
//        manaLabel = new JLabel("Mana: " + mana);
//
//        hpLabel.setFont(new Font("Arial", Font.BOLD, 24));
//        manaLabel.setFont(new Font("Arial", Font.BOLD, 24));
//
//        // กำหนด alignment ให้ชิดซ้ายภายใน BoxLayout
//        hpLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
//        manaLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
//
//        statusContainer.add(hpLabel);
//        // เว้นช่องว่างระหว่าง HP กับ Mana
//        statusContainer.add(Box.createRigidArea(new Dimension(0, 10)));
//        statusContainer.add(manaLabel);
//
//        zone.add(statusContainer);
//    }

    private void createProfilePanel(JPanel zone, String path, String name) {
        JPanel profileContainer = new JPanel(new BorderLayout());
        profileContainer.setOpaque(false);

        // โหลดรูปภาพโปรไฟล์
        ImageIcon profileIcon = new ImageIcon(path);
        Image scaledImage = profileIcon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        JLabel profileImage = new JLabel(new ImageIcon(scaledImage));

        // สร้าง JLabel สำหรับชื่อ และวางไว้ด้านบน (NORTH)
        JLabel profileName = new JLabel(name, SwingConstants.CENTER);
        profileName.setFont(new Font("Arial", Font.BOLD, 32));
        profileContainer.add(profileName, BorderLayout.NORTH);

        // วางรูปภาพไว้ตรงกลาง
        profileContainer.add(profileImage, BorderLayout.CENTER);

        zone.add(profileContainer);
    }

    private void createEnemyProfilePanel(JPanel zone, String path, String name, int hp) {
        JPanel enemyProfilePanel = new JPanel(new BorderLayout());
        enemyProfilePanel.setOpaque(false);

        // ชื่ออยู่ด้านบน (NORTH)
        JLabel nameLabel = new JLabel(name, SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 32));
        enemyProfilePanel.add(nameLabel, BorderLayout.NORTH);

        // รูปภาพตรงกลาง (CENTER)
        ImageIcon profileIcon = new ImageIcon(path);
        Image scaledImage = profileIcon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        JLabel profileImage = new JLabel(new ImageIcon(scaledImage));
        enemyProfilePanel.add(profileImage, BorderLayout.CENTER);

        // HP อยู่ด้านล่าง (SOUTH) โดยจัดให้อยู่ชิดซ้าย
        JLabel hpLabel = new JLabel("HP: " + hp);
        hpLabel.setFont(new Font("Arial", Font.BOLD, 24));
        JPanel hpPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        hpPanel.setOpaque(false);
        hpPanel.add(hpLabel);
        enemyProfilePanel.add(hpPanel, BorderLayout.SOUTH);

        // ตั้ง border ให้เว้นระยะด้านซ้าย 50px
        enemyProfilePanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));

        zone.add(enemyProfilePanel);
    }


    public static void main(String[] args) {
        new newGui();
    }
}
