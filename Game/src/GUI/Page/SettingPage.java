package GUI.Page;

import GUI.Component.RotatingSettingOption;
import GUI.SettingController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SettingPage extends Page implements ActionListener {
    private JLabel resolutionText;
    private JButton applyButton;

    private RotatingSettingOption resolutionSettingButton;

    public SettingPage(){
        resolutionText = new JLabel("Choose Resolution");
        resolutionSettingButton = new RotatingSettingOption(new ArrayList<String>(List.of("1920x1080","1366x768")));
        applyButton = new JButton("Apply");

        mainPanel.add(resolutionText);
        mainPanel.add(resolutionSettingButton);
        mainPanel.add(applyButton);

        applyButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(applyButton)){
            SettingController.updateResolution(resolutionSettingButton.getText());
        }
    }
}
