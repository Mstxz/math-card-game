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
        banner.setBackground(SharedResource.SIAMESE_DARK);
        banner.setBorder(new MatteBorder(2,0,2,0,SharedResource.SIAMESE_BRIGHT));

        this.setLayout(new GridLayout(3,1));
        this.add(new JLabel());
        this.add(banner);
        this.add(new JLabel());
        this.setBackground(new Color(0,0,0,0.6f));
        this.setBounds(0,0, Router.getMainFrame().getWidth(),Router.getMainFrame().getHeight());
    }
}
