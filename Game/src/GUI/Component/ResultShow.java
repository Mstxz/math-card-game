package GUI.Component;

import GUI.Router;
import utils.SharedResource;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class ResultShow extends JPanel {
    private  JPanel banner;
    private JLabel resultText;
    public ResultShow(boolean isWin){
        banner = new JPanel();
        resultText = new JLabel();
        if (isWin){
            resultText.setText("Victory");
        }
        else {
            resultText.setText("Defeat");
        }
        resultText.setFont(SharedResource.getCustomSizeFont(64));
        banner.setBackground(SharedResource.SIAMESE_DARK);
        banner.setBorder(new MatteBorder(2,0,2,0,SharedResource.SIAMESE_BRIGHT));
        banner.setBounds(0,Router.getMainFrame().getHeight()/2-150,Router.getMainFrame().getWidth(),300);
        resultText.setBounds((Router.getMainFrame().getWidth()-400)/2,banner.getY()-50,400,100);
        resultText.setHorizontalAlignment(SwingConstants.CENTER);
        System.out.println(resultText);
        this.setLayout(null);
        this.add(resultText);
        this.add(banner);

        this.setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
