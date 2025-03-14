package GUI.Component;

import GUI.Router;
import utils.SharedResource;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class ResultShow extends JPanel {
    private  JPanel banner;
    private String displayText;
    public ResultShow(String displayText) {
        banner = new JPanel();
        this.displayText = displayText;
        JLabel bigText = new JLabel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setFont(SharedResource.getCustomSizeFont(100));
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int x = 35;
                int y = 90;
                g2d.setColor(SharedResource.SIAMESE_BRIGHT);
                for(int dx = -5 ;dx <= 5 ;dx++){
                    for(int dy = -5 ;dy <= 5 ;dy++){
                        g2d.drawString(displayText, x+dx,y+dy);
                    }
                }
                g2d.setColor(SharedResource.SIAMESE_LIGHT);
                g2d.drawString(displayText, x,y);
                System.out.println(x+ ", " + y);
            }
        };

        banner.setBackground(SharedResource.SIAMESE_DARK);
        banner.setBorder(new MatteBorder(2,0,2,0,SharedResource.SIAMESE_BRIGHT));
        banner.setBounds(0,Router.getMainFrame().getHeight()/2-150,Router.getMainFrame().getWidth(),300);
        bigText.setBounds((Router.getMainFrame().getWidth()-400)/2,banner.getY()-50,400,200);
        //System.out.println(resultText);
        this.setLayout(null);
        this.add(bigText);
        this.add(banner);

        this.setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
