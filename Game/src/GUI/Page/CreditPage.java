package GUI.Page;

import AudioPlayer.BGMPlayer;
import GUI.Component.CreditProfile;
import GUI.Component.ExitButton;
import GUI.Router;
import utils.SharedResource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CreditPage extends Page implements KeyListener {
    private JPanel topPanel, creditPanel;
    private JLabel creditLabel;
    private ExitButton exitButton;

    public CreditPage() {
        BGMPlayer.stopBackgroundMusic();
        exitButton = new ExitButton("MainMenu"){
            @Override
            public void cleanUp() {
                mainFrame.removeKeyListener(CreditPage.this);
            }
        };
        mainFrame.addKeyListener(this);
        mainPanel.setBackground(SharedResource.SIAMESE_BRIGHT);

        creditLabel = new JLabel("Credits");
        creditLabel.setFont(SharedResource.getCustomSizeFont(96));
        creditLabel.setHorizontalAlignment(SwingConstants.CENTER);

        topPanel = new JPanel(new BorderLayout());
        topPanel.setPreferredSize(new Dimension(Router.getMainFrame().getWidth(), 100));
        topPanel.setBackground(SharedResource.SIAMESE_BRIGHT);
        topPanel.add(exitButton, BorderLayout.WEST);
        topPanel.add(creditLabel, BorderLayout.CENTER);

        creditPanel = new JPanel(new GridLayout(2, 5, 10,10));
        creditPanel.setPreferredSize(new Dimension(Router.getMainFrame().getWidth() - 300, Router.getMainFrame().getHeight() - 200));
        creditPanel.setBackground(SharedResource.SIAMESE_BRIGHT);

        creditPanel.add(new CreditProfile("Mstxz.EXE","Mystyr.webp", "Sound Producer , UX/UI Designer", "I’m just making music with my mouse and keyboard, and wasting some bucks."));
        creditPanel.add(new CreditProfile("Cyan Kagami","Pupr.webp", "\"Quote Here\" huh? not here?", "FrontEnd, Backend, GameDesign, Artist, Game Tester, Lore Writer, Klong Owner, Mascot"));
        creditPanel.add(new CreditProfile("Arktik","Arsr.webp", "Back-End", "Left is Cute"));
        creditPanel.add(new CreditProfile("Gun","Angy.webp", "Game Designer", "Actually, I like dogs."));
        creditPanel.add(new CreditProfile("Pooh","Who.webp", "Back-End", "Don't lie on the resume."));
        creditPanel.add(new CreditProfile("Few","More.webp", "Game design,Slave owner", "No pain no gain, go rich or go home"));
        creditPanel.add(new CreditProfile("TeeIT","Toom.webp", "Front-end, UI/UX", "I love to catch bugs in my computer."));
        creditPanel.add(new CreditProfile("Ja","Chen.webp", "Front-End", "Never gonna give you up"));
        creditPanel.add(new CreditProfile("Nice","LifeCoach.webp", "Front-End", "Everything you can imagine is REAL!"));
        creditPanel.add(new CreditProfile("Klong Eng Ha","Klong Ha.webp", "Actor", "Klong eng Ha, Klong isn't a member of this group Ha."));

        mainPanel.add(topPanel);
        mainPanel.add(creditPanel);
        BGMPlayer.playBackgroundMusic("Game/src/assets/Audio/BGM/PurrfectEquations_OST.wav", true);

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code;

        if (!this.getMainPanel().isFocusable())
            return;
        code = e.getKeyCode();
        if (code == KeyEvent.VK_ESCAPE){
            this.getMainFrame().removeKeyListener(this);
            Router.setRoute("MainMenu",null);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
