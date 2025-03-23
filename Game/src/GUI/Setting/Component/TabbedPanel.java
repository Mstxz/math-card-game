package GUI.Setting.Component;

import GUI.Setting.UIManager.TabbedPanelUI;
import utils.SharedResource;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class TabbedPanel extends JTabbedPane {
    GameSetting gameSetting;
    Profile profile;

    public TabbedPanel(){
        this.setUI(new TabbedPanelUI());
        this.setOpaque(false);
        gameSetting = new GameSetting();
        profile = new Profile();

        this.addTab("Game Setting",gameSetting);
        this.addTab("Profile",profile);
        //this.setBorder(new LineBorder(SharedResource.SIAMESE_DARK,5));

    }

    @Override
    protected void paintComponent(Graphics g) {
        this.setBackground(this.getParent().getBackground());
        super.paintComponent(g);
    }

    public static void main(String[] args) {
        SharedResource.loadFont();
        JFrame frame = new JFrame();
        frame.setLayout(null);
        frame.add(new TabbedPanel());

        frame.setSize(1000,659);
        frame.setBackground(SharedResource.SIAMESE_BRIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
