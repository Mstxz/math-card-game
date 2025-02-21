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
        enemyZone.setLayout(new GridLayout(1, 3));
        createProfilePanel(enemyZone, "assets/ProfileCat1.jpg", "SoKen");
        enemyZone.add(new JPanel()); // Spacer
        createStatusPanel(enemyZone);

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
        JPanel statusContainer = new JPanel(new GridLayout(2, 2, 5, 5));
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

    private void createProfilePanel(JPanel zone, String path, String name) {
        JPanel profileContainer = new JPanel(new BorderLayout());
        profileContainer.setOpaque(false);

        // Load Profile Image
        ImageIcon profileIcon = new ImageIcon(path);
        Image scaledImage = profileIcon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        JLabel profileImage = new JLabel(new ImageIcon(scaledImage));

        // Add to panel
        profileContainer.add(profileImage, BorderLayout.CENTER);
        JLabel profileName = new JLabel(name, SwingConstants.CENTER);
        profileName.setFont(new Font("Arial", Font.BOLD, 32));
        profileContainer.add(profileName, BorderLayout.SOUTH);

        zone.add(profileContainer);
    }

    public static void main(String[] args) {
        new newGui();
    }
}
