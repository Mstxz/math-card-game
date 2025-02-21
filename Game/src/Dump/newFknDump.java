package Dump;

import javax.swing.*;
import java.awt.*;

public class newFknDump {
    public static void main(String[] args) {
        // Create Frame
        JFrame frame = new JFrame("Game HUD");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);
        frame.setLayout(new BorderLayout());

        // Create a Panel for Stats
        JPanel statsPanel = new JPanel(new GridLayout(2, 2, 50, 50));

        // HP and Mana Labels
        JLabel hpLabel = new JLabel("HP: 100");
        JLabel manaLabel = new JLabel("Mana: 100");

        // HP and Mana Bars
        JProgressBar hpBar = new JProgressBar(0, 100);
        hpBar.setValue(100);
        hpBar.setForeground(Color.RED);

        JProgressBar manaBar = new JProgressBar(0, 100);
        manaBar.setValue(100);
        manaBar.setForeground(Color.BLUE);

        // Add to Panel
        statsPanel.add(hpLabel);
        statsPanel.add(hpBar);
        statsPanel.add(manaLabel);
        statsPanel.add(manaBar);

        // Profile Section
        JButton profileButton = new JButton("Profile");
        JPanel profilePanel = new JPanel();
        profilePanel.add(profileButton);

        // Add Components to Frame
        frame.add(statsPanel, BorderLayout.CENTER);
        frame.add(profilePanel, BorderLayout.EAST);

        // Show Frame
        frame.setVisible(true);
    }
}
