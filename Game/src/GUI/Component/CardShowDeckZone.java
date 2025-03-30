package GUI.Component;

import utils.SharedResource;

import javax.swing.*;
import java.awt.*;

public class CardShowDeckZone extends JPanel {
    private int cardAmount;
    public static final int MAX_CARD = 60;
    public CardShowDeckZone(int cardAmount){
        this.cardAmount = cardAmount;
        this.setPreferredSize(new Dimension(75,75));
        this.setFont(SharedResource.getCustomSizeFont(32));
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(getParent().getBackground());
        g2.fillRect(0,0,getWidth(),getHeight());

        g2.setColor(SharedResource.SIAMESE_BRIGHT);
        if (cardAmount == MAX_CARD){
            g2.setColor(SharedResource.SKYBLUE_BRIGHT);
        }
        g2.fillRoundRect(2+10,2,56,60,15,15);

        g2.setColor(SharedResource.SIAMESE_DARK);
        if (cardAmount == MAX_CARD){
            g2.setColor(SharedResource.SKYBLUE_DARK);
        }
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(1+10,1,56+3,60+3,15,15);

        g2.setColor(SharedResource.SIAMESE_BRIGHT);
        if (cardAmount == MAX_CARD){
            g2.setColor(SharedResource.SKYBLUE_BRIGHT);
        }
        g2.fillRoundRect(2,2+8,56,60,15,15);

        g2.setColor(SharedResource.SIAMESE_DARK);
        if (cardAmount == MAX_CARD){
            g2.setColor(SharedResource.SKYBLUE_DARK);
        }
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(1,1+8,56+3,60+3,15,15);

        if (cardAmount == MAX_CARD){
            g2.setColor(SharedResource.SKYBLUE_DARK);
        }
        else {
            g2.setColor(SharedResource.SIAMESE_DARK);
        }

        g2.setFont(getFont());
        FontMetrics fm = g2.getFontMetrics();
        int x = (75 - fm.stringWidth(cardAmount+"")) / 2 -8;
        int y = (75 + fm.getAscent()) / 2 - 2;
        g2.drawString(cardAmount+"", x, y);

    }

    public static void main(String[] args) {
        SharedResource.loadFont();
        SharedResource.setAllFont();
        JFrame f = new JFrame();
        JPanel p = new JPanel();
        CardShowDeckZone a = new CardShowDeckZone(20);
        CardShowDeckZone b = new CardShowDeckZone(2);
        CardShowDeckZone c = new CardShowDeckZone(40);

        f.add(p,BorderLayout.CENTER);
        p.add(a);
        p.add(b);
        p.add(c);
        p.setBackground(SharedResource.SIAMESE_BASE);

        f.setSize(200,200);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    public int getCardAmount() {
        return cardAmount;
    }

    public void setCardAmount(int cardAmount) {
        this.cardAmount = cardAmount;
        repaint();
    }
}
