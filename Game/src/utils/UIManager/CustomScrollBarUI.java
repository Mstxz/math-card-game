package utils.UIManager;

import utils.SharedResource;

import java.awt.*;
import javax.swing.JButton;
import javax.swing.JScrollBar;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicScrollBarUI;

/**
 * <h2>Example</h2>
 * <br>
 * <pre>
 *     JScrollbar Bar = new JScrollbar();
 *     Bar.setUI(new CustomScrollBarUI());
 * </pre>
 * <br>
 * <h3>or</h3>
 * <br>
 * <pre>
 *     JScrollPane Pane = new JScrollPane();
 *     Pane.getVerticalScrollBar().setUI(new CustomScrollBarUI());
 * </pre>
 * <br>
 */
public class CustomScrollBarUI extends BasicScrollBarUI {

    private final Dimension d = new Dimension();
    private Color defaultColor;
    private Color mouseHoverColor;
    private Color mouseClickColor;
    private Color trackColor;

    public CustomScrollBarUI(Color defaultColor, Color mouseHoverColor, Color mouseClickColor, Color trackColor){
        this.defaultColor = defaultColor;
        this.mouseHoverColor = mouseHoverColor;
        this.mouseClickColor = mouseClickColor;
        this.trackColor = trackColor;
    }

    public CustomScrollBarUI(Color defaultColor, Color mouseHoverColor, Color mouseClickColor){
        this(SharedResource.SIAMESE_DARK, SharedResource.SIAMESE_BASE, SharedResource.SIAMESE_LIGHT, null);
    }

    public CustomScrollBarUI(){
        this(SharedResource.SIAMESE_DARK, SharedResource.SIAMESE_BASE, SharedResource.SIAMESE_LIGHT);
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        return new JButton() {
            @Override
            public Dimension getPreferredSize() {
                return d;
            }
        };
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        return new JButton() {
            @Override
            public Dimension getPreferredSize() {
                return d;
            }
        };
    }

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle r) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (trackColor != null)
            g2.setPaint(this.trackColor);
        else {
            g2.setColor(c.getParent().getBackground());
        }
        g2.fillRoundRect(r.x, r.y, r.width, r.height, 10, 10);
        g2.setColor(SharedResource.SIAMESE_DARK);
        g2.drawRoundRect(r.x, r.y, r.width, r.height, 10, 10);
        g2.dispose();
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle r) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Color color = null;
        JScrollBar sb = (JScrollBar) c;
        if (!sb.isEnabled() || r.width > r.height) {
            return;
        } else if (isDragging) {
            color = this.mouseClickColor;
        } else if (isThumbRollover()) {
            color = this.mouseHoverColor;
        } else {
            color = this.defaultColor;
        }
        g2.setPaint(color);
        g2.fillRoundRect(r.x, r.y, r.width, r.height, 10, 10);
        g2.setPaint(Color.WHITE);
        g2.drawRoundRect(r.x, r.y, r.width, r.height, 10, 10);
        g2.dispose();
    }

    @Override
    protected void setThumbBounds(int x, int y, int width, int height) {
        super.setThumbBounds(x, y, width, height);
        scrollbar.repaint();
    }
}
