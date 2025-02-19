package GUI;

import javax.swing.*;

public class MainMenu {
    private JPanel MainPanel;
    private JButton playButton;
    private JButton yourDecksButton;
    private JButton tutorialButton;
    private JButton settingsButton;
    private JButton creditButton;
    private JButton exitButton;
    private JLabel Title;
    private JPanel BlankPanel;
    private JPanel TitlePanel;
    private JPanel ButtonZone;

    public MainMenu() {

    }

    public JPanel getMainPanel() {
        return MainPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Math Card Game");
            MainMenu mainMenu = new MainMenu();

            // Set the app icon (image in the same package)
            ImageIcon icon = new ImageIcon(MainMenu.class.getResource("icon.png")); //icon.png should be in this package only
            frame.setIconImage(icon.getImage());

            frame.setContentPane(mainMenu.getMainPanel());
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        });
    }

}
