package utils.UIManager;

import GUI.Component.RoundBorder;
import utils.SharedResource;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.plaf.basic.BasicTextFieldUI;
import java.awt.*;

public class TextFieldUI extends BasicTextFieldUI {


    @Override
    protected void paintSafely(Graphics g) {
        JTextField tmp = ((JTextField)getComponent());
        tmp.setFont(SharedResource.getCustomSizeFont(28));
        tmp.setBounds(getComponent().getBounds());
        tmp.setForeground(SharedResource.SIAMESE_DARK);
        tmp.setBorder(new CompoundBorder(
                new RoundBorder(SharedResource.SIAMESE_BASE, null, 3, 20),
                BorderFactory.createEmptyBorder(10,10,10,10)
        ));
        tmp.setOpaque(false); // ทำให้พื้นหลังของ JTextField โปร่งใส
        tmp.setBackground((tmp.getParent().getBackground()));
        super.paintSafely(g);
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
