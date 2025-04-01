package GUI.Page;

import GUI.Component.ExitButton;
import GUI.Component.RotatingSettingOption;
import GUI.Router;
import GUI.Setting.Component.TabbedPanel;
import GUI.Setting.Controller.SettingController;
import GUI.Setting.UserPreference;
import utils.SharedResource;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SettingPage extends Page implements KeyListener {
    private JPanel settingTabPanel;
    private JLabel settingTitle;


    public SettingPage(){
        mainPanel.setBackground(SharedResource.SIAMESE_BRIGHT);
        ExitButton exitButton = new ExitButton("MainMenu"){
            @Override
            public void cleanUp() {
                mainFrame.removeKeyListener(SettingPage.this);
            }
        };
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        settingTitle = new JLabel("Setting");
        settingTitle.setFont(SharedResource.getCustomSizeFont(48));
        settingTitle.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(exitButton,BorderLayout.WEST);
        topPanel.add(settingTitle,BorderLayout.CENTER);
        topPanel.setBackground(SharedResource.SIAMESE_BRIGHT);
        JLabel emptySpace = new JLabel();
        emptySpace.setPreferredSize(new Dimension(100, 40));
        topPanel.add(emptySpace,BorderLayout.EAST);
        settingTabPanel = new JPanel();
        settingTabPanel.setBorder(new EmptyBorder(0,40,0,80));
        settingTabPanel.add(new TabbedPanel());
        settingTabPanel.setBackground(SharedResource.SIAMESE_BRIGHT);
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(topPanel,BorderLayout.NORTH);
        mainPanel.add(settingTabPanel);
        mainPanel.setBorder(new EmptyBorder(20,20,80,40));
        mainFrame.addKeyListener(this);
        setupMainPanel();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code;

        if (!this.getMainPanel().isFocusable())
            return;
        code = e.getKeyCode();
        if (code == KeyEvent.VK_ESCAPE){
            this.getMainFrame().removeKeyListener(this);
            Router.setRoute("MainMenu",null);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
