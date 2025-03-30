package GUI.Component;

import javax.swing.*;
import java.awt.*;

public class RoundedPanel extends JPanel {
    private int cornerRadius;
    private Color backgroundColor;

    public RoundedPanel(int radius, Color bgColor) {
        this.cornerRadius = radius;
        this.backgroundColor = bgColor;
        setOpaque(false); // ทำให้ JPanel โปร่งใส
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // วาดพื้นหลังที่มีขอบโค้ง
        g2.setColor(backgroundColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

        g2.dispose();
    }
}