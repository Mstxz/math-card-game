package GUI.Setting.UIManager;

import utils.SharedResource;

import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.awt.*;

public class TabbedPanelUI extends BasicTabbedPaneUI {

    @Override
    protected void installDefaults() {
        super.installDefaults();
        tabInsets = new Insets(5,60,5,60);
        contentBorderInsets = new Insets(0,0,0,0);
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
        g2d.fillRoundRect(x, y, w, h, 60, 60);
        g2d.fillRect(x, y + h / 2, w, h / 2);
    }

    @Override
    protected void paintTab(Graphics g, int tabPlacement, Rectangle[] rects, int tabIndex, Rectangle iconRect, Rectangle textRect) {
        super.paintTab(g, tabPlacement, rects, tabIndex, iconRect, textRect);
    }

    @Override
    protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {

    }

    @Override
    protected void paintFocusIndicator(Graphics g, int tabPlacement, Rectangle[] rects, int tabIndex, Rectangle iconRect, Rectangle textRect, boolean isSelected) {

    }

    @Override
    protected void paintContentBorder(Graphics g, int tabPlacement, int selectedIndex) {

    }


}
