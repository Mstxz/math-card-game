package GUI.Component;

import AudioPlayer.SFXPlayer;
import utils.SharedResource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TutorialSlideShow extends JPanel implements ActionListener {
    private TutorialSlideShowButton prevButton, nextButton;
    private JLabel title;
    private JPanel tutorialShow; //will be the image later

    public TutorialSlideShow() {
        setBackground(SharedResource.SIAMESE_LIGHT);
        /*Prev Button*/
        prevButton = new TutorialSlideShowButton("< Prev");

        /*Next Button*/
        nextButton = new TutorialSlideShowButton("Next >");

        /*Title Label*/
        title = new JLabel("Gameplay");
        setPreferredSize(new Dimension(1670, 880));
        title.setFont(SharedResource.getCustomSizeFont(96));

        add(prevButton);
        add(title);
        add(nextButton);

        prevButton.addActionListener(this);
        nextButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        SFXPlayer.playSound("Game/src/assets/Audio/SFX/Button_Click.wav");
        if (e.getSource() == prevButton) {
            //smth goes here
        }
        else if (e.getSource() == nextButton) {
            //smth goes here
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new TutorialSlideShow());
        frame.setSize(1000, 600);
        frame.setVisible(true);
    }
}
