package GUI.Page;

import GUI.Component.ButtonPanelComponent;
import GUI.Component.PlayerPanelComponent;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import utils.SharedResource;

import utils.ResourceLoader;


public class PlayerVsPlayer extends Page {
    private JLabel title,head;
    //private Image bg;

    public PlayerVsPlayer() {
        mainPanel.setLayout(new BorderLayout(20, 0));
        mainPanel.setBackground(new Color(221,218,210));
        mainPanel.setBorder(new EmptyBorder(20, 40, 80, 40));
        // Title Component
        title = new JLabel("Player Vs Player");
        title.setFont(SharedResource.getCustomSizeFont(80));
        title.setForeground(new Color(72, 62, 56));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setPreferredSize(new Dimension(0, 100));
        mainPanel.add(title, BorderLayout.NORTH);

        head = new JLabel("Matchmaking...");
        head.setFont(SharedResource.getCustomSizeFont(40));
        head.setHorizontalAlignment(SwingConstants.CENTER);

        // Add ButtonPanelComponent
        ButtonPanelComponent buttonPanelComponent = new ButtonPanelComponent();
        JPanel panel = new JPanel(new BorderLayout(20, 0));
        panel.add(head,BorderLayout.NORTH);
        panel.setBackground(SharedResource.SIAMESE_LIGHT);
        buttonPanelComponent.setOpaque(false);

        panel.add(buttonPanelComponent, BorderLayout.SOUTH);
        
        // Create a JPanel to hold the PlayerPanelComponent and center it
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
        centerPanel.setOpaque(false);
        //centerPanel.setBackground(SharedResource.SIAMESE_BASE);
        centerPanel.add(Box.createVerticalGlue());
        centerPanel.add(new PlayerPanelComponent());
        centerPanel.add(Box.createVerticalGlue());
        panel.add(centerPanel, BorderLayout.CENTER);

        mainPanel.add(panel, BorderLayout.CENTER);

    }


    public static void main(String[] args) {
        new PlayerVsPlayer();
    }
}
