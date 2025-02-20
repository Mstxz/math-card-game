package Dump;

import javax.swing.*;
import java.awt.*;

public class GraphicsDump extends JFrame {
    public GraphicsDump() {
        setTitle("Cards Click Event");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        Cards card1 = new Cards((1920/2)-165, 780, 165, 225, Color.BLACK);
        card1.setBounds((1920/2)-165, 780, 165, 225);

        Cards card2 = new Cards(((1920/2)-165) + 200, 780, 165, 225, Color.BLACK);
        card2.setBounds((1920/2)-165 + 200, 780, 165, 225);

        add(card1);
        add(card2);
        setVisible(true);
    }

    public static void main(String[] args) {
        new GraphicsDump();
    }
}
