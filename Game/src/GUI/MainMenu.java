package GUI;

import javax.swing.*;
import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.border.EmptyBorder;

public class MainMenu {
    private JPanel MainPanel;
    private JPanel ButtonZone;
    private JPanel TitlePanel;
    private JButton playButton = new JButton("Play");
    private JButton yourDecksButton = new JButton("Your Decks");
    private JButton tutorialButton = new JButton("Tutorial");
    private JButton settingsButton = new JButton("Settings");
    private JButton creditButton = new JButton("Credit");
    private JButton exitButton = new JButton("Exit");
    private JLabel Title;
    private Image bg;

    public MainMenu() {
        // Ensure the background image exists
        try {
            bg = new ImageIcon(getClass().getResource("Bg.jpg")).getImage();
        } catch (Exception e) {
            System.out.println("Error loading background image: " + e.getMessage());
            // You can use a default image or just a solid color as a fallback
            //bg = new Color(0, 0, 0); // Solid black as fallback
        }
        initComponents();
    }

    private void initComponents() {
        // MainPanel: Use GridBagLayout instead of GridLayout for flexible positioning
        MainPanel = new JPanel(new GridLayout(3,1)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g); // Ensures the panel is painted first
                if (bg != null) {
                    g.drawImage(bg, 0, 0, getWidth(), getHeight(), this); // Draw the background image
                }
            }
        };

        TitlePanel = new JPanel(new BorderLayout());
        TitlePanel.setBackground(new Color(255, 255, 255, 0));
        TitlePanel.add(Title = new JLabel("Math Card Game"));
        Title.setForeground(Color.WHITE);
        Title.setFont(new Font("Arial", Font.BOLD, 72));
        Title.setBorder(new EmptyBorder(50,50,0,0));

        ButtonZone = new JPanel();
        ButtonZone.setLayout(new BoxLayout(ButtonZone, BoxLayout.Y_AXIS));
        ButtonZone.setBackground(new Color(255, 255, 255, 0));
        ButtonZone.setBorder(new EmptyBorder(50,50,0,0));
        ButtonZone.setOpaque(false);
        ButtonZone.add(playButton);
        setButton(playButton);
        ButtonZone.add(yourDecksButton);
        setButton(yourDecksButton);
        ButtonZone.add(tutorialButton);
        setButton(tutorialButton);
        ButtonZone.add(settingsButton);
        setButton(settingsButton);
        ButtonZone.add(creditButton);
        setButton(creditButton);
        ButtonZone.add(exitButton);
        setButton(exitButton);

        MainPanel.add(TitlePanel, BorderLayout.NORTH);
        MainPanel.add(ButtonZone, BorderLayout.CENTER);
    }

    public JPanel getMainPanel() {
        return MainPanel;
    }

    // Set button properties for transparency
    public void setButton(JButton button) {
        button.setOpaque(false);
        button.setBackground(new Color(255, 255, 255, 0));
        button.setBorder(new EmptyBorder(5,50,0,0));
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 24));
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(300, 100));

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Math Card Game");
            MainMenu mainMenu = new MainMenu();

            // Ensure the icon exists
            try {
                ImageIcon icon = new ImageIcon(MainMenu.class.getResource("icon.png"));
                frame.setIconImage(icon.getImage());
            } catch (Exception e) {
                System.out.println("Error loading icon: " + e.getMessage());
            }

            frame.setContentPane(mainMenu.getMainPanel());
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1920, 1080);
            frame.setVisible(true);
        });
    }
}
