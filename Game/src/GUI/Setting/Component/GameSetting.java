package GUI.Setting.Component;

import GUI.Component.RotatingSettingOption;
import GUI.Setting.SettingController;
import GUI.Setting.UserPreference;
import utils.RoundPanelUI;
import utils.SharedResource;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameSetting extends JPanel implements ActionListener {
    private JPanel panel1;
    private JPanel panel2;
    private RotatingSettingOption resolution;
    private VolumeSlider sfxVolume;
    private VolumeSlider musicVolume;
    private JButton revertButton;
    private JButton applyButton;


    public GameSetting(){
        this.setLayout(new BorderLayout());
        resolution = new RotatingSettingOption(SettingController.resolutionList, UserPreference.getInstance().getResolutionIndex());
        panel1 = new JPanel();
        panel1.setLayout(new GridLayout(3,3));
        panel1.add(new JLabel("BGM Volume"));
        musicVolume = new VolumeSlider();
        musicVolume.setValue(UserPreference.getInstance().getMusicVolume());
        panel1.add(musicVolume);
        panel1.add(new JLabel("SFX Volume"));
        sfxVolume = new VolumeSlider();
        sfxVolume.setValue(UserPreference.getInstance().getSFXVolume());
        panel1.add(sfxVolume);
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
        panel2.setBorder(new EmptyBorder(0,80,0,80));
        panel2.setBackground(SharedResource.SIAMESE_LIGHT);
        applyButton.addActionListener(this);
        this.add(panel1,BorderLayout.NORTH);
        this.add(panel2,BorderLayout.SOUTH);
        this.setBorder(new EmptyBorder( 0,30,30,30));
        this.setUI(new RoundPanelUI(SharedResource.SIAMESE_LIGHT,30,30,false,true,true,true));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(applyButton)){
            UserPreference.getInstance().setResolutionIndex(resolution.getCurrentIndex());
            UserPreference.getInstance().setSFXVolume(sfxVolume.getValue());
            UserPreference.getInstance().setMusicVolume(musicVolume.getValue());
            SettingController.update();
            SettingController.updatePreference();
        }
    }
}
