package GUI;

import java.awt.*;
import javax.swing.*;

public class MainMenu {
    private final JFrame frame = new JFrame("Math Card Game");
    private JPanel buttonZone = new JPanel();
    private JButton startButton = new JButton("Start Game");
    private JButton decksButton = new JButton("Your Decks");
    private JButton tutorialButton = new JButton("Tutorial");
    private JButton optionsButton = new JButton("Settings");
    private JButton exitButton = new JButton("Exit");

    public MainMenu() {
        frame.add(buttonZone, BorderLayout.WEST);
        buttonZone.setLayout(new BoxLayout(buttonZone, BoxLayout.Y_AXIS));
        buttonZone.setBorder(BorderFactory.createEmptyBorder(5, 50, 5, 5));

        Dimension buttonSize = new Dimension(150, 100);
        Font buttonFont = new Font("Arial", Font.PLAIN, 16);
        setupNewButton(startButton);
        setupNewButton(decksButton);
        setupNewButton(tutorialButton);
        setupNewButton(optionsButton);
        setupNewButton(exitButton);

        buttonZone.add(Box.createVerticalGlue());
        buttonZone.add(startButton);
        buttonZone.add(decksButton);
        buttonZone.add(tutorialButton);
        buttonZone.add(optionsButton);
        buttonZone.add(exitButton);
        buttonZone.add(Box.createVerticalGlue());

        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    public void setupNewButton(JButton b){
        b.setMaximumSize(new Dimension(150, 100));
        b.setFont(new Font("Arial", Font.PLAIN, 16));
        b.setHorizontalAlignment(SwingConstants.LEFT);
    }
}
