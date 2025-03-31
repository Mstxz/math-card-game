package GUI.Page;

import GUI.Component.CreditProfile;
import GUI.Component.ExitButton;
import GUI.Router;
import utils.SharedResource;

import javax.swing.*;
import java.awt.*;

public class CreditPage extends Page {
    private JPanel topPanel, creditPanel;
    private JLabel creditLabel;
    private ExitButton exitButton;

    public CreditPage() {
        exitButton = new ExitButton("MainMenu");
        mainPanel.setBackground(SharedResource.SIAMESE_BRIGHT);

        creditLabel = new JLabel("Credits");
        creditLabel.setFont(SharedResource.getCustomSizeFont(96));
        creditLabel.setHorizontalAlignment(SwingConstants.CENTER);

        topPanel = new JPanel(new BorderLayout());
        topPanel.setPreferredSize(new Dimension(Router.getMainFrame().getWidth(), 100));
        topPanel.setBackground(SharedResource.TRANSPARENT);
        topPanel.add(exitButton, BorderLayout.WEST);
        topPanel.add(creditLabel, BorderLayout.CENTER);

        creditPanel = new JPanel(new GridLayout(2, 5, 10,10));
        creditPanel.setPreferredSize(new Dimension(Router.getMainFrame().getWidth() - 300, Router.getMainFrame().getHeight() - 200));
        creditPanel.setBackground(SharedResource.SIAMESE_BRIGHT);

        creditPanel.add(new CreditProfile("Mstxz.EXE","Mystyr.webp", "Sound Producer , UX/UI Designer", "I’m just making music with my mouse and keyboard, and wasting some bucks."));
        creditPanel.add(new CreditProfile("Cyan Kagami","Pupr.webp", "Duck of the Project", "{Quote Here}"));
        creditPanel.add(new CreditProfile("Arktik","Arsr.webp", "{Position Here}", "{Quote Here}"));
        creditPanel.add(new CreditProfile("D37un","Angy.webp", "{Position Here}", "{Quote Here}"));
        creditPanel.add(new CreditProfile("Pooh","Who.webp", "Back-End", "Don't lie on the resume."));
        creditPanel.add(new CreditProfile("Few","More.webp", "Game design,Slave owner", "No pain no gain, go rich or go home"));
        creditPanel.add(new CreditProfile("TeeIT","Mystyr.webp", "Front-end, UI/UX", "I love to catch bugs in my computer."));
        creditPanel.add(new CreditProfile("Ja","Mystyr.webp", "Front-End", "Never gonna give you up"));
        creditPanel.add(new CreditProfile("Nice","Mystyr.webp", "Front-End", "Everything you can imagine is REAL!"));
        creditPanel.add(new CreditProfile("Klong Eng Ha","Klong ha.webp", "Actor", "Klong eng Ha, Klong isn't a member of this group Ha."));

        mainPanel.add(topPanel);
        mainPanel.add(creditPanel);

    }

}
