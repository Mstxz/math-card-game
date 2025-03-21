package GUI.Setting.Component;

import utils.SharedResource;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class GameSetting extends JPanel {
    private JPanel panel1;
    private JPanel panel2;

    public GameSetting(){
        this.setBackground(SharedResource.SIAMESE_LIGHT);
        this.setLayout(new BorderLayout());

        panel1 = new JPanel();
        panel1.setLayout(new GridLayout(3,3));
        panel1.add(new JLabel("BGM Volume"));
        panel1.setBorder(new EmptyBorder(70,80,20,20));

        this.add(panel1,BorderLayout.NORTH);
    }
}
