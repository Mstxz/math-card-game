package GUI.Setting.Component;

import GUI.Component.RotatingSettingOption;
import GUI.Setting.SettingController;
import GUI.Setting.UserPreference;
import utils.RoundPanelUI;
import utils.SharedResource;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class GameSetting extends JPanel {
    private JPanel panel1;
    private JPanel panel2;
    private RotatingSettingOption resolution;
    private JButton revertButton;
    private JButton applyButton;


    public GameSetting(){
        this.setLayout(new BorderLayout());
        resolution = new RotatingSettingOption(SettingController.resolutionList, UserPreference.getInstance().getResolutionIndex());
        panel1 = new JPanel();
        panel1.setLayout(new GridLayout(3,3));
        panel1.add(new JLabel("BGM Volume"));
        panel1.add(new VolumeSlider());
        panel1.add(new JLabel("SFX Volume"));
        panel1.add(new VolumeSlider());
        panel1.add(new JLabel("Screen Size"));
        panel1.add(resolution); // Fix it to button <>
        panel1.setBorder(new EmptyBorder(70,80,20,80));
        panel1.setBackground(SharedResource.SIAMESE_LIGHT);

        panel2 = new JPanel();
        revertButton = new JButton("Revert to default");
        applyButton = new JButton("Apply");
        panel2.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panel2.add(revertButton);
        panel2.add(applyButton);
        panel2.setBorder(new EmptyBorder(0,80,80,80));
        panel2.setBackground(SharedResource.SIAMESE_LIGHT);


        this.add(panel1,BorderLayout.NORTH);
        this.add(panel2,BorderLayout.SOUTH);
        this.setBorder(new EmptyBorder( 0,30,30,30));
        this.setUI(new RoundPanelUI(SharedResource.SIAMESE_LIGHT,30,30));
    }
}
