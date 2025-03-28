package GUI.Component;
import GUI.Component.RoundBorder;
import utils.SharedResource;

import javax.swing.*;
        import java.awt.*;

public class PlayerVSPlayerSelectButton extends JButton {
    public PlayerVSPlayerSelectButton(String text) {
        super(text);
        setPreferredSize(new Dimension(350, 80));
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
        //setFont(new Font("SansSerif", Font.BOLD, 24));
    }
}