package GUI.Component;

import java.awt.*;
import javax.swing.border.Border;

public class RoundBorder implements Border {
    private int radius;
    private Color borderColor;
    private Color backgroundColor;
    private int thickness;

    public RoundBorder(Color borderColor, Color backgroundColor, int thickness, int radius) {
        this.borderColor = borderColor;
        this.backgroundColor = backgroundColor;
        this.thickness = thickness;
        this.radius = radius;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
    Graphics2D g2 = (Graphics2D) g.create();
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    if (backgroundColor != null) {
        g2.setColor(backgroundColor);
        g2.fillRoundRect(x, y, width, height, radius, radius);
    }

    g2.setColor(borderColor);
    g2.setStroke(new BasicStroke(thickness));
    g2.drawRoundRect(x + thickness / 2, y + thickness / 2, width - thickness, height - thickness, radius, radius);

    g2.dispose();
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(thickness, thickness, thickness, thickness);
    }

    @Override
    public boolean isBorderOpaque() {
        return true;
    }
}
