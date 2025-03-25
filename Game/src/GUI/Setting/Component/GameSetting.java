package GUI.Setting.Component;

import GUI.Component.RotatingSettingOption;
import GUI.Component.RoundBorder;
import GUI.Setting.Controller.SettingController;
import GUI.Setting.UserPreference;
import utils.RoundPanelUI;
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
        revertButton.setPreferredSize(new Dimension(250, 76));
        revertButton.setFont(SharedResource.getCustomSizeFont(28));
        revertButton.setForeground(new Color(102, 142, 169));
        revertButton.setContentAreaFilled(false);
        revertButton.setOpaque(false);
        revertButton.setForeground(Color.BLACK);
        revertButton.setBorder(BorderFactory.createCompoundBorder(
                new RoundBorder(new Color(102, 142, 169), new Color(98, 86, 77), 4, 36),  // ขอบนอก (เข้ม)
                BorderFactory.createCompoundBorder(
                        new RoundBorder(new Color(163, 190, 208), new Color(163, 190, 208), 8, 28), // ขอบกลาง (กลาง)
                        new RoundBorder(new Color(216, 220, 223), new Color(216, 220, 223), 2, 12)   // ขอบใน (อ่อน)
                )
        ));
        revertButton.setLayout(null);

        JLabel revertButtonLabel = new JLabel("Revert to default", SwingConstants.CENTER);
        revertButtonLabel.setFont(SharedResource.getCustomSizeFont(28));
        revertButtonLabel.setForeground(new Color(102, 142, 169));
        revertButtonLabel.setBounds(0, 0, revertButton.getPreferredSize().width, revertButton.getPreferredSize().height);
        revertButton.add(revertButtonLabel);

        applyButton = new JButton("Apply");

        applyButton.setPreferredSize(new Dimension(250, 76));
        applyButton.setFont(SharedResource.getCustomSizeFont(28));
        applyButton.setForeground(new Color(102, 142, 169));
        applyButton.setContentAreaFilled(false);
        applyButton.setOpaque(false);
        applyButton.setForeground(Color.BLACK);
        applyButton.setBorder(BorderFactory.createCompoundBorder(
                new RoundBorder(new Color(102, 142, 169), new Color(98, 86, 77), 4, 36),  // ขอบนอก (เข้ม)
                BorderFactory.createCompoundBorder(
                        new RoundBorder(new Color(163, 190, 208), new Color(163, 190, 208), 8, 28), // ขอบกลาง (กลาง)
                        new RoundBorder(new Color(216, 220, 223), new Color(216, 220, 223), 2, 12)   // ขอบใน (อ่อน)
                )
        ));
        applyButton.setLayout(null);

        JLabel applyButtonLabel = new JLabel("Apply", SwingConstants.CENTER);
        applyButtonLabel.setFont(SharedResource.getCustomSizeFont(28));
        applyButtonLabel.setForeground(new Color(102, 142, 169));
        applyButtonLabel.setBounds(0, 0, applyButton.getPreferredSize().width, applyButton.getPreferredSize().height);
        applyButton.add(applyButtonLabel);

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
