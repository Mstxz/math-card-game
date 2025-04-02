package GUI.Setting.Component;

import AudioPlayer.SFXPlayer;
import GUI.Component.RotatingSettingOption;
import GUI.Component.RoundBorder;
import GUI.Router;
import GUI.Setting.Controller.SettingController;
import GUI.Setting.UserPreference;
import utils.UIManager.ButtonUI;
import utils.UIManager.RoundPanelUI;
import utils.SharedResource;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        System.out.println(resolution.getCurrentIndex());
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
        panel1.setBorder(new EmptyBorder(70,60,20,60));
        panel1.setBackground(SharedResource.SIAMESE_LIGHT);

        panel2 = new JPanel();

        revertButton = new JButton("Revert to default");
        revertButton.setUI(new ButtonUI());
        revertButton.setPreferredSize(new Dimension(280, 80));


        applyButton = new JButton("Apply");
        applyButton.setUI(new ButtonUI());
        applyButton.setPreferredSize(new Dimension(250, 80));

        panel2.setLayout(new FlowLayout(FlowLayout.RIGHT,10,0));
        panel2.add(revertButton);
        panel2.add(applyButton);
        panel2.setBorder(new EmptyBorder(0,60,0,60));
        panel2.setBackground(SharedResource.SIAMESE_LIGHT);
        revertButton.addActionListener(this);
        applyButton.addActionListener(this);
        this.add(panel1,BorderLayout.NORTH);
        this.add(panel2,BorderLayout.SOUTH);
        this.setBorder(new EmptyBorder( 0,20,30,20));
        this.setUI(new RoundPanelUI(SharedResource.SIAMESE_LIGHT,30,30,false,true,true,true));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(applyButton)) {
            System.out.println(resolution.getCurrentIndex());
            if (UserPreference.getInstance().getResolutionIndex() != resolution.getCurrentIndex()){
                Router.getMainFrame().setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                UserPreference.getInstance().setResolutionIndex(resolution.getCurrentIndex());
                SettingController.updateResolution(resolution.getCurrentIndex());
                Router.getMainFrame().setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            }
            UserPreference.getInstance().setSFXVolume(sfxVolume.getValue());
            UserPreference.getInstance().setMusicVolume(musicVolume.getValue());
            SettingController.updateSoundVolume();
            SettingController.updatePreference();
            SFXPlayer.playSound("Game/src/assets/Audio/SFX/Deck_Confirm.wav");
        }
        else if(e.getSource().equals(revertButton)){
            resolution.setCurrentIndex(0);
            sfxVolume.setValue(50);
            musicVolume.setValue(50);

        }
    }
}
