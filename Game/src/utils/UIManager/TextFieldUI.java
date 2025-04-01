package utils.UIManager;

import GUI.Component.RoundBorder;
import utils.SharedResource;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicTextFieldUI;
import java.awt.*;

public class TextFieldUI extends BasicTextFieldUI {


    @Override
    protected void paintSafely(Graphics g) {
        JTextField tmp = ((JTextField)getComponent());
        tmp.setFont(SharedResource.getCustomSizeFont(28));
        tmp.setBounds(getComponent().getBounds());
        tmp.setForeground(SharedResource.SIAMESE_DARK);
        //tmp.setBorder(new EmptyBorder(3,3,3,3));
        tmp.setOpaque(false); // ทำให้พื้นหลังของ JTextField โปร่งใส
        tmp.setBackground((tmp.getParent().getBackground()));
        super.paintSafely(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(SharedResource.SIAMESE_DARK);
        g2d.setStroke(new BasicStroke(3));
        g.drawRoundRect(1,1 , getComponent().getWidth() -3 , getComponent().getHeight() -3,10,10);

    }
    @Override
    protected void paintBackground(Graphics g) {
        super.paintBackground(g);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(500,500);
        JTextField t = new JTextField();
        t.setSize(100,100);
        t.setUI(new TextFieldUI());
        frame.add(t,BorderLayout.SOUTH);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
