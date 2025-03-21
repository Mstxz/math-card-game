package GUI.Setting.Component;

import utils.SharedResource;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class GameSetting extends JPanel {
    private JPanel panel1;
    private JPanel panel2;

    private JButton revertButton;
    private JButton applyButton;

    public GameSetting(){
        this.setBackground(SharedResource.SIAMESE_LIGHT);
        this.setLayout(new BorderLayout());

        panel1 = new JPanel();
        panel1.setLayout(new GridLayout(3,3));
        panel1.add(new JLabel("BGM Volume"));
        panel1.add(new VolumeSlider());
        panel1.add(new JLabel("SFX Volume"));
        panel1.add(new VolumeSlider());
        panel1.add(new JLabel("Screen Size"));
        panel1.add(new VolumeSlider()); // Fix it to button <>
        panel1.setBorder(new EmptyBorder(70,80,20,20));
        panel1.setBackground(SharedResource.SIAMESE_LIGHT);

        panel2 = new JPanel();
        revertButton = new JButton("Revert to default");
        applyButton = new JButton("Apply");
        panel2.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panel2.add(revertButton);
        panel2.add(applyButton);
        panel2.setBorder(new EmptyBorder(0,0,80,20));
        panel2.setBackground(SharedResource.SIAMESE_LIGHT);


        this.add(panel1,BorderLayout.NORTH);
        this.add(panel2,BorderLayout.SOUTH);
    }
}
