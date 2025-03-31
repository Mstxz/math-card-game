package GUI.Component;

import utils.ResourceLoader;
import utils.SharedResource;
import utils.UIManager.RoundPanelUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class IconButton extends JPanel {
    private JButton button;
    public IconButton(String iconPath){
        button = new JButton();
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setIcon(ResourceLoader.loadPicture(iconPath,40,50));
        button.setPreferredSize(new Dimension(65,65));
        button.setBackground(SharedResource.SIAMESE_BRIGHT);

        this.setLayout(new BorderLayout());
        this.add(button,BorderLayout.CENTER);
        this.setPreferredSize(new Dimension(65,65));
        this.setUI(new RoundPanelUI(SharedResource.SIAMESE_BRIGHT,10,10));
    }

    public JButton getButton() {
        return button;
    }
}
