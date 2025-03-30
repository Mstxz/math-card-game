package GUI.Page;

import AudioPlayer.SFXPlayer;
import GUI.Component.ExitButton;
import GUI.Component.TutorialSlideShow;
import GUI.Router;
import utils.SharedResource;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TutorialPage extends Page {
    private JPanel topPanel;
    private final ExitButton exitButton = new ExitButton("MainMenu");
    public TutorialPage() {
        mainPanel.setBackground(SharedResource.SIAMESE_BRIGHT);
        mainPanel.setLayout(new BorderLayout());

        topPanel = new JPanel();
        topPanel.setBackground(SharedResource.SIAMESE_BRIGHT);

        mainPanel.add(topPanel,BorderLayout.NORTH);
        topPanel.add(exitButton, BorderLayout.WEST);
        mainPanel.add(new TutorialSlideShow(), BorderLayout.CENTER);
    }
}
