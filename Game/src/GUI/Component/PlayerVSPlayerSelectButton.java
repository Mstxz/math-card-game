package GUI.Component;

import javax.swing.*;
import java.awt.*;

public class PlayerVSPlayerSelectButton extends JButton {
    public PlayerVSPlayerSelectButton(String text) {
        super(text);
        setPreferredSize(new Dimension(550, 76)); // ตั้งขนาดปุ่ม
        setFocusPainted(false);
        setBackground(new Color(216, 220, 223)); // สีพื้นหลัง
        setForeground(new Color(102, 142, 169)); // สีตัวอักษร
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(102, 142, 169), 3), // ขอบนอกสุด
                BorderFactory.createLineBorder(new Color(163, 190, 208), 3)  // ขอบชั้นกลาง
        ));
        //setFont(new Font("SansSerif", Font.BOLD, 24));
    }
}