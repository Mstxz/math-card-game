package GUI.Component;

import utils.SharedResource;

import javax.swing.*;
import java.awt.*;

public class TutorialSlideShowButton extends JButton {
    public TutorialSlideShowButton(String title) {
        setText(title);
        setFont(SharedResource.getCustomSizeFont(32));
        setBackground(SharedResource.TRANSPARENT);
        setBorder(BorderFactory.createLineBorder(SharedResource.TRANSPARENT));
        setPreferredSize(new Dimension(200, 50));

        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
    }
}
