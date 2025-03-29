package GUI.Component;

import utils.SharedResource;
import utils.UIManager.RoundPanelUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TurnCount extends JPanel {
    private final JLabel countLabel;
    public TurnCount(){
        super();
        JLabel countTitle = new JLabel("Turn Count");
        countTitle.setFont(SharedResource.getCustomSizeFont(34));
        countTitle.setForeground(SharedResource.SIAMESE_BRIGHT);
        countLabel = new JLabel("1 / 40");
        countLabel.setFont(SharedResource.getCustomSizeFont(40));
        countLabel.setForeground(SharedResource.SIAMESE_BRIGHT);

        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.add(countTitle);
        this.add(countLabel);
        this.setPreferredSize(new Dimension(250,150));
        this.setMaximumSize(new Dimension(250,150));
        this.setBorder(new EmptyBorder(20,20,20,20));
        this.setUI(new RoundPanelUI(SharedResource.SIAMESE_DARK,30,30,false,true,false,true));
    }

    public void updateCount (int count){
        countLabel.setText(count + " / 40");
    }
}
