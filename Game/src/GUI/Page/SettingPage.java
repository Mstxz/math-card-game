package GUI.Page;

import GUI.Component.RotatingSettingOption;
import GUI.Setting.Component.TabbedPanel;
import GUI.Setting.Controller.SettingController;
import GUI.Setting.UserPreference;
import utils.SharedResource;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingPage extends Page implements ActionListener {
    private JLabel resolutionText;
    private JButton applyButton;
    private JPanel settingTabPanel;
    private JLabel settingTitle;

    private RotatingSettingOption resolutionSettingButton;

    public SettingPage(){
        settingTitle = new JLabel("Setting");
        settingTitle.setFont(SharedResource.getCustomSizeFont(48));
        settingTitle.setHorizontalAlignment(SwingConstants.CENTER);
        settingTabPanel = new JPanel();
        settingTabPanel.setBorder(new EmptyBorder(0,40,0,80));
        settingTabPanel.add(new TabbedPanel());
        //createResolutionSettingButton();

        //mainPanel.add(resolutionText);
        //mainPanel.add(resolutionSettingButton);
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(settingTitle,BorderLayout.NORTH);
        mainPanel.add(settingTabPanel);
        mainPanel.setBorder(new EmptyBorder(20,20,80,40));
        //applyButton.addActionListener(this);
        setupMainPanel();
    }

    public void createResolutionSettingButton(){
        resolutionSettingButton = new RotatingSettingOption(SettingController.resolutionList,UserPreference.getInstance().getResolutionIndex());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(applyButton)){
            UserPreference.getInstance().setResolutionIndex(resolutionSettingButton.getCurrentIndex());

            SettingController.updateResolution(resolutionSettingButton.getCurrentIndex());
            SettingController.updatePreference();
        }
    }
}
