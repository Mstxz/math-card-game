package GUI.Page;

import Audio.SFXPlayer;
import GUI.Router;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import utils.ResourceLoader;
import utils.SharedResource;

public class SelGameMode extends Page implements ActionListener {
    private JPanel ButtonZone;
    private JPanel TitlePanel;
    private JLabel Title;
    private JButton playerButton = new JButton("Player vs Player");
    private JButton botButton = new JButton("Player vs Bot");
    private JButton exitButton = new JButton("exit");
    private JButton backButton = new JButton("Back");
    private Image bg;

    public SelGameMode() {
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
            //mainFrame.setIconImage(icon.getImage());
        } catch (Exception e) {
            System.out.println("Error loading icon: " + e.getMessage());
        }

    }

    private void initComponents() {
        // MainPanel: Use GridBagLayout instead of GridLayout for flexible positioning
        mainPanel = new JPanel(new BorderLayout()) {
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
        TitlePanel.add(Title = new JLabel("Meowthematicians"));
        Title.setForeground(SharedResource.SIAMESE_BASE);
        Title.setFont(SharedResource.getCustomSizeFont(100));
        Title.setBorder(new EmptyBorder(150,150,0,0));

        ButtonZone = new JPanel();
        ButtonZone.setLayout(new BoxLayout(ButtonZone, BoxLayout.Y_AXIS));
        ButtonZone.setPreferredSize(new Dimension(400, 800));
        ButtonZone.setBackground(new Color(255, 255, 255, 0));
        ButtonZone.setBorder(new EmptyBorder(50,100,0,50));
        ButtonZone.setOpaque(false);

        ButtonZone.add(playerButton);
        setButton(playerButton);
        ButtonZone.add(botButton);
        setButton(botButton);
        ButtonZone.add(backButton);
        setButton(backButton);
//        ButtonZone.add(exitButton);
//        setButton(exitButton);

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
        button.setForeground(SharedResource.SIAMESE_DARK);
        button.setIcon(ResourceLoader.loadPicture("assets/catpaw_icon.png", 30, 30));
        button.setHorizontalTextPosition(SwingConstants.RIGHT);
        button.setIconTextGap(10);
        Dimension defaultSize = new Dimension(300, 50);
        Font defaultFont = SharedResource.getCustomSizeFont(36);

        //button.setPreferredSize(defaultSize);
        button.setFont(defaultFont);

        // Increase Play button size and font

        button.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SFXPlayer.playSound("Game/src/assets/Audio/Test2.wav", -10.0f);

        if (e.getSource().equals(botButton)){
            Router.setRoute("Avenger",null);
        }
       else if (e.getSource().equals(playerButton)){
            Router.setRoute("Player",null);
        }
        else if (e.getSource().equals(backButton)){
            Router.setRoute("MainMenu",null);
        }
    }
}
