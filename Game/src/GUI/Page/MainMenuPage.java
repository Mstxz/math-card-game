package GUI.Page;

import GUI.Router;
import utils.SharedResource;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuPage extends Page implements ActionListener {
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

    public MainMenuPage() {
        super();
        // Ensure the background image exists
        try {
            bg = new ImageIcon(getClass().getClassLoader().getResource("assets/blankBG.jpg")).getImage();
        } catch (Exception e) {
            System.out.println("Error loading background image: " + e.getMessage());
            // You can use a default image or just a solid color as a fallback
            //bg = new Color(0, 0, 0); // Solid black as fallback
        }
        initComponents();
        try {
            ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("assets/icon.png"));
            mainFrame.setIconImage(icon.getImage());
        } catch (Exception e) {
            System.out.println("Error loading icon: " + e.getMessage());
        }

        mainFrame.setResizable(false);
        mainFrame.setSize(1920, 1080);
    }

    private void initComponents() {
        // MainPanel: Use GridBagLayout instead of GridLayout for flexible positioning
        mainPanel = new JPanel(new GridLayout(3,1)) {
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
        Title.setForeground(new Color(100, 90, 82));
        Title.setFont(SharedResource.getCustomSizeFont(70));
        Title.setBorder(new EmptyBorder(50,50,0,0));

        ButtonZone = new JPanel();
        ButtonZone.setLayout(new BoxLayout(ButtonZone, BoxLayout.Y_AXIS));
        ButtonZone.setPreferredSize(new Dimension(400, 800));
        ButtonZone.setBackground(new Color(255, 255, 255, 0));
        ButtonZone.setBorder(new EmptyBorder(50,50,0,50));
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



        mainPanel.add(TitlePanel, BorderLayout.NORTH);
        mainPanel.add(ButtonZone, BorderLayout.CENTER);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    // Set button properties for transparency
    public void setButton(JButton button) {
        button.setOpaque(false);
        button.setBackground(new Color(255, 255, 255, 0));
        button.setBorder(new EmptyBorder(5, 50, 0, 0));
        button.setFocusPainted(false);
        button.setForeground(new Color(100, 90, 62));

        // Default size and font for other buttons
        Dimension defaultSize = new Dimension(300, 150);
        Font defaultFont = SharedResource.getCustomSizeFont(28);

        // Increase Play button size and font
        if (button == playButton) {
            button.setPreferredSize(new Dimension(350, 150)); // Bigger button
            button.setFont(SharedResource.getCustomSizeFont(50)); // Bigger text
        } else {
            button.setPreferredSize(defaultSize);
            button.setFont(defaultFont);
        }

        button.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(playButton)){
            Router.setRoute("SelMode",null);
        }
        else if (e.getSource().equals(exitButton)){
            System.exit(0);
        }
        else if (e.getSource().equals(yourDecksButton)){
            Router.setRoute("DeckCreator",null);
        }
    }
}
