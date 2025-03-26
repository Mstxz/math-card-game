package GUI.Component;
import GUI.Component.RoundBorder;
import javax.swing.*;
import java.awt.*;

public class PlayerVSPlayerSelectButton extends JButton {
    public PlayerVSPlayerSelectButton(String text) {
        super(text);
        setPreferredSize(new Dimension(350, 80));
//        setMinimumSize(new Dimension(350, 80));
//        setMaximumSize(new Dimension(350, 80));

        setFocusPainted(false);
        setBackground(new Color(216, 220, 223));
        setForeground(new Color(102, 142, 169));

//        setBorder(BorderFactory.createCompoundBorder(
//                BorderFactory.createLineBorder(new Color(102, 142, 169), 12), // ขอบนอกสุด
//                BorderFactory.createLineBorder(new Color(163, 190, 208), 1)  // ขอบชั้นกลาง
//        ));

        setBorder(BorderFactory.createCompoundBorder(
                new RoundBorder(new Color(102, 142, 169), new Color(98, 86, 77), 3, 36),
                BorderFactory.createCompoundBorder(
                        new RoundBorder(new Color(163, 190, 208), new Color(163, 190, 208), 10, 30),
                        new RoundBorder(new Color(216, 220, 223), new Color(216, 220, 223), 1, 16)
                )
        ));
        setHorizontalAlignment(SwingConstants.CENTER);
        //setFont(new Font("SansSerif", Font.BOLD, 24));
    }
}