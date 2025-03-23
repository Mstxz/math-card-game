package utils;

import javax.swing.*;
import javax.swing.plaf.PanelUI;
import java.awt.*;

public class RoundPanelUI extends PanelUI {
    private Color color;
    private int arcWidth;
    private int arcHeight;
    public RoundPanelUI(Color c){
        this(c,20,20);
        
    }
    public RoundPanelUI(Color color,int arcWidth, int arcHeight){
        this.color = color;
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
    }
    @Override
    public void paint(Graphics g, JComponent c) {
        c.setBackground(this.color);
        Graphics2D g2d = ((Graphics2D)(g));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(c.getParent().getBackground());
        g2d.fillRect(0, 0, c.getWidth(), c.getHeight());
        g2d.setColor(this.color);
        g2d.fillRoundRect(0,0,c.getWidth(),c.getHeight(),arcWidth,arcHeight);
        super.paint(g,c);
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        JPanel p1 = new JPanel();
        p1.setUI(new RoundPanelUI(SharedResource.SIAMESE_LIGHT));
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