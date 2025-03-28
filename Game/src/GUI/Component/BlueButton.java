package GUI.Component;

import utils.SharedResource;

import javax.swing.*;
import java.awt.*;

public class BlueButton extends JButton {
    public BlueButton(String text, int width, int height) {
        super(text);
        setPreferredSize(new Dimension(width, height));
        setMaximumSize(new Dimension(width, height));
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setHorizontalAlignment(SwingConstants.CENTER); // Center text horizontally
        setVerticalAlignment(SwingConstants.CENTER);   // Center text vertically
//        setMinimumSize(new Dimension(350, 80));
//        setMaximumSize(new Dimension(350, 80));

        setFocusPainted(false);
        setBackground(SharedResource.SKYBLUE_BRIGHT);
        setForeground(SharedResource.SKYBLUE_BASE);

//        setBorder(BorderFactory.createCompoundBorder(
//                BorderFactory.createLineBorder(new Color(102, 142, 169), 12), // ขอบนอกสุด
//                BorderFactory.createLineBorder(new Color(163, 190, 208), 1)  // ขอบชั้นกลาง
//        ));

        setBorder(BorderFactory.createCompoundBorder(
                new RoundBorder(SharedResource.SKYBLUE_DARK, new Color(98, 86, 77), 3, 36),
                BorderFactory.createCompoundBorder(
                        new RoundBorder(SharedResource.SKYBLUE_BASE, SharedResource.SKYBLUE_BASE, 10, 30),
                        new RoundBorder(SharedResource.SKYBLUE_BRIGHT, SharedResource.SKYBLUE_BRIGHT, 1, 16)
                )
        ));
        setHorizontalAlignment(SwingConstants.CENTER);
        add(BlueButton.createLabel(this, this.getPreferredSize()), BorderLayout.CENTER);
    }

    public static JLabel createLabel(BlueButton b, Dimension d) {
        JLabel label = new JLabel(b.getText());
        label.setPreferredSize(d);
        label.setMaximumSize(d);
        label.setHorizontalAlignment(SwingConstants.CENTER); // Center text horizontally
        label.setVerticalAlignment(SwingConstants.CENTER);   // Center text vertically
        label.setFont(SharedResource.getCustomSizeFont(28)); // Replace with your font method
        label.setForeground(SharedResource.SKYBLUE_DARK); // Replace with `SharedResource.SKYBLUE_DARK`
        return label;
    }
}
