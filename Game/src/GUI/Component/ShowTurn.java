package GUI.Component;

import utils.SharedResource;

import javax.swing.*;
import java.awt.*;

public class ShowTurn extends JLabel {
//    170*170
    private int turn = 0;

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
        this.setText(String.valueOf(turn));
    }

    public ShowTurn() {
        this.setPreferredSize(new Dimension(100,50));
        this.setFont(SharedResource.getCustomSizeFont(25));
        this.setForeground(SharedResource.SIAMESE_BRIGHT);
        this.setBackground(SharedResource.SIAMESE_BASE);
        this.setOpaque(true);

        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setVerticalAlignment(SwingConstants.CENTER);

        //this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setText(String.valueOf(getTurn()));
    }
}
