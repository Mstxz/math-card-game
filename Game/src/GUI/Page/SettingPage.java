package GUI.Page;

import GUI.Component.ExitButton;
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

public class SettingPage extends Page {
    private JPanel settingTabPanel;
    private JLabel settingTitle;


    public SettingPage(){
        ExitButton exitButton = new ExitButton("MainMenu");
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        settingTitle = new JLabel("Setting");
        settingTitle.setFont(SharedResource.getCustomSizeFont(48));
        settingTitle.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(exitButton,BorderLayout.WEST);
        topPanel.add(settingTitle,BorderLayout.CENTER);
        JLabel emptySpace = new JLabel();
        emptySpace.setPreferredSize(new Dimension(100, 40));
        topPanel.add(emptySpace,BorderLayout.EAST);
        settingTabPanel = new JPanel();
        settingTabPanel.setBorder(new EmptyBorder(0,40,0,80));
        settingTabPanel.add(new TabbedPanel());
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(topPanel,BorderLayout.NORTH);
        mainPanel.add(settingTabPanel);
        mainPanel.setBorder(new EmptyBorder(20,20,80,40));
        setupMainPanel();
    }

}
