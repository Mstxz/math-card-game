package GUI.Component;

import javax.swing.*;
import java.awt.*;

public class RoundedButton extends JButton {
    public RoundedButton(String text) {
        super(text);
        setFont(new Font("Arial", Font.BOLD, 18));
        setBackground(new Color(190, 220, 250)); // Light blue
        setForeground(Color.BLACK);
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Button background
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);

        // Button border
        g2.setColor(new Color(150, 180, 230));
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(1, 1, getWidth() - 3, getHeight() - 3, 25, 25);

        g2.dispose();
        super.paintComponent(g);
    }
}
