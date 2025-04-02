package GUI.Component;

import Gameplay.Card;
import Gameplay.Cards.CatClown;
import Gameplay.Cards.CatNap;
import Gameplay.Cards.SleepyCat;
import utils.ResourceLoader;
import utils.SharedResource;
import utils.UIManager.ButtonUI;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class CardPlayShow extends JPanel {
    private JPanel cardZone;
    private ImageIcon icon = null;

    public CardPlayShow(){
        cardZone = new JPanel(){
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                Graphics2D g2 = (Graphics2D) g;

                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                if (icon == null){
                    g2.setColor(SharedResource.SKYBLUE_BRIGHT);
                    g2.fillRoundRect(1,1,getWidth()-3,getHeight()-3,20,20);

                    g2.setColor(SharedResource.SKYBLUE_DARK);
                    float[] dashPattern = {10,5};
                    g2.setStroke(new BasicStroke(3,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,10,dashPattern,10));
                    g2.drawRoundRect(1,1,getWidth()-3,getHeight()-3,20,20);


                    g2.setFont(SharedResource.getCustomSizeFont(32));
                    FontMetrics metrics = g2.getFontMetrics(SharedResource.getCustomSizeFont(32));
                    int textWidth = metrics.stringWidth("Play Zone");
                    int textHeight = metrics.getHeight();

                    // Calculate centered position
                    int x = (getWidth() - textWidth) / 2;
                    int y = (getHeight() - textHeight) / 2 + metrics.getAscent();

                    // Draw text
                    g2.drawString("Play Zone", x, y);
                }
                else {
                    g2.drawImage(icon.getImage(),0,0,null);
                }
            }
        };

        this.setSize(177,242);
        cardZone.setPreferredSize(new Dimension(177,242));
        cardZone.setMinimumSize(new Dimension(177,242));
        cardZone.setBackground(SharedResource.SIAMESE_BRIGHT);
        this.setLayout(new GridBagLayout());
        this.add(cardZone,new GridBagConstraints());
        this.setBackground(SharedResource.SIAMESE_BRIGHT);
    }

    public void setIcon(Card c){
        icon = ResourceLoader.loadPicture(c.getPicture(),177,242);
        repaint();
    }

}
