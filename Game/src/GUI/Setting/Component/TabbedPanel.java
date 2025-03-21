package GUI.Setting.Component;

import GUI.Setting.UIManager.TabbedPanelUI;
import utils.SharedResource;

import javax.swing.*;

public class TabbedPanel extends JTabbedPane {
    GameSetting gameSetting;
    Profile profile;

    public TabbedPanel(){
        this.setUI(new TabbedPanelUI());
        this.setBorder(BorderFactory.createEmptyBorder());
        gameSetting = new GameSetting();
        profile = new Profile();

        this.addTab("Game Setting",gameSetting);
        this.addTab("Profile",profile);

        this.setSize(1000,595);
    }

    public static void main(String[] args) {
        SharedResource.loadFont();
        JFrame frame = new JFrame();
        frame.add(new TabbedPanel());

        frame.setSize(1000,595);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
