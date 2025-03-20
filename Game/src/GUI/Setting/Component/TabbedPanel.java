package GUI.Setting.Component;

import utils.ResourceLoader;
import utils.SharedResource;

import javax.swing.*;

public class TabbedPanel extends JTabbedPane {
    GameSetting gameSetting;
    Profile profile;

    public TabbedPanel(){
        this.setUI(new TabbedPanelUI());
        gameSetting = new GameSetting();
        profile = new Profile();

        this.addTab("Game Setting",gameSetting);
        this.addTab("Profile",profile);


    }

    public static void main(String[] args) {
        SharedResource.loadFont();
        JFrame frame = new JFrame();
        frame.add(new TabbedPanel());

        frame.setSize(500,500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
