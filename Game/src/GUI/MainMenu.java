package GUI;

import javax.swing.*;

public class MainMenu {
    private final JFrame frame = new JFrame("Math Card Game");

    public MainMenu() {
        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
