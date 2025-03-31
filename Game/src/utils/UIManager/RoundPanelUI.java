package utils.UIManager;

import utils.SharedResource;

import javax.swing.*;
import javax.swing.plaf.PanelUI;
import java.awt.*;

/**
 * <h2>Example</h2>
 *
 * <p>JPanel p1 = new JPanel();</p>
 * <p>p1.setUI(new RoundPanelUI(SharedResource.SIAMESE_LIGHT));</p>
 * <p>p1.setPreferredSize(new Dimension(300,300));</p>
 *
 */
public class RoundPanelUI extends PanelUI {
    private Color color;
    private int arcWidth;
    private int arcHeight;
    private boolean topLeft;
    private boolean topRight;
    private boolean bottomLeft;
    private boolean bottomRight;

    private Color borderColor;
    private int borderWidth;

    public RoundPanelUI(Color c){
        this(c,20,20);
    }
    public RoundPanelUI(Color color,int arcWidth, int arcHeight){
        this(color,arcWidth,arcHeight,true,true,true,true,null,0);
    }

    public RoundPanelUI(Color color,int arcWidth,int arcHeight,Color borderColor,int borderWidth){
        this(color,arcWidth,arcHeight,true,true,true,true,borderColor,borderWidth);
    }

    public RoundPanelUI(Color color,int arcWidth,int arcHeight,boolean topLeft,boolean topRight,boolean bottomLeft,boolean bottomRight){
        this(color,arcWidth,arcHeight,topLeft,topRight,bottomLeft,bottomRight,null,0);
    }

    public RoundPanelUI(Color color, int arcWidth, int arcHeight, boolean topLeft, boolean topRight, boolean bottomLeft, boolean bottomRight,Color borderColor,int borderWidth) {
        this.color = color;
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
        this.borderColor = borderColor;
        this.borderWidth = borderWidth;
    }

    @Override
    public void installUI(JComponent c) {
        super.installUI(c);
        c.setOpaque(false);
        c.setBackground(color);
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        Graphics2D g2d = ((Graphics2D)(g));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(this.color);
        g2d.fillRoundRect(0,0,c.getWidth(),c.getHeight(),arcWidth,arcHeight);

        if (!topLeft){
            g2d.fillRect(0,0,arcWidth,arcHeight);
        }
        if (!topRight){
            g2d.fillRect(c.getWidth()-arcWidth,0,arcWidth,arcHeight);
        }
        if (!bottomLeft){
            g2d.fillRect(0,c.getHeight() - arcHeight,arcWidth,arcHeight);
        }
        if (!bottomRight){
            g2d.fillRect(c.getWidth()-arcWidth,c.getHeight()-arcHeight,arcWidth,arcHeight);
        }

        if (borderColor != null){
            g2d.setColor(borderColor);
            g2d.setStroke(new BasicStroke(borderWidth));
            g2d.drawRoundRect(borderWidth/2,borderWidth/2,c.getWidth()-borderWidth,c.getHeight()-borderWidth,arcWidth-borderWidth,arcHeight-borderWidth);
        }
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        JPanel p1 = new JPanel();
        p1.setUI(new RoundPanelUI(SharedResource.SIAMESE_LIGHT,10,10,SharedResource.SIAMESE_BRIGHT,5));
        p1.setPreferredSize(new Dimension(300,300));
        JPanel p2 = new JPanel();
        p2.setUI(new RoundPanelUI(SharedResource.SIAMESE_BASE));
        p2.add(p1);
        f.add(p2,BorderLayout.CENTER);

        f.setSize(500,500);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}