package GUI.Component;

import javax.swing.*;
import java.awt.*;

public class RoundedPanel extends JPanel {
    private int cornerRadius;
    private Color backgroundColor;

    public RoundedPanel(int radius, Color bgColor) {
        this.cornerRadius = radius;
        this.backgroundColor = bgColor;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw background with rounded edges
        g2.setColor(backgroundColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

        // Draw blue border
        g2.setColor(new Color(0, 100, 255));
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(1, 1, getWidth() - 3, getHeight() - 3, cornerRadius, cornerRadius);

        g2.dispose();
    }
}
