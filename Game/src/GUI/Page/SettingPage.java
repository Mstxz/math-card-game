package GUI.Page;

import GUI.Component.RotatingSettingOption;
import GUI.Setting.SettingController;
import GUI.Setting.UserPreference;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SettingPage extends Page implements ActionListener {
    private JLabel resolutionText;
    private JButton applyButton;

    private RotatingSettingOption resolutionSettingButton;

    public SettingPage(){
        resolutionText = new JLabel("Choose Resolution");
        applyButton = new JButton("Apply");
        createResolutionSettingButton();

        mainPanel.add(resolutionText);
        mainPanel.add(resolutionSettingButton);
        mainPanel.add(applyButton);

        applyButton.addActionListener(this);
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
