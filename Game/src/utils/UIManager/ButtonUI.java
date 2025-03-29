package utils.UIManager;

import utils.SharedResource;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

public class ButtonUI extends BasicButtonUI {
    @Override
    public void paint(Graphics g, JComponent c) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        AbstractButton button = (AbstractButton) c;
        button.setFocusPainted(false);
        button.setBorderPainted(false);

        g2.setColor(c.getParent().getBackground());
        g2.fillRect(0,0,c.getWidth(),c.getHeight());

        g2.setColor(SharedResource.SKYBLUE_BASE);
        g2.fillRoundRect(0,0,c.getWidth(),c.getHeight(),10,10);
        g2.setColor(SharedResource.SKYBLUE_BRIGHT);

        int x;
        int y;
        FontMetrics fm = g2.getFontMetrics();
        x = (button.getWidth() - fm.stringWidth(button.getText())) / 2;

        if (button.getModel().isRollover() || button.getModel().isPressed()){
            g2.fillRoundRect(8,10,c.getWidth()-16,c.getHeight()-28,10,10);
            y = (button.getHeight() + fm.getAscent()) / 2 - 8;
        }
        else {
            g2.fillRoundRect(8,8,c.getWidth()-16,c.getHeight()-28,10,10);
            y = (button.getHeight() + fm.getAscent()) / 2 - 10;
        }


        g2.setColor(SharedResource.SKYBLUE_DARK);
        g2.setFont(button.getFont());
        g2.drawString(button.getText(), x, y);

        g2.setColor(SharedResource.SKYBLUE_DARK);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(1,1,c.getWidth()-3,c.getHeight()-3,10,10);
    }

    public static void main(String[] args) {
        SharedResource.loadFont();
        SharedResource.setAllFont();
        JFrame frame = new JFrame();
        JPanel p = new JPanel();
        p.setBackground(SharedResource.SIAMESE_LIGHT);
        //frame.setLayout(new FlowLayout());
        frame.setSize(400,100);
        JButton b1 = new JButton("Save");
        JButton b2 = new JButton("Create");
        b1.setUI(new ButtonUI());
        b2.setUI(new ButtonUI());

        b1.setPreferredSize(new Dimension(200,70));
        b2.setPreferredSize(new Dimension(200,70));

        p.add(b1);
        p.add(b2);

        frame.add(p);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
