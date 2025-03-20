package GUI.Setting.Component;

import utils.SharedResource;

import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.awt.*;

public class TabbedPanelUI extends BasicTabbedPaneUI {

    @Override
    protected void installDefaults() {
        super.installDefaults();
        tabInsets = new Insets(5,30,5,30);
    }


    @Override
    protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        tabPane.setFont(SharedResource.getCustomSizeFont(25));
        if (isSelected) {
            g2d.setPaint(SharedResource.SIAMESE_LIGHT);
        } else {
            g2d.setColor(new Color(214,203,191));
        }

        g2d.fillRoundRect(x, y, w, h, 20, 20); // Rounded corners

        int arc = 20; // Radius for the rounded corners

        // Draw rounded rectangle for tab background
        g2d.fillRoundRect(x, y, w, h, arc, arc);

        // Cover bottom corners to keep them square
        g2d.fillRect(x, y + h / 2, w, h / 2);
    }

    @Override
    protected void paintTab(Graphics g, int tabPlacement, Rectangle[] rects, int tabIndex, Rectangle iconRect, Rectangle textRect) {
        super.paintTab(g, tabPlacement, rects, tabIndex, iconRect, textRect);
    }
}
